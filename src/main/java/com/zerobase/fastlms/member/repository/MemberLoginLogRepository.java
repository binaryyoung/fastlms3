package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.MemberLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLoginLogRepository extends JpaRepository<MemberLoginLog, Long> {
}