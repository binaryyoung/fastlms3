package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.dto.MemberLoginLogDto;
import com.zerobase.fastlms.admin.mapper.MemberLoginLogMapper;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.entity.MemberLoginLog;
import com.zerobase.fastlms.member.repository.MemberLoginLogRepository;
import com.zerobase.fastlms.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberLoginLogService {

    private final MemberRepository memberRepository;
    private final MemberLoginLogRepository memberLoginLogRepository;
    private final MemberLoginLogMapper memberLoginLogMapper;

    public void createMemberLoginLog(String username, String ip, String userAgent) {
        Member member = memberRepository.findById(username).orElseThrow(() ->
            new UsernameNotFoundException("회원 정보가 존재하지 않습니다.")
        );

        MemberLoginLog memberLoginLog = new MemberLoginLog(member, ip, userAgent);
        memberLoginLogRepository.save(memberLoginLog);
    }

    public List<MemberLoginLogDto> list(MemberParam param) {
        long totalCount = memberLoginLogMapper.selectListCount(param);
        List<MemberLoginLogDto> list = memberLoginLogMapper.selectList(param);

        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (MemberLoginLogDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - param.getPageStart() - i);
                i++;
            }
        }

        return list;
    }
}