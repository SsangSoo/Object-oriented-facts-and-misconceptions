package book.objectorientedfactsandmisconceptions.pojo.responsibility;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;

import java.time.LocalDate;
import java.util.List;

/**
 * 키오스크의 책임
 */
public interface KioskResponsibility {

    /**
     * 앱(=키오스크)은 손님에게 커피 주문을 받을 수 있다.
     *     - 회원으로 주문여부를 결정할 수 있다.
     *       - 회원이 아니라면, 회원으로 저장이 된 후 결제 프로세스가 진행된다.
     *     - 어떤 커피를, 몇 잔 주문할지에 대한 정보를 받을 수 있다.
     *     - 쿠폰사용 여부를 받을 수 있다.
     *       - 단 쿠폰이 없는데, 사용하려고 하면 자동으로 계상되어 스탬프로 적립된다.
     * @param 주문 정보
     * @param 회원으로 주문 여부
     * @param 쿠폰으로 주문 여부
     * @return
     */
    List<Coffee> orderCoffee(List<OrderInfo> orderInfo, boolean orderAsMember, boolean orderWithCoupon);

    /**
     * 구매 내역을 보여줄 책임
     * - 고객이 앱(=키오스크)을 통해 구매 내역을 확인하려고 하면 구매내역을 보여줄 책임이 있다.
     *  - 일 / 월 / 년 별로 확인할 수 있다.
     */

    /**
     * 내역(년)
     * @param year  : 년
     * @param id    : 고객 id
     * @return
     */
    HistoryElement historyPurchaseOfYear(int year, Long id);

    /**
     * 내역(월)
     * @param year  : 년
     * @param month : 월
     * @param id    : 고객 id
     * @return
     */
    HistoryElement historyPurchaseOfMonth(int year, int month, Long id);

    /**
     * 내역(일)
     * @param date  : 일
     * @param id    : 고객 id
     * @return
     */
    HistoryElement historyPurchaseOfDay(LocalDate date, Long id);


    /**
     * 판매 내역을 보여줄 책임
     * - 커피샵은 앱(=키오스크)를 통해 판매 내역을 확인하려고 하면 판매 내역을 보여줄 책임이 있다.
     *  - 일 / 월 / 년 별로 확인할 수 있다.
     */

    /**
     * 내역(년)
     * @param year  : 년
     * @return
     */
    HistoryElement historySalesOfYear(int year);

    /**
     * 내역(월)
     * @param year  : 년
     * @param month : 월
     * @return
     */
    HistoryElement historySalesOfMonth(int year, int month);

    /**
     * 내역(일)
     * @param date  : 일
     * @return
     */
    HistoryElement historySalesOfDay(LocalDate date);
}