package project.nxt.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import project.nxt.main.repositories.userRepository;
import project.nxt.main.models.user;

import java.net.Authenticator;
import java.util.List;

@RestController
@RequestMapping(path = "")
public class authController {
    @Autowired
    private userRepository userRepository;
    @GetMapping("/api/auth/checkexpire")
    ResponseEntity<Object> checkexpire(Authentication auth){
        if(auth==null) return ResponseEntity.status(403).body(null);
        return ResponseEntity.ok().body(null);
    }
    @GetMapping("/api/account/getall")
    List<user> getall(){
        return userRepository.findAll();
    }
}
