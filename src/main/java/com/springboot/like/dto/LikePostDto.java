package com.springboot.like.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class LikePostDto {
    @Positive
    private long memberId;
    private long questionId;
}
