package book.objectorientedfactsandmisconceptions.pojo.exception;

import lombok.Getter;

@Getter
public enum BusinessException {

    IMPOSSIBLE_CANCEL("스탬프 취소가 불가능한 상태입니다.");

    String message;

    BusinessException(String message) {
        this.message = message;
    }
}
