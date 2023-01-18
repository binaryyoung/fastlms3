package com.zerobase.fastlms.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberLoginLogDto {

    private Long id;
    private String userId;
    private LocalDateTime loginDt;
    private String ip;
    private String userAgent;

    private long totalCount;
    private long seq;
}
