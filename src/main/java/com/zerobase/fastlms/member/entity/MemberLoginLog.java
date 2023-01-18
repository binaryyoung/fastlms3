package com.zerobase.fastlms.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Data
@Entity
public class MemberLoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    private LocalDateTime loginDt;
    private String ip;
    private String userAgent;

    public MemberLoginLog(Member member, String ip, String userAgent) {
        this.member = member;
        this.loginDt = LocalDateTime.now();
        this.ip = ip;
        this.userAgent = userAgent;
    }
}