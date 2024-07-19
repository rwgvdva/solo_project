package com.springboot.like.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.like.entity.Like;
import com.springboot.like.repository.LikeRepository;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import com.springboot.question.entity.Question;
import com.springboot.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public LikeService(LikeRepository likeRepository, MemberRepository memberRepository, QuestionRepository questionRepository) {
        this.likeRepository = likeRepository;
        this.memberRepository = memberRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public Like likeQuestion(long memberId, long questionId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        Optional<Like> existingLike = likeRepository.findByMemberAndQuestion(member, question);
        if(existingLike.isPresent()){
            likeRepository.delete(existingLike.get());
            question.getLikes().remove(existingLike.get());
            return null;
        }else {
            Like like = new Like();
            like.setMember(member);
            like.setQuestion(question);

            question.getLikes().add(like);
            return likeRepository.save(like);
        }
    }

    public int countLikes(Question question){
        return likeRepository.countByQuestion(question);
    }
}
