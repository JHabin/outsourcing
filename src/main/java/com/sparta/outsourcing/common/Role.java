package com.sparta.outsourcing.common;

// NOT_USE 말고 삭제 시간으로 하는 게 나음
public enum Role {
    OWNER("OWNER"), USER("USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    // getName 메서드
    public String getName() {
        return name;
    }

    public static Role of(String roleName) throws IllegalArgumentException {
        for (Role role : values()) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }

        throw new IllegalArgumentException("해당하는 이름의 권한을 찾을 수 없습니다: " + roleName);
    }
}
