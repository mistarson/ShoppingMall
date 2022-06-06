package myproject.shoppingmall.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 회원
    ALREADY_REGISTERED_MEMBER(400, "이미 가입된 회원 입니다.");

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;
}
