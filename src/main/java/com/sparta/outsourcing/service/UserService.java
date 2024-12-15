package com.sparta.outsourcing.service;

import com.sparta.outsourcing.common.Role;
import com.sparta.outsourcing.config.PasswordEncoder;
import com.sparta.outsourcing.entity.User;
import com.sparta.outsourcing.dto.user.SignUpRequestDto;
import com.sparta.outsourcing.dto.user.SignUpResponseDto;
import com.sparta.outsourcing.exception.DeactivatedException;
import com.sparta.outsourcing.exception.DuplicatedEmailException;
import com.sparta.outsourcing.exception.ErrorCode;
import com.sparta.outsourcing.exception.PasswordIncorrectException;
import com.sparta.outsourcing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        //중복된 사용자 아이디로 가입하는 경우

        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new DuplicatedEmailException(ErrorCode.DUPLICATED_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        User user = new User(signUpRequestDto.getEmail(),
                encodedPassword,
                signUpRequestDto.getNickname(),
                signUpRequestDto.getAddress(),
                signUpRequestDto.getPhone(),
                signUpRequestDto.getRole());
        User saveUser = userRepository.save(user);

        return SignUpResponseDto.toDto(saveUser);

    }

    public User login(String email, String password) {
        User findUser = userRepository.findByUserOrElseThrow(email);

        // 탈퇴한 회원 로그인 예외처리
        if (Role.NOT_USE.equals(findUser.getRole())) {
            throw new DeactivatedException(ErrorCode.DEACTIVATED_USER);
        }

        if (!passwordEncoder.matches(password, findUser.getPassword())) {
            throw new PasswordIncorrectException(ErrorCode.PASSWORD_INCORRECT);
        }

        return findUser;
    }


}
