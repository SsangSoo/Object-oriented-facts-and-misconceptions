package book.objectorientedfactsandmisconceptions.pojo.domain;

import lombok.Getter;

import java.util.List;

/**
 * 년, 월, 일 별로 불러모은 결제 내역을 모아 보여준다.
 */
@Getter
public class PaymentHistory {

    private List<OrderHistory> orderHistoryList;

    private PaymentHistory(List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }

    public static PaymentHistory of(List<OrderHistory> orderHistoryList) {
        return new PaymentHistory(orderHistoryList);
    }

}
