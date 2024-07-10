package book.objectorientedfactsandmisconceptions.domain.usecase;

import book.objectorientedfactsandmisconceptions.domain.Coffee;
import book.objectorientedfactsandmisconceptions.domain.OrderHistory;

import java.util.List;

/**
 * 주문 내역의 책임
 * 1. 주문내역을 담을 수 있다.
 * 2. 주문내역을 확인할 수 있다.
 */
public interface OrderHistoryUsecase {

    // 1. 주문내역을 담을 수 있다.
    void addOrderHistory(List<Coffee> coffees);




    // 2. 주문내역을 확인할 수 있다.
    List<OrderHistory> getOrderHistory(String customerName);



}
