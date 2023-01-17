package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.entity.MemberLoginLog;
import com.zerobase.fastlms.member.repository.MemberLoginLogRepository;
import com.zerobase.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberLoginLogService {

    private final MemberRepository memberRepository;
    private final MemberLoginLogRepository memberLoginLogRepository;

    public void createMemberLoginLog(String username, String ip, String userAgent) {
        Member member = memberRepository.findById(username).orElseThrow(() ->
            new UsernameNotFoundException("회원 정보가 존재하지 않습니다.")
        );

        MemberLoginLog memberLoginLog = new MemberLoginLog(member, ip, userAgent);
        memberLoginLogRepository.save(memberLoginLog);
    }
}