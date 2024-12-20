package com.sparta.outsourcing.interceptor;

import com.sparta.outsourcing.constants.SessionNames;
import com.sparta.outsourcing.exception.ErrorCode;
import com.sparta.outsourcing.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component   // Spring에서 이 클래스를 Bean으로 등록하여 사용할 수 있도록 지정
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * controller로 가기 전에 호출되는 메서드
     * 로그인이 필요한지 확인합니다.
     * HttpServletRequest : HTTP 요청 정보
     * HttpServletResponse : 응답 정보 (HTTP 코드, 메시지 등)
     * handler : @Controller 또는 @RequestMapping으로 매핑된 메서드 정보 제공
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws UserException {

        HttpSession session = request.getSession(false);

        // 세션이 없을 경우 401 에러
        if (session == null) {
            throw new UserException(ErrorCode.SESSION_INVALID);
        }
        // 세션이 auth(회원) 표시가 없을 경우 401 에러
        if (session.getAttribute(SessionNames.USER_AUTH) == null) {
            throw new UserException(ErrorCode.UNAUTHORIZED);
        }

        return true;
    }
}
