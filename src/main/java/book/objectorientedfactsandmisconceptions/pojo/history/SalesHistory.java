package book.objectorientedfactsandmisconceptions.pojo.history;

import lombok.Getter;

import java.util.List;

/**
 * 판매 내역
 */
@Getter
public class SalesHistory {

    private final List<History> histories;
    private final int totalPrice;

    SalesHistory(List<History> histories) {
        this.histories = histories;
        this.totalPrice = calculateTotalPrice(histories);
    }

    public static SalesHistory of(List<History> histories) {
        return new SalesHistory(histories);
    }

    private int calculateTotalPrice(List<History> histories) {
        return histories.stream()
                .map(History::getTotalPrice)
                .reduce(0, Integer::sum);
    }

}
