package book.objectorientedfactsandmisconceptions.pojo.domain.usecase;

import book.objectorientedfactsandmisconceptions.pojo.domain.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.Order;
import book.objectorientedfactsandmisconceptions.pojo.domain.OrderHistory;

import java.util.List;

/**
 * 판매 내역의 책임
 * 1. 판매내역을 담을 수 있다.
 * 2. 판매내역을 확인할 수 있다.
 */
public interface SalesHistoryUsecase {

    // 1. 판매내역을 담을 수 있다.
    void addSellHistory(Order[] orders);

    // 2. 판매내역을 확인할 수 있다.
    List<OrderHistory> getSellHistory();
}
