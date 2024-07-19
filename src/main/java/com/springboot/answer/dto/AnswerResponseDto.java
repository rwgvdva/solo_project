package com.springboot.answer.dto;

import com.springboot.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerResponseDto {
    private long answerId;
    private long questionId;
    private String answerContent;
    private LocalDateTime createdAt;

//    public void setQuestion(Question question){
//        this.questionId = question.getQuestionId();
//    }
}
