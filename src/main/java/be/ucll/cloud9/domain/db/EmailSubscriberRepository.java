package be.ucll.cloud9.domain.db;

import be.ucll.cloud9.domain.model.EmailSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSubscriberRepository extends JpaRepository<EmailSubscriber, Long> {
}
