package book.objectorientedfactsandmisconceptions.pojo.domain.customer;

import java.util.List;

/**
 * 년, 월, 일 별로 불러모은 결제 내역을 모아 보여준다.
 */
public class PaymentHistory {

    List<OrderHistory> orderHistoryList;

    private PaymentHistory(List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }

    public PaymentHistory from(List<OrderHistory> orderHistoryList) {
        return new PaymentHistory(orderHistoryList);
    }

}
