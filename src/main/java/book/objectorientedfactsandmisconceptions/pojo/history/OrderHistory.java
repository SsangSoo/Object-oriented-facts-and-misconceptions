package book.objectorientedfactsandmisconceptions.pojo.history;


import book.objectorientedfactsandmisconceptions.pojo.coffee.CoffeeOrder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * 단건 조회
 *
 */
@Getter
public class OrderHistory {

    private final LocalDate orderedDate;
    private final List<CoffeeOrder> coffeeOrderList;
    private final int totalPrice;

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
        int result = 0;
        for(CoffeeOrder coffeeOrder : coffeeOrders) {
            result += coffeeOrder.getMenu().getPrice() * coffeeOrder.getCount();
        }
        return result;
    }

}
