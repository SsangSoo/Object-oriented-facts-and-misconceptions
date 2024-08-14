package book.objectorientedfactsandmisconceptions.pojo.history;


import book.objectorientedfactsandmisconceptions.pojo.coffee.CoffeeOrder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 책임 1. 주문내역을 보여줄 수 있어야 한다.
 *      일별로 주문한 커피와 개수, 총계를 담는다.
 *
 */
@Getter
public class OrderHistory {

    private LocalDate orderedDate;
    private List<CoffeeOrder> coffeeOrderList = new ArrayList<>();
    private int totalPrice;

    public static OrderHistory of(List<CoffeeOrder> coffeeOrders, LocalDate orderedDate) {
        return new OrderHistory(coffeeOrders, orderedDate);
    }

    public static OrderHistory of(List<CoffeeOrder> coffeeOrders) {
        return new OrderHistory(coffeeOrders);
    }

    private OrderHistory(List<CoffeeOrder> coffeeOrders, LocalDate orderedDate) {
        this.orderedDate = orderedDate;
        this.coffeeOrderList = coffeeOrders;
        this.totalPrice = calculateTotalPrice(coffeeOrders);
    }

    private OrderHistory(List<CoffeeOrder> coffeeOrders) {
        this(coffeeOrders, LocalDate.now());
    }

    // 주문에 대한 총계
    private int calculateTotalPrice(List<CoffeeOrder> coffeeOrders) {
        for(CoffeeOrder coffeeOrder : coffeeOrders) {
            this.totalPrice += coffeeOrder.getMenu().getPrice() * coffeeOrder.getCount();
        }
        return this.totalPrice;
    }

}
