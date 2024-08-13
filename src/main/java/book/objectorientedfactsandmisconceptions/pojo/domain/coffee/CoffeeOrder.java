package book.objectorientedfactsandmisconceptions.pojo.domain.coffee;

import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.usecase.CoffeeOrderUsecase;
import lombok.Getter;


@Getter
public class CoffeeOrder implements CoffeeOrderUsecase {

    private Menu menu;
    private int count;

    private CoffeeOrder(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    @Override
    public CoffeeOrder addOrder(String menu, int count) {
        return new CoffeeOrder(Menu.valueOf(menu), count);
    }

    @Override
    public CoffeeOrder addOrder(Menu menu, int count) {
        return new CoffeeOrder(menu, count);
    }


}
