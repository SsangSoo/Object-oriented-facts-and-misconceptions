package book.objectorientedfactsandmisconceptions.pojo.exception;

import lombok.Getter;

@Getter
public enum BusinessException {

    IMPOSSIBLE_CANCEL("스탬프 취소가 불가능한 상태입니다.", 400),
    IMPOSSIBLE_ORDER_BY_CUSTOMER("회원으로의 주문시 핸드폰 번호가 필요합니다.", 404),
    CANNOT_FOUND_CUSTOMER("회원 정보를 찾을 수 없습니다.", 404);

    String message;
    int statusCode;

    BusinessException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
