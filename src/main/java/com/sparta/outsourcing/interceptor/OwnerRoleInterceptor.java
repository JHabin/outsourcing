package com.sparta.outsourcing.interceptor;

import com.sparta.outsourcing.common.Role;
import com.sparta.outsourcing.common.Authentication;
import com.sparta.outsourcing.constants.SessionNames;
import com.sparta.outsourcing.exception.ErrorCode;
import com.sparta.outsourcing.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 세션과 인증 정보를 확인하여 로그인 여부를 판단
 */
@Component
public class OwnerRoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws UserException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new UserException(ErrorCode.SESSION_INVALID);
        }

        Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);
        Role role = authentication.getRole();

        if (role != Role.OWNER) {
            throw new UserException(ErrorCode.ROLE_MISMATCH);
        }

        return true;
    }
}
