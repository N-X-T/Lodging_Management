package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.nxt.main.models.feedback;

public interface feedbackRepository extends JpaRepository<feedback, String> {
}
