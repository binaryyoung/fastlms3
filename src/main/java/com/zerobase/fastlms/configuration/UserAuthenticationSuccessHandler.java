package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.service.MemberLoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberLoginLogService memberLoginLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        String username = user.getUsername();
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("user-agent");

        memberLoginLogService.createMemberLoginLog(username, ip, userAgent);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}