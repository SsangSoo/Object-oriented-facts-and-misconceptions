package book.objectorientedfactsandmisconceptions.domain;

import lombok.Getter;

@Getter
public enum Menu {

    AMERICANO("아메리카노", 4000),
    ESSPRESSO("에스프레소", 4000),
    CAFELATTE("카페라떼", 4500);


    private String name;
    private int price;

    Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

}
