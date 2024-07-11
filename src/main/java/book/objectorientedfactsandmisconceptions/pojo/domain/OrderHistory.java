package book.objectorientedfactsandmisconceptions.pojo.domain;

import lombok.Getter;

import java.time.LocalDate;

/**
 * 주문내역
 * 책임 1. 고객의 주문 내역을 담을 수 있다.
 */
@Getter
public class OrderHistory {

    private String customer;
    private LocalDate orderDate;
    private int totalPrice;

    private OrderHistory(String customer, LocalDate orderDate) {
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public static OrderHistory of(String customer, LocalDate orderDate, int totalPrice) {
        return new OrderHistory(customer, orderDate);
    }

    public void addPrice(int price) {
        this.totalPrice += price;
    }
}

