package myproject.shoppingmall.global.error.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    public BusinessException(ErrorCode errorCode){super(errorCode.getMessage());}
}
