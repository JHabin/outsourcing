package com.sparta.outsourcing.service.user;

import com.sparta.outsourcing.config.PasswordEncoder;
import com.sparta.outsourcing.dto.user.LoginRequestDto;
import com.sparta.outsourcing.dto.user.OwnerResponseDto;
import com.sparta.outsourcing.dto.user.UserResponseDto;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.repository.StoreRepository;
import com.sparta.outsourcing.entity.User;

import com.sparta.outsourcing.common.Authentication;
import com.sparta.outsourcing.common.Role;
import com.sparta.outsourcing.dto.user.SignUpRequestDto;
import com.sparta.outsourcing.exception.UserException;
import com.sparta.outsourcing.exception.ErrorCode;
import com.sparta.outsourcing.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StoreRepository storeRepository;

    /**
     * 회원가입 requestDto(이메일,전화번호, 주소, 권한 등)를 받음
     * 저장된 회원 정보를 responseDto에 저장하며 return
     **/
    @Transactional
    public UserResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        //중복된 사용자 아이디로 가입하는 경우 에러 "중복된 아이디입니다." 출력하며 예외 처리
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new UserException(ErrorCode.DUPLICATED_EMAIL);
        }
        // requestDto에서 회원의 비밀번호 값을 받아 암호화, 로그인할 때에는 입력된 비밀번호를 암호화된 값과 비교하는 방식
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        // 암호화된 비밀번호 포함 회원 정보를 User 객체에 저장
        User user = new User(signUpRequestDto.getEmail(),
                encodedPassword,
                signUpRequestDto.getNickname(),
                signUpRequestDto.getAddress(),
                signUpRequestDto.getPhone(),
                Role.of(signUpRequestDto.getRole())
        );

        // savedUser 객체에 해당 정보 저장
        User savedUser = userRepository.save(user);

        //ResponseDto 형태로 해당 객체 반환
        return new UserResponseDto(savedUser);
    }

    /**
     * 이메일, 비밀번호를 가진 requestDto 로 로그인 메서드 구현
     * 로그인 성공 시 Authentication(이메일, 권한 정보 포함) 객체로 반환
     **/
    public Authentication login(LoginRequestDto loginRequestDto) throws UserException {

        /**
         * if 조건문 보다는 OrElseThrows 쓰기 - 1. Service에서 람다식, 2. Repository에서 default 메서드
         * OrElseThrows를 했다면 Service
         * Repository에 했다면 Repository 를 주입받아야 함.
         -> 메서드 재사용성, 코드 간결화 good
         **/
        // 이메일 정보로 회원을 조회해서 조회된 사용자 findUser에 저장
        User findUser = userRepository.findByEmailOrElseThrow(loginRequestDto.getEmail());

        //삭제된 사용자일 경우 로그인 안 되도록 구현
        if (findUser.isDeleted()) {
            throw new UserException(ErrorCode.DEACTIVATED_USER);
        }
        // 조회된 회원의 비밀번호와 입력한 비밀번호 두 object를 비교해서 일치하지 않는 경우 예외 처리, 굳이 else if 안 써도 됨.
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), findUser.getPassword())) {
            throw new UserException(ErrorCode.PASSWORD_INCORRECT);
        }
        // 로그인 성공 시, 해당 회원의 이메일과 권한 정보를 가진 authentication 반환
        return new Authentication(
                findUser.getEmail(),
                findUser.getRole()
        );

    }

    /**
     * user가 아닐 경우 interceptro에 의애 예외 처리됨
     *
     * @param userId
     * @return user responseDto
     */
    public Object findUserById(Long userId) {
        // user id 조회
        User user = userRepository.findByIdOrElseThrow(userId);

        // UserResponseDto 형태 반환
        return new UserResponseDto(user);

    }
    /**
     * owner가 아닐 경우 interceptor에 의해 예외 처리됨
     * @param ownerId
     * @return owner responseDto
     */
    public Object findOwnerById(Long ownerId) {
        // user id 조회
        User owner = userRepository.findByIdOrElseThrow(ownerId);

        // 점주가 가진 가게 수 계산
        Long storeCount = storeRepository.countStoresById(owner.getId());
        // 점주의 Id와 일치하는 가게를 모두 가져와 가게 리스트에 저장
        List<Store> storeList = storeRepository.findAllById(owner.getId());
        // 가게 정보 (id, 이름) 리스트 생성
        List<OwnerResponseDto.StoreInfo> storeDetails = storeList.stream()
                .map(store -> new OwnerResponseDto.StoreInfo(
                        store.getId(),
                        store.getName(),
                        store.getOpenTime(),
                        store.getCloseTime(),
                        store.getMinOrderPrice()
                ))
                .toList();
        // 위의 리스트들을 포함하는 OwnerResponseDto 반환
        return new OwnerResponseDto(
                owner.getEmail(),
                owner.getNickname(),
                owner.getAddress(),
                owner.getPhone(),
                owner.getRole(),
                owner.getCreatedAt(),
                storeCount,
                storeDetails
        );

    }


    // 비활성화할 사용자의 email 와 비밀번호를 가지고 비활성화
    public void deleteUserByEmail(String email, String password) {
        // email로 사용자를 찾는다. 못 찾으면 예외 처리
        User findUser = userRepository.findByEmailOrElseThrow(email);

        // 조회된 회원의 비밀번호와 입력한 비밀번호 두 object를 비교해서 일치하지 않는 경우 예외 처리, 굳이 else if 안 써도 됨.
        if (!passwordEncoder.matches(password, findUser.getPassword())) {
            throw new UserException(ErrorCode.PASSWORD_INCORRECT);
        }

        // 삭제일시를 저장하는 부분 구현, soft delete
        findUser.softDelete();

        // 변경 내용(soft delete 내용) 저장
        userRepository.save(findUser);
    }

}
