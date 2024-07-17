package book.objectorientedfactsandmisconceptions.pojo.domain.customer;


import book.objectorientedfactsandmisconceptions.pojo.domain.order.Order;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 책임 1. 주문내역을 보여줄 수 있어야 한다.
 *  - 년, 월, 일로 주문내역을 보여줄 수 있어야 한다.
 * 책임 2. 커피 주문시 주문 내역에 추가되어야 한다.
 *  - 총계, 어떤 커피가 얼마나 담겼는지
 */
@Getter
public class OrderHistory {

    private LocalDate orderedDate;
    private List<Order> orderList = new ArrayList<>();
    private int totalPrice;

    public static OrderHistory of(Order[] orders) {
        return new OrderHistory(orders);
    }

    private OrderHistory(Order[] orders) {
        this.orderedDate = LocalDate.now();
        this.orderList = List.of(orders);
        this.totalPrice = calculateTotalPrice(List.of(orders));
    }

    private OrderHistory(List<Order> orders) {
        this.orderedDate = LocalDate.now();
        this.orderList = orders;
        this.totalPrice = calculateTotalPrice(orders);
    }

    // 주문에 대한 총계
    private int calculateTotalPrice(List<Order> orders) {
        int totalPrice = 0;
        for(Order order : orders) {
            totalPrice = order.getMenu().getPrice() * order.getCount();
        }
        return totalPrice;
    }

}
