package com.springboot.question.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.answer.entity.Answer;
import com.springboot.like.entity.Like;
import com.springboot.member.entity.Member;
import com.springboot.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class QuestionResponseDto {
    private long questionId;
    private long memberId;
    private String title;
    private String content;
    private Question.QuestionStatus questionStatus;
    private LocalDateTime createdAt;
    private String answerContent;
    private int countLikes;

    public void setMember(Member member) {
        this.memberId = member.getMemberId();
    }


    public QuestionResponseDto(Question question) {
        if (question != null) {
            this.questionId = question.getQuestionId();
            this.title = question.getTitle();
            this.content = question.getContent();
            this.questionStatus = question.getQuestionStatus();
            this.createdAt = question.getCreatedAt();

            Answer answer = question.getAnswer();
            this.answerContent = (answer != null) ? answer.getAnswerContent() : null;

            this.countLikes = question.getLikes().size();
        }
    }
}