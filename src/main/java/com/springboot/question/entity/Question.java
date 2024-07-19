package com.springboot.question.entity;

import com.springboot.answer.entity.Answer;
import com.springboot.like.entity.Like;
import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "QUESTIONS")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTERED;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus = BoardStatus.BOARD_PUBLIC;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(mappedBy = "question", fetch = FetchType.EAGER)
    private Answer answer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    public void addMember(Member member){
        if(!member.getQuestions().contains(this)){
            member.getQuestions().add(this);
        }
        this.member = member;
    }

    public void setAnswer(Answer answer){
        this.answer = answer;
        if(answer.getQuestion() != this){
            answer.setQuestion(this);
        }
    }

    public enum QuestionStatus {
        QUESTION_REGISTERED(1, "질문 등록 상태"),
        QUESTION_ANSWERED(2, "답변 완료 상태"),
        QUESTION_DELETED(3, "질문 삭제 상태"),
        QUESTION_DEACTIVED(4, "질문 비활성화 상태");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        QuestionStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

    public enum BoardStatus {
        BOARD_PUBLIC( "공개글 상태"),
        BOARD_SECRET( "비밀글 상태");

        @Getter
        private String stepDescription;

        BoardStatus(String stepDescription) {
            this.stepDescription = stepDescription;
        }
    }
}