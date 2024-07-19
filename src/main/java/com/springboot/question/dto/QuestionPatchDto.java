package com.springboot.question.dto;

import com.springboot.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class QuestionPatchDto {
    @Setter
    private long questionId;
    private String title;
    private String content;
    private Question.BoardStatus boardStatus;
}