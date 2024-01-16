package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.nxt.main.models.purchase;

import java.util.List;

public interface purchaseRepository extends JpaRepository<purchase, String> {
    @Query(value = "SELECT * FROM purchases WHERE room_id = :uuid", nativeQuery = true)
    List<purchase> findByroomid(String uuid);
}
