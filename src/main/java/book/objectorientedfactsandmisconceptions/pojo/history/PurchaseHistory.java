package book.objectorientedfactsandmisconceptions.pojo.history;

import lombok.Getter;

import java.util.List;

/**
 * 고객의 구매 내역
 *  고객이 구매 내역을 보려고 할 때 결과를 내준다.
 *  ResponseDto 의 역할
 */
@Getter
public class PurchaseHistory {

    private final String phone;
    private final List<History> histories;
    private final int totalPrice;

    private PurchaseHistory(String phone, List<History> histories) {
        this.phone = phone;
        this.histories = histories;
        this.totalPrice = calculateTotalPrice(histories);
    }

    public static PurchaseHistory of(String phone, List<History> histories) {
        return new PurchaseHistory(phone, histories);
    }

    private int calculateTotalPrice(List<History> histories) {
        return histories.stream()
                .map(History::getTotalPrice)
                .reduce(0, Integer::sum);
    }

}
