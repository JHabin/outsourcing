package com.sparta.outsourcing.interceptor;

import com.sparta.outsourcing.common.Role;
import com.sparta.outsourcing.common.Authentication;
import com.sparta.outsourcing.constants.SessionNames;
import com.sparta.outsourcing.exception.ErrorCode;
import com.sparta.outsourcing.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 세션과 인증 정보를 확인하여 로그인 여부를 판단
 * HttpServletRequest : HTTP 요청 정보
 * HttpServletResponse : 응답 정보 (HTTP 코드, 메시지 등)
 * handler : @Controller 또는 @RequestMapping으로 매핑된 메서드 정보 제공
 */
@Component   // Spring에서 이 클래스를 Bean으로 등록하여 사용할 수 있도록 지정
public class OwnerRoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws UserException {

        //주문 단건조회에서 OWNER role 체크하지 않도록 추가
        String method = request.getMethod(); //요청 메서드명 GET, POST, ...인지 받아옴
        String requestURI = request.getRequestURI(); //요청이 들어온 URI를 받아옴
        if (method.equals(HttpMethod.GET.toString()) && requestURI.matches("/orders/\\d+")) { //정규표현식:orders+숫자를 의미
            return true;
        }
        if (method.equals(HttpMethod.GET.toString()) && requestURI.matches("/menus/\\d+")) { //정규표현식:orders+숫자를 의미
            return true;
        }

        // 세션 불러오기
        HttpSession session = request.getSession(false);

        // 세션이 없을 경우 401 에러
        if (session == null) {
            throw new UserException(ErrorCode.SESSION_INVALID);
        }
        // 세션에서 가져온 정보를 가진 authentication 객체 생성
        Authentication authentication = (Authentication) session.getAttribute(SessionNames.USER_AUTH);
        // 객체의 role 가져옴
        Role role = authentication.getRole();
        // role이 owner가 아닐 경우 권한 401 에러 (인가)
        if (role != Role.OWNER) {
            throw new UserException(ErrorCode.ROLE_MISMATCH);
        }

        return true;
    }
}
