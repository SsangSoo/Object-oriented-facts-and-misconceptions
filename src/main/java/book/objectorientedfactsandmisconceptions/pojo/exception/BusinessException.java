package book.objectorientedfactsandmisconceptions.pojo.exception;

import lombok.Getter;

@Getter
public enum BusinessException {

    IMPOSSIBLE_CANCEL("스탬프 취소가 불가능한 상태입니다."),
    IMPOSSIBLE_ORDER_BY_CUSTOMER("회원으로의 주문시 핸드폰 번호가 필요합니다.");

    String message;

    BusinessException(String message) {
        this.message = message;
    }
}
