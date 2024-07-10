package book.objectorientedfactsandmisconceptions.domain.usecase;

import book.objectorientedfactsandmisconceptions.domain.Coffee;
import book.objectorientedfactsandmisconceptions.domain.OrderHistory;

import java.util.List;

/**
 * 판매 내역의 책임
 * 1. 판매내역을 담을 수 있다.
 * 2. 판매내역을 확인할 수 있다.
 */
public interface SellHistoryUsecase {

    // 1. 판매내역을 담을 수 있다.
    void addSellHistory(List<Coffee> coffees);

    // 2. 판매내역을 확인할 수 있다.
    List<OrderHistory> getSellHistory();
}
