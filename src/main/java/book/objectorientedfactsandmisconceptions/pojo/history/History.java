package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;
import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElementOfDay;
import lombok.Getter;

import java.util.List;

/**
 * 판매 내역
 */
@Getter
public class History {

    private final List<HistoryElement> histories;
    private final List<HistoryElementOfDay> historiesOfDay;
    private final int totalPrice;

    History(List<HistoryElement> histories, List<HistoryElementOfDay> historiesOfDay) {
        this.histories = histories;
        this.historiesOfDay = historiesOfDay;
        this.totalPrice = histories != null ? calculateTotalPrice(histories) : calculateTotalPriceOfDay(historiesOfDay);
    }

    public static History ofDay(List<HistoryElementOfDay> historiesOfDay) {
        return new History(null, historiesOfDay);
    }


    public static History of(List<HistoryElement> histories) {
        return new History(histories, null);
    }

    private int calculateTotalPrice(List<HistoryElement> histories) {
        return histories.stream()
                .map(history -> history.getTotalPrice())
                .reduce(0, Integer::sum);
    }

    private int calculateTotalPriceOfDay(List<HistoryElementOfDay> historiesOfDay) {
        return historiesOfDay.stream()
                .map(history -> history.getTotalPriceOfDay())
                .reduce(0, Integer::sum);
    }

}
