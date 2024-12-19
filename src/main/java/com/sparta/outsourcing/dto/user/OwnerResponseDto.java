package com.sparta.outsourcing.dto.user;

import com.sparta.outsourcing.common.Role;
import com.sparta.outsourcing.common.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class OwnerResponseDto {

    private final String nickname;

    private final String password;

    private final String email;

    private final String phone;

    private final Role role;

    private final LocalDateTime createdAt;

    private final Long storeNum;

    private final List<StoreDetail> stores;

    @Getter
    @RequiredArgsConstructor
    public static class StoreDetail {
        private final Long storeId;
        private final String storeName;
    }
}
