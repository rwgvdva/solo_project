package com.springboot.like.mapper;

import com.springboot.like.dto.LikePostDto;
import com.springboot.like.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "questionId", target = "question.questionId")
    Like likePostDtoToLike(LikePostDto likePostDto);
}
