package com.springboot.member.dto;

import com.springboot.member.entity.Member;
import com.springboot.member.entity.Stamp;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String name;
    private String phone;
    private Member.MemberStatus memberStatus;   // 추가된 부분
    private List<Stamp> memberStamps;

    // 추가된 부분
    public String getMemberStatus() {
        return memberStatus.getStatus();
    }
}
