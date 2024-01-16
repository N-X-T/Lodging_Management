package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nxt.main.models.resident;



public interface residentRepository extends JpaRepository<resident, String> {
}
