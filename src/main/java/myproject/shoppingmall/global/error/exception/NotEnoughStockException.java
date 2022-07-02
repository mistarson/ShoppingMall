package myproject.shoppingmall.global.error.exception;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(ErrorCode errorCode){super(errorCode.getMessage());}
}
