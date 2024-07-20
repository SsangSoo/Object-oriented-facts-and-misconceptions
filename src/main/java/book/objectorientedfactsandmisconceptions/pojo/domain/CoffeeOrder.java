package book.objectorientedfactsandmisconceptions.pojo.domain;

import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Menu;
import lombok.Getter;

/**
 * 주문 정보를 담기 위한 객체
 */
@Getter
public class CoffeeOrder {

    private Menu menu;
    private int count;

    private CoffeeOrder(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public static CoffeeOrder of(String coffee, int count) {
        String makeCoffee = getMakeCoffeeMenu(coffee);
        return new CoffeeOrder(Menu.valueOf(makeCoffee), count);
    }

    private static String getMakeCoffeeMenu(String coffee) {
        String makeCoffee = "AMERICANO";
        for(Menu menu : Menu.values()) {
            if(menu.getCoffee().equals(coffee)) {
                makeCoffee = menu.name();
            }
        }
        return makeCoffee;
    }
}
