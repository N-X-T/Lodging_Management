package project.nxt.main.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import project.nxt.main.models.user;
import project.nxt.main.repositories.userRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {
    @Autowired
    userRepository userRepository;
    @Bean
    UserDetailsService users() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                user user = userRepository.findByusername(username);
                if(user == null){
                    throw new UsernameNotFoundException("Username not found!");
                }
                return new User(user.getUsername(),user.getPassword(), List.of(new SimpleGrantedAuthority(user.getType())));
            }
        };
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(
                                        "/api/contract/signer/**",
                                        "/api/invoice/getbycontractid/*",
                                        "/api/feedback/add/"
                                ).hasAuthority("resident")
                                .requestMatchers(
                                        "/api/room/*",
                                        "/api/resident/*"
                                ).hasAnyAuthority("resident","admin")
                                .requestMatchers(
                                        "/api/account/getall",
                                        "/api/contract/**",
                                        "/api/feedback/**",
                                        "/api/invoice/**",
                                        "/api/purchase/**",
                                        "/api/resident/**",
                                        "/api/room/**",
                                        "/api/roomtype/**",
                                        "/api/service/**",
                                        "/api/service-index/**",
                                        "/api/statistics/gettotalservice"
                                ).hasAuthority("admin")
                                .anyRequest().permitAll()
                )
                .formLogin(login ->
                        login
                                .loginProcessingUrl("/api/auth/signin")
                                .successHandler(new AuthenticationSuccessHandler() {
                                    @Override
                                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                        response.setStatus(HttpStatus.OK.value());
                                        response.addHeader("Content-Type","application/json");
                                        response.getWriter().write(new ObjectMapper().writeValueAsString(userRepository.findByusername(authentication.getName())));
                                    }
                                }) // Xử lý sau khi đăng nhập thành công
                                .failureHandler(new AuthenticationFailureHandler() {
                                    @Override
                                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                        response.setStatus(HttpStatus.OK.value());
                                        response.addHeader("Content-Type","plain/text");
                                        response.getWriter().write("fail");
                                    }
                                }) // Xử lý sau khi đăng nhập thất bại
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/api/auth/logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                ).exceptionHandling(except ->
                        except.accessDeniedHandler(new AccessDeniedHandler() {
                            @Override
                            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                                accessDeniedException.printStackTrace();
                                response.setContentType("plain/text;charset=UTF-8");
                                response.setStatus(403);
                                response.getWriter().write("Bạn không đủ quyền truy cập liên kết này!");
                            }
                        })
                );
        return http.build();
    }
}
