package be.ucll.cloud9.domain.db;

import be.ucll.cloud9.domain.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
