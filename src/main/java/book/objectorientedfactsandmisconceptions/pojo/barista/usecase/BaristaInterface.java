package book.objectorientedfactsandmisconceptions.pojo.barista.usecase;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.coffee.CoffeeOrder;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * 바리스타의 요구사항
 * - 판매 내역을 일 / 월 / 년 별로 확인할 수 있다.
 *    - 언제 어떤 커피, 수량, 총 매출
 *      - 일 : 시간, 커피/ 수량, 매출 총계(가격)
 *      - 월 : 일별, 커피/수량 일별 매출 합계(가격), 월 총계(가격)
 *      - 년 : 월별 커피/수량, 월별 매출 합계(가격), 년 총계(가격)
 * - 커피를 제조할 수 있다.
 *
 *
 * 바리스타의 책임
 *  1. 판매 내역을 일 / 월 / 년 별로 확인할 책임
 *      - 언제 어떤 커피, 수량, 총 매출
 *          - 일 : 시간, 커피/ 수량, 매출 총계(가격)를 확인할 책임
 *          - 월 : 일별, 커피/수량 일별 매출 합계(가격), 월 총계(가격)를 확인할 책임
 *          - 년 : 월별 커피/수량, 월별 매출 합계(가격), 년 총계(가격)를 확인할 책임
 *  2. 커피를 제조할 책임.
 *      - 한잔
 *      - 여러 잔
 *
 */
public interface BaristaInterface {

    List<Coffee> makeCoffee(List<CoffeeOrder> coffeeOrders, LocalDate orderDate);

    // 2. 판매내역을 날짜별로 확인할 수 있다.



}
