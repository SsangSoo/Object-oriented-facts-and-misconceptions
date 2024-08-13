package book.objectorientedfactsandmisconceptions.pojo.domain.coffee;

import lombok.Getter;

/**
 * 메뉴판 enum
 */
@Getter
public enum Menu {

    AMERICANO("아메리카노", 4000),
    ESSPRESSO("에스프레소", 4000),
    CAFELATTE("카페라떼", 4500);


    private String coffee;
    private int price;

    Menu(String coffee, int price) {
        this.coffee = coffee;
        this.price = price;
    }

}
