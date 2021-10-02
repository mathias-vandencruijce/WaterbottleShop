package be.ucll.cloud9.domain.service;

import be.ucll.cloud9.domain.db.FeedbackRepository;
import be.ucll.cloud9.domain.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {return feedbackRepository.findAll();}
    public void addFeedback(Feedback feedback) {feedbackRepository.save(feedback);}
}
