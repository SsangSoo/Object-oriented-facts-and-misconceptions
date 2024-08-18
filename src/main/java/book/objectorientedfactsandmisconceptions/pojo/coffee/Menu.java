package book.objectorientedfactsandmisconceptions.pojo.coffee;

import lombok.Getter;

/**
 * 메뉴판 enum
 */
@Getter
public enum Menu {

    AMERICANO("아메리카노", 4000),
    ESPRESSO("에스프레소", 4000),
    CAFE_LATTE("카페라떼", 4500);


    private String name;
    private int price;

    Menu(String coffee, int price) {
        this.name = coffee;
        this.price = price;
    }

    public static Menu of(String coffee) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(coffee)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("Invalid coffee: " + coffee);
    }

}
