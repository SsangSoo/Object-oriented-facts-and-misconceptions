package book.objectorientedfactsandmisconceptions.pojo.history.element;

import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

/**
 * 하루 내역
 *  - 일의 내역을 확인하는데 필요
 */
@Getter
public class HistoryElementOfDay {

    private final LocalTime orderTime;
    private final List<OrderItem> orderItem;
    private final int totalPriceOfDay;

    public HistoryElementOfDay(LocalTime orderTime, List<OrderItem> orderItem) {
        this.orderTime = orderTime;
        this.orderItem = orderItem;
        this.totalPriceOfDay = orderItem.stream()
                .map(oi -> oi.getTotalPrice())
                .reduce(0, Integer::sum);
    }
}
