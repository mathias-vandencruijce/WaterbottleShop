package be.ucll.cloud9.domain.service;

import be.ucll.cloud9.domain.db.EmailSubscriberRepository;
import be.ucll.cloud9.domain.db.FeedbackRepository;
import be.ucll.cloud9.domain.model.EmailSubscriber;
import be.ucll.cloud9.domain.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmailSubscriberService {
    @Autowired
    private EmailSubscriberRepository emailSubscriberRepository;

    public List<EmailSubscriber> getAllEmails() {return emailSubscriberRepository.findAll();}
    public void addEmail(EmailSubscriber emailSubscriber) {emailSubscriberRepository.save(emailSubscriber);}
}

