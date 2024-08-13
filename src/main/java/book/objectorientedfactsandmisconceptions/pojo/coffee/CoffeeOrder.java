package book.objectorientedfactsandmisconceptions.pojo.coffee;

import lombok.Getter;


@Getter
public class CoffeeOrder {

    private Menu menu;
    private int count;

    private CoffeeOrder(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public static CoffeeOrder of(String menu, int count) {
        return new CoffeeOrder(Menu.of(menu), count);
    }

    public static CoffeeOrder of(Menu menu, int count) {
        return new CoffeeOrder(menu, count);
    }



}
