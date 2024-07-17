package book.objectorientedfactsandmisconceptions.pojo.usecase;

import book.objectorientedfactsandmisconceptions.pojo.domain.customer.OrderHistory;
import book.objectorientedfactsandmisconceptions.pojo.domain.order.Order;

import java.time.LocalDate;
import java.util.List;

/**
 * 책임 1. 주문내역을 보여줄 수 있어야 한다.
 *  - 년, 월, 일로 주문내역을 보여줄 수 있어야 한다.
 * 책임 2. 커피 주문시 주문 내역에 추가되어야 한다.
 *  - 총계, 어떤 커피가 얼마나 담겼는지
 */
public interface OrderHistoryUsecase {

    // 책임 1
    List<OrderHistory> getOrderHistoryByDay(LocalDate date);
    List<OrderHistory> getOrderHistoryByMonth(int month);
    List<OrderHistory> getOrderHistoryByYear(int year);

    // 책임 2
    void addOrderHistory(List<Order> orders);

}
