package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.nxt.main.models.contract;

import java.util.List;


public interface contractRepository extends JpaRepository<contract, String> {
    @Query(value = "SELECT uuid FROM contracts WHERE uuid = :uuid", nativeQuery = true)
    String getStatus(@Param("uuid") String uuid);

    @Query(value = "SELECT * FROM contracts WHERE status = :status", nativeQuery = true)
    List<contract> getStatusContract(boolean status);

    @Query(value = "SELECT * FROM contracts WHERE signer = :signer", nativeQuery = true)
    contract findBySigner(String signer);
}
