package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;
import lombok.Getter;

import java.util.List;

/**
 * 판매 내역
 */
@Getter
public class History {

    private final List<HistoryElement> histories;
    private final int totalPrice;

    History(List<HistoryElement> histories) {
        this.histories = histories;
        this.totalPrice = calculateTotalPrice(histories);
    }

    public static History of(List<HistoryElement> histories) {
        return new History(histories);
    }

    private int calculateTotalPrice(List<HistoryElement> histories) {
        return histories.stream()
                .map(history -> history.getTotalPrice())
                .reduce(0, Integer::sum);
    }

}
