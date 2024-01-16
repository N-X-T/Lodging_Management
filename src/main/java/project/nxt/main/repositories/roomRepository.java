package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.nxt.main.models.room;

import java.util.List;


public interface roomRepository extends JpaRepository<room, String> {
    @Query(value = "SELECT uuid FROM rooms WHERE uuid NOT IN (SELECT room_id FROM contracts);", nativeQuery = true)
    List<String> findEmptyRoom();
}
