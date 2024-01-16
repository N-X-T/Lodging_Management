package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.nxt.main.models.service_index;

import java.util.List;
import java.util.Optional;

public interface service_indexRepository extends JpaRepository<service_index, String> {
    @Query(value = "SELECT * FROM service_index WHERE room_id = :room_id AND service_id = :service_id", nativeQuery = true)
    List<service_index> findByRoomIdAndServiceId(String room_id, String service_id);

    @Query(value = "SELECT * FROM service_index WHERE room_id = :room_id", nativeQuery = true)
    List<service_index> findByRoomId(String room_id);
    @Query(value = "SELECT SUM(index_price) FROM service_index WHERE service_id = :service_id", nativeQuery = true)
    Optional<Integer> SumUsedService(String service_id);
}
