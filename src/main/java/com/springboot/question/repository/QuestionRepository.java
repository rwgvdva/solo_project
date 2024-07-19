package com.springboot.question.repository;

import com.springboot.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> { // 수정된 부분
    Page<Question> findByQuestionStatusNot(Question.QuestionStatus status, Pageable pageable);
}
