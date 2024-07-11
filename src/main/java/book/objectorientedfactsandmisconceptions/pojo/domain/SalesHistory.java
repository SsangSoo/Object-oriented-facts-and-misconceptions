package book.objectorientedfactsandmisconceptions.pojo.domain;

import book.objectorientedfactsandmisconceptions.pojo.domain.usecase.SalesHistoryUsecase;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Getter
public class SalesHistory implements SalesHistoryUsecase {

    private String customer;
    private LocalDate orderDate;
    private int totalPrice;

    private SalesHistory(String customer, LocalDate orderDate) {
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public static OrderHistory of(String customer, LocalDate orderDate, int totalPrice) {
        return new OrderHistory(customer, orderDate);
    }

    public void addPrice(int price) {
        this.totalPrice += price;
    }

    @Override
    public void addSellHistory(Order[] orders) {

    }

    @Override
    public List<OrderHistory> getSellHistory() {
        return List.of();
    }
}
