package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.coffee.CoffeeOrder;

import java.util.List;

/**
 * 판매/구매 내역 리스트의 책임
 * 1. 판매/구매 내역들을 담을 책임
 * 2. 손님/바리스타의 판매/구매내역리스트 / 총계를 알려줄 책임
 */
public interface HistoryListInterface {

    /**
     * 판매/구매 내역들을 담을 책임
     * @param coffeeOrder   : 커피 주문
     */
    void addHistory(CoffeeOrder coffeeOrder);

    /**
     * 판매/구매 내역들을 보여줄 책임
     * @param year  : 년
     * @param month : 월
     * @param day   : 일
     * @return      : 내역 리스트
     */
    List<HistoryInterface> getHistoryList(Integer year, Integer month, Integer day);
}
