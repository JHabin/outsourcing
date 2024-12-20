package com.sparta.outsourcing.config;

//import com.sparta.outsourcing.interceptor.AuthInterceptor;
//import com.sparta.outsourcing.interceptor.OwnerRoleInterceptor;
//import com.sparta.outsourcing.interceptor.UserRoleInterceptor;
import com.sparta.outsourcing.interceptor.AuthInterceptor;
import com.sparta.outsourcing.interceptor.OwnerRoleInterceptor;
import com.sparta.outsourcing.interceptor.UserRoleInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private static final String[] AUTH_REQUIRED_PATH_PATTERNS = {"/users/login", "/users/logout", "/users/deactivate"};
    private static final String[] OWNER_ROLE_REQUIRED_PATH_PATTERNS = {"/users/owner/*"};
    private static final String[] USER_ROLE_REQUIRED_PATH_PATTERNS = {"/users/user/*"};

    private final AuthInterceptor authInterceptor;
    private final OwnerRoleInterceptor ownerRoleInterceptor;
    private final UserRoleInterceptor userRoleInterceptor;

    /**
     * 우선순위 : 로그인 -> owner/user 권한 체크
     * 권한 체크는 딱히 우선순위 안 해도 되지만 +1로 일치 시킬 경우 둘 중 하나 랜덤이라 그냥 좀 더 구분을 명확하게 해 두기 위해 다르게 함.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authInterceptor)
                .addPathPatterns(AUTH_REQUIRED_PATH_PATTERNS)
                .order(Ordered.HIGHEST_PRECEDENCE);

        registry.addInterceptor(ownerRoleInterceptor)
                .addPathPatterns(OWNER_ROLE_REQUIRED_PATH_PATTERNS)
                .order(Ordered.HIGHEST_PRECEDENCE + 1);

        registry.addInterceptor(userRoleInterceptor)
                .addPathPatterns(USER_ROLE_REQUIRED_PATH_PATTERNS)
                .order(Ordered.HIGHEST_PRECEDENCE + 2);

    }

}
