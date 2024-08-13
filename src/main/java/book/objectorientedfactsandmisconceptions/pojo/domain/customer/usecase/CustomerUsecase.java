package book.objectorientedfactsandmisconceptions.pojo.domain.customer.usecase;

import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.customer.Customer;
import book.objectorientedfactsandmisconceptions.pojo.domain.history.PaymentHistory;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.CoffeeOrder;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * 손님 요구사항
 * - 메뉴를 확인할 수 있다.
 * - 구매 내역을 일 / 월 / 년 별로 확인할 수 있다.
 *   - 언제, 어떤 바리스타, 어떤 커피, 수량, 주문 별 금액, 총계 
 *     - 일 : 시간, 커피/수량, 주문 총계(가격)
 *     - 월 : 몇월 몇일, 커피/수량, 일별 주문 합계(가격), 월 총계(가격)
 *     - 년 : 몇월 커피/수량, 월별 주문합계(가격) / 년 총계(가격)
 * - 커피를 주문할 수 있다.
 * - 바리스타를 지정할 수 있다.(바리스타 이름)
 * - 어떤 커피를 몇 잔 주문할지 지정할 수 있다.
 * 
 * 
 * 손님의 책임
 * 1. 등록(생성)될 책임
 * 2. 회원 정보를 변경힐 첵임
 * 3. 주문 내역을 확인할 책임
 * 4. 커피 주문 책임
 *    4-1. 확장 : 포장/주문 구분
 * 5. 메뉴를 확인할 책임
 */
public interface CustomerUsecase {

    /**
     * 손님 등록의 책임
     *
     * @param name : 손님
     * @return : 손님
     */
    static Customer createCustomer(String name) {
        return Customer.of(name);
    }

    /**
     * 메뉴를 확인할 수 있다.
     * @return 커피 메뉴
     */
    Menu[] getCoffeeMenu();

    /**
     * 커피를 주문할 수 있다.
     * @param coffeeOrders  : 커피 주문 내역
     * @param barista       : 바리스타 이름
     * @param orderDate     : 주문 일자(기본 LocalDate.now())
     * @return              : 주문한 커피리스트
     */
    List<Coffee> orderCoffee(List<CoffeeOrder> coffeeOrders, String barista, LocalDate orderDate);

    /**
     * 구매내역을 년 별로 확인한다.
     * @param year  : 구매내역 확인할 년도
     * @return
     */
    PaymentHistory getPaymentHistoryAtYear(int year);

    /**
     * 구매내역을 월 별로 확인한다.
     * @param year  : 구매내역 확인 년도
     * @param month : 해당 년도의 월
     * @return
     */
    PaymentHistory getPaymentHistoryAtMonth(int year, int month);

    /**
     * 구매내역을 일 별로 확인한다.
     * @param date  : 구매내역 확인 일
     * @return
     */
    PaymentHistory getPaymentHistoryAtDay(LocalDate date);
}
