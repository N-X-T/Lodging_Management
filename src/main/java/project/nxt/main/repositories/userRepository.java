package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nxt.main.models.user;

public interface userRepository extends JpaRepository<user, String>{
    user findByusername(String username);
}
