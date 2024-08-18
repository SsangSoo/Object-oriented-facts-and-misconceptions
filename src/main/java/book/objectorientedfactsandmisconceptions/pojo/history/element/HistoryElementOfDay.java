package book.objectorientedfactsandmisconceptions.pojo.history.element;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
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
    private final List<OrderInfo> orderInfo;
    private final int totalPriceOfDay;

    public HistoryElementOfDay(LocalTime orderTime, List<OrderInfo> orderInfo) {
        this.orderTime = orderTime;
        this.orderInfo = orderInfo;
        this.totalPriceOfDay = orderInfo.stream()
                .map(oi -> oi.getPrice())
                .reduce(0, Integer::sum);
    }
}
