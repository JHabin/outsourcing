package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.common.Authentication;
import com.sparta.outsourcing.constants.SessionNames;
import com.sparta.outsourcing.dto.user.DeactivateRequestDto;
import com.sparta.outsourcing.dto.user.LoginRequestDto;
import com.sparta.outsourcing.dto.user.SignUpRequestDto;
import com.sparta.outsourcing.dto.user.SignUpResponseDto;
import com.sparta.outsourcing.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // @Controller + @ResponseBody, 보통 return이 body 형태
@RequiredArgsConstructor    // 생성자 의존성 주입
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    /**
     * ResponseEntity로 HTTP 상태와 reponsedto body를 같이 반환
     * @Valid로 requestdto 유효성 검사, @RequestBody로 http 요청으로 받는 json을 request 객체로 변환
     * @param signUpRequestDto
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        //요청 데이터 dto 로부터 파라미터 값을 받아 회원 가입하는 메소드 구현
        SignUpResponseDto responseDto = userService.signUp(signUpRequestDto);
        //201 Created 반환하고 SignUpResponseDto 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto loginRequestDto,
                                        HttpServletRequest request) {   // HttpServletRequest로 세션 설정
        // requestDto로 login 구현. authentication은 사용자 인증 정보(이메일, 권한) 가짐
        Authentication authentication = this.userService.login(loginRequestDto);
        // 클라이언트의 세션 가져옴. 없으면 생성
        HttpSession session = request.getSession(true);
        // USER_AUTH라는 이름으로 authentication 객체를 세션에 저장 (key, value)
        session.setAttribute(SessionNames.USER_AUTH, authentication);
        // 200 ok 반환
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {  // HttpServletRequest : 현재 사용 중인 세션 가져오기 위함
        // 현재 세션을 가져옴. false - 세션이 존재하지 않을 경우 null을 반환, 세션 없으면 새로 생성 X
        // true면 세션 엾으면 새로 생성함
        HttpSession session = request.getSession(false);
        // 세션이 존재한다면, 현재 세션을 무효화함. -> 세션과 관련된 인증 정보 삭제됨
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        // 상태 코드 200 ok + "로그아웃 성공" 문자열 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("로그아웃 성공");
    }

    /*
    * 로그아웃이랑 구현은 비슷하지만, deleteUserById로 사용자를 삭제하는 코드가 추가됨.
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateUser(@PathVariable Long id,     // {id}를 경로에서 가져옴
                                                 @RequestBody DeactivateRequestDto deactivateRequestDto,    // 본인 인증에 필요한 이메일, 비밀번호 값 가짐
                                                 HttpServletRequest request) {
        // 해당 id와 그 비밀번호를 가지고 사용자 delete 메서드 수행
        userService.deleteUserById(id, deactivateRequestDto.getPassword());
        // 현재 세션을 가져옴. false - 세션이 존재하지 않을 경우 null을 반환, 세션 없으면 새로 생성 X
        HttpSession session = request.getSession(false);
        // 세션이 존재한다면, 현재 세션을 무효화함. -> 세션과 관련된 인증 정보 삭제됨
        if (session != null) {
            session.invalidate();
        }
        // 상태 코드 200 ok + "회원 삭제 성공" 문자열 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("회원 삭제 성공");
    }

}
