package book.objectorientedfactsandmisconceptions.pojo.exception;

import lombok.Getter;

@Getter
public enum BusinessException {

    IMPOSSIBLE_CANCEL("스탬프 취소가 불가능한 상태입니다.", 400),
    IMPOSSIBLE_ORDER_BY_CUSTOMER("회원으로의 주문시 핸드폰 번호가 필요합니다.", 404),
    CANNOT_USE_COUPON("쿠폰을 사용할 수 없습니다.", 404),
    CANNOT_FOUND_CUSTOMER("회원 정보를 찾을 수 없습니다.", 404),
    INVALID_STATE("쿠폰 사용시 적용할 쿠폰 개수를 올바로 적어주세요.", 400),
    INVALID_COFFEE("존재하지 않는 메뉴입니다.", 400);

    String message;
    int statusCode;

    BusinessException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
