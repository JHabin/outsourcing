
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
 * HandlerInterceptor : 요청이 컨트롤러로 전달되기 전에 처리하거나, 응답(response)이 사용자에게 전달되기 전에 수행
 * return true :  요청 처리가 계속 진행
 * false : 요청 중단
 */

@Component  // Spring에서 이 클래스를 Bean으로 등록하여 사용할 수 있도록 지정
public class UserRoleInterceptor implements HandlerInterceptor {

    @Override
    // 요청이 컨트롤러에 도달하기 전에 실행, 세션과 권한 검증
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws UserException {
        // 현재 HTTP 세션을 가져옵니다. 만약 세션이 존재하지 않으면 null을 반환
        HttpSession session = request.getSession(false);
        // 세션 없을 경우 401 반환
        if (session == null) {
            throw new UserException(ErrorCode.SESSION_INVALID);
        }
        // 세션에서 authentication 가져옴
        Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);
        // 세션에서 role 정보 가져오기
        Role role = authentication.getRole();
        System.out.println("user 권한 : "+role);

        // 회원이 아니라면 user 권한 필요 하다는 메시지
        if (role != Role.USER) {
            throw new UserException(ErrorCode.ROLE_MISMATCH);
        }

        return true;
    }
}
