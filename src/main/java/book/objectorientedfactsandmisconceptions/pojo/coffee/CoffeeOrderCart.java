package book.objectorientedfactsandmisconceptions.pojo.coffee;

import lombok.Getter;

import java.util.List;

@Getter
public class CoffeeOrderCart {

    private List<CoffeeOrder> coffeeOrderList;

    public CoffeeOrderCart(CoffeeOrder... coffeeOrders) {
        this.coffeeOrderList = List.of(coffeeOrders);
    }

}
