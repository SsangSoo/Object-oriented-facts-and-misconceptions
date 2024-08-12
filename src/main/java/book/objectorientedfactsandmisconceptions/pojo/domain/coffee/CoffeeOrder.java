package book.objectorientedfactsandmisconceptions.pojo.domain.coffee;

import lombok.Getter;

/**
 * 주문 정보를 담기 위한 객체
 * 책임 1. 주문 정보를 담는다.
 */
@Getter
public class CoffeeOrder implements CoffeeOrderUsecase {

    private Menu menu;
    private int count;

    private CoffeeOrder(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public static CoffeeOrder of(Menu coffee, int count) {
        return addCoffeeToCart(coffee, count);
    }

    public static CoffeeOrder addCoffeeToCart(Menu coffee, int count) {
        return new CoffeeOrder(Menu.valueOf(coffee.getCoffee()), count);
    }


}
