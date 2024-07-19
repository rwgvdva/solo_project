package com.springboot.answer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AnswerPatchDto {
    @Setter
    private long answerId;
    private String answerContent;
}
