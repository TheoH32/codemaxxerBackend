package com.nighthawk.spring_portfolio.mvc.questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "Question.findByCourse", query = "SELECT * FROM ?1", resultClass = Question.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Question", nullable = false)
    private String question;

    @Column(name = "Answer 1", nullable = false)
    private String answer1;

    @Column(name = "Answer 2", nullable = false)
    private String answer2;

    @Column(name = "Answer 3", nullable = false)
    private String answer3;

    @Column(name = "Answer 4", nullable = false)
    private String answer4;

    @Column(name = "Correct Answer", nullable = false)
    private Integer correctAnswer;

    @Column(name = "Difficulty", nullable = false)
    private Integer difficulty;

    @Column(name = "Unit", nullable = false)
    private Integer unit;

    @Column(name = "Points", nullable = false)
    private Integer points;

    // Getters and setters
}
