package com.springboot.like.mapper;

import com.springboot.like.dto.LikePostDto;
import com.springboot.like.entity.Like;
import com.springboot.member.entity.Member;
import com.springboot.question.entity.Question;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-18T18:04:25+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class LikeMapperImpl implements LikeMapper {

    @Override
    public Like likePostDtoToLike(LikePostDto likePostDto) {
        if ( likePostDto == null ) {
            return null;
        }

        Like like = new Like();

        like.setMember( likePostDtoToMember( likePostDto ) );
        like.setQuestion( likePostDtoToQuestion( likePostDto ) );

        return like;
    }

    protected Member likePostDtoToMember(LikePostDto likePostDto) {
        if ( likePostDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( likePostDto.getMemberId() );

        return member;
    }

    protected Question likePostDtoToQuestion(LikePostDto likePostDto) {
        if ( likePostDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( likePostDto.getQuestionId() );

        return question;
    }
}
