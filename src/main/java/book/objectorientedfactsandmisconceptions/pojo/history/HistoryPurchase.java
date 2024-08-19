package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;
import lombok.Getter;

import java.util.List;

/**
 * 고객의 구매 내역
 */
@Getter
public class HistoryPurchase {

    private final String customer;
    private final List<HistoryElement> histories;
    private final int totalPrice;

    HistoryPurchase(String customer, List<HistoryElement> histories) {
        this.customer = customer;
        this.histories = histories;
        this.totalPrice = calculateTotalPrice(histories);
    }

    public static HistoryPurchase of(String customer, List<HistoryElement> histories) {
        return new HistoryPurchase(customer, histories);
    }

    private int calculateTotalPrice(List<HistoryElement> histories) {
        return histories.stream()
                .map(history -> history.getTotalPrice())
                .reduce(0, Integer::sum);
    }

}
