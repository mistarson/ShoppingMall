package myproject.shoppingmall.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 회원
    ALREADY_REGISTERED_MEMBER(400, "이미 가입된 회원 입니다."),
    NON_EXISTENT_MEMBER(400, "존재하지 않는 회원입니다."),

    // 상품
    REST_STOCK_NOT_EXISTS(400, "상품의 재고가 부족 합니다."),
    ALREADY_REGISTERED_ITEM(400, "이미 존재하는 아이템 입니다."),
    FIRST_ITEM_IMAGE_NOT_EXISTS(400, "첫번째 상품 이미지는 필수 입력 값 입니다.");

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private final int status;
    private final String message;
}
