package book.objectorientedfactsandmisconceptions.pojo.responsebillity;

import book.objectorientedfactsandmisconceptions.pojo.domain.global.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.global.CoffeeOrder;

import java.time.LocalDate;
import java.util.List;

/**
 * 바리스타의 책임
 * 책임 1. 주문 받은 커피를 제조한다.
 *      커피를 제조하면서 받은 커피 내역을 판매 내역에 저장한다.
 * 책임 2. 판매내역을 확인할 수 있다.
 *      년, 월, 일 별로 판매 내역을 확인할 수 있다.
 *
 */
public interface BaristaResponsibillity {

    // 1. 커피를 제조한다.
    List<Coffee> makeCoffee(List<CoffeeOrder> coffeeOrders, LocalDate orderDate);

    // 2. 판매내역을 날짜별로 확인할 수 있다.



}
