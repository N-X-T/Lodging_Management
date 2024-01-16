package project.nxt.main.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String type;
    @Column(unique=true)
    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "resident_id")
    @JsonIgnore
    private resident resident; //private String resident_id;

    @JsonGetter("resident_id")
    String resident_id(){
        if(resident == null)
            return "";
        return resident.getUuid();
    }
    private String admin_id;
//    @JsonIgnore
//    public Collection<? extends GrantedAuthority> getAuthorities(){
//        return List.of(new SimpleGrantedAuthority(this.type));
//    }
}
