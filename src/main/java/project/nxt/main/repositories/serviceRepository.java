package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.nxt.main.models.service;

import java.util.List;


public interface serviceRepository extends JpaRepository<service, String> {
    @Query(value = "SELECT uuid FROM services", nativeQuery = true)
    List<String> findAlluuid();
}
