package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;
import lombok.Getter;

import java.util.List;

/**
 * 고객의 구매 내역
 */
@Getter
public class HistoryPurchase extends History {

    private final String customer;

    private HistoryPurchase(String customer, List<HistoryElement> histories) {
        super(histories);
        this.customer = customer;
    }

    public static HistoryPurchase of(String customer, List<HistoryElement> histories) {
        return new HistoryPurchase(customer, histories);
    }

}
