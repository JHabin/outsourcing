package com.sparta.outsourcing.interceptor;

import com.sparta.outsourcing.constants.SessionNames;
import com.sparta.outsourcing.exception.ErrorCode;
import com.sparta.outsourcing.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * create on 2024. 11. 28. create by IntelliJ IDEA.
 *
 * <p> 로그인 확인을 위한 인터셉터. </p>
 *
 * @author Seokgyu Hwang (Chris)
 * @version 1.0
 * @since 1.0
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 로그인이 필요한지 확인합니다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws UserException {
        String requestURI = request.getRequestURI(); // 요청 URI 확인
        HttpSession session;
        if ("/users/login".equals(requestURI)) {
            session = request.getSession(true); // 세션 생성
            return true;

        }
        session = request.getSession(false);

        if (session == null) {
            throw new UserException(ErrorCode.SESSION_INVALID);
        }
        if (session.getAttribute(SessionNames.USER_AUTH) == null) {
            throw new UserException(ErrorCode.UNAUTHORIZED);
        }

        return true;
    }
}
