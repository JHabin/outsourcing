package com.sparta.outsourcing.service;

import com.sparta.outsourcing.common.Role;
import com.sparta.outsourcing.config.PasswordEncoder;
import com.sparta.outsourcing.dto.user.LoginRequestDto;
import com.sparta.outsourcing.entity.User;

import com.sparta.outsourcing.common.Authentication;
import com.sparta.outsourcing.dto.user.SignUpRequestDto;
import com.sparta.outsourcing.dto.user.SignUpResponseDto;
import com.sparta.outsourcing.exception.DeactivatedException;
import com.sparta.outsourcing.exception.DuplicatedEmailException;
import com.sparta.outsourcing.exception.ErrorCode;
import com.sparta.outsourcing.exception.PasswordIncorrectException;
import com.sparta.outsourcing.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // userRepository, passwordEncoder 의존성 주입 생성자 구현
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /*
    * 회원가입 requestDto(이메일,전화번호, 주소, 권한 등)를 받음
    * 저장된 회원 정보를 responseDto에 저장하며 return
     */
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        //중복된 사용자 아이디로 가입하는 경우 에러 "중복된 아이디입니다." 출력하며 예외 처리
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new DuplicatedEmailException(ErrorCode.DUPLICATED_EMAIL);
        }
        // requestDto에서 회원의 비밀번호 값을 받아 암호화, 로그인할 때에는 입력된 비밀번호를 암호화된 값과 비교하는 방식
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        // NOT_USE인 사용자 예외 처리

        // 암호화된 비밀번호 포함 회원 정보를 User 객체에 저장
        User user = new User(signUpRequestDto.getEmail(),
                encodedPassword,
                signUpRequestDto.getNickname(),
                signUpRequestDto.getAddress(),
                signUpRequestDto.getPhone(),
                signUpRequestDto.getRole());
        // savedUser 객체에 해당 정보 저장
        User savedUser = userRepository.save(user);
        //ResponseDto 형태로 해당 객체 반환
        return SignUpResponseDto.toDto(savedUser);

    }
    /*
    * 이메일, 비밀번호를 가진 requestDto 로 로그인 메서드 구현
    * 로그인 성공 시 Authentication(이메일, 권한 정보 포함) 객체로 반환
     */
    public Authentication login(LoginRequestDto loginRequestDto) {
        // 이메일 정보로 회원을 조회해서 조회된 사용자 findUser에 저장
        User findUser = userRepository.findByEmail(loginRequestDto.getEmail());

        // NOT_USE인 사용자 예외 처리

        if (findUser == null) {
            throw new DeactivatedException(ErrorCode.DEACTIVATED_USER); // 탈퇴한 회원 로그인 예외처리
        }
        // 조회된 회원의 비밀번호와 입력한 비밀번호 두 object를 비교해서 일치하지 않는 경우 예외 처리
        else if (!Objects.equals(findUser.getPassword(), loginRequestDto.getPassword())) {
            throw new PasswordIncorrectException(ErrorCode.PASSWORD_INCORRECT);
        }
        // 로그인 성공 시, 해당 회원의 이메일과 권한 정보를 가진 authentication 반환
        return new Authentication(findUser.getEmail(), findUser.getRole());
    }
    // 비활성화할 사용자의 id 와 비밀번호를 가지고 비활성화
    public void deleteUserById(Long id, String password) {
        // id로 사용자를 찾는다. 못 찾으면 예외 처리
        User user = userRepository.findByIdOrElseThrows(id);
        // 해당 사용자 권한을 NOT_USE로 변경
        user.disableUser(Role.NOT_USE);
    }

}
