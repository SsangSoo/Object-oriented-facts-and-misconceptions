package book.objectorientedfactsandmisconceptions.pojo.domain.customer;


import book.objectorientedfactsandmisconceptions.pojo.domain.order.Order;
import book.objectorientedfactsandmisconceptions.pojo.usecase.OrderHistoryUsecase;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
public class OrderHistory implements OrderHistoryUsecase {

    private LocalDate orderDate;
    private List<Order> orders = new ArrayList<>();
    private int totalPrice;

    public OrderHistory(List<Order> orders) {
        this.orderDate = LocalDate.now();
        this.orders = orders;

    }

    @Override
    public List<OrderHistory> getOrderHistoryByDay(LocalDate date) {
        return List.of();
    }

    @Override
    public List<OrderHistory> getOrderHistoryByMonth(int month) {
        return List.of();
    }

    @Override
    public List<OrderHistory> getOrderHistoryByYear(int year) {
        return List.of();
    }

    @Override
    public void addOrderHistory(List<Order> orders) {

    }
}
