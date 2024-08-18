package book.objectorientedfactsandmisconceptions.pojo.history.element;

import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * 내역
 *  월, 년 내역 조회시 필요
 */
@Getter
public class HistoryElement {

    private final LocalDate date;
    private final List<OrderItem> orderItem;
    private final int totalPrice;

    public HistoryElement(LocalDate date, List<OrderItem> orderItem) {
        this.date = date;
        this.orderItem = orderItem;
        this.totalPrice = orderItem.stream()
                .map(o -> o.getTotalPrice())
                .reduce(0, Integer::sum);
    }
}
