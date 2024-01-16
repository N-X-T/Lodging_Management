package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.nxt.main.models.contract_service;

import java.util.List;
import java.util.Optional;

public interface contract_serviceRepository extends JpaRepository<contract_service, String> {
    @Query(value = "SELECT uuid FROM contract_service WHERE contract_id = :uuid", nativeQuery = true)
    List<String> findUUIDBycontractid(String uuid);

    @Query(value = "SELECT * FROM contract_service WHERE contract_id = :uuid", nativeQuery = true)
    List<contract_service> findBycontractid(String uuid);
    @Query(value = "SELECT SUM(register_amount) FROM contract_service WHERE service_id = :service_id", nativeQuery = true)
    Optional<Integer> SumUsedService(String service_id);
}
