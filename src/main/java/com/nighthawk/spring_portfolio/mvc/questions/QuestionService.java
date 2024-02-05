package com.nighthawk.spring_portfolio.mvc.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Autowired
    private CustomQuestionRepository customQuestionRepository;

    public List<Question> getQuestionsByCourse(String courseName) {
        return customQuestionRepository.findByCourse(courseName);
    }


    // Other business logic methods
}
