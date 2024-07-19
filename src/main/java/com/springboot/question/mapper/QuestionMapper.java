package com.springboot.question.mapper;

import com.springboot.answer.entity.Answer;
import com.springboot.member.entity.Member;
import com.springboot.question.dto.QuestionPatchDto;
import com.springboot.question.dto.QuestionPostDto;
import com.springboot.question.dto.QuestionResponseDto;
import com.springboot.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    default Question questionPostDtoToQuestion(QuestionPostDto questionPostDto) {
        Question question = new Question();
        Member member = new Member();
        member.setMemberId(questionPostDto.getMemberId());
        question.setContent(questionPostDto.getContent());
        question.setTitle(questionPostDto.getTitle());

        question.setMember(member);
        return question;
    }

    ;

    Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);

    default QuestionResponseDto questionToQuestionResponseDto(Question question) {
        if (question == null) {
            return null;
        }

        QuestionResponseDto questionResponseDto = new QuestionResponseDto(question);
        questionResponseDto.setQuestionId(question.getQuestionId());
        questionResponseDto.setTitle(question.getTitle());
        questionResponseDto.setContent(question.getContent());
        questionResponseDto.setQuestionStatus(question.getQuestionStatus());
        questionResponseDto.setCreatedAt(question.getCreatedAt());

        Answer answer = question.getAnswer();
        if (answer != null) {
            questionResponseDto.setAnswerContent(answer.getAnswerContent());
        }

        questionResponseDto.setCountLikes(question.getLikes().size());

        return questionResponseDto;
    }


    default List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions) {
        return questions.stream()
                .map(question -> new QuestionResponseDto(question))
                .collect(Collectors.toList());
    }
}