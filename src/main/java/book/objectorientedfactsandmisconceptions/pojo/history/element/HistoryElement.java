package book.objectorientedfactsandmisconceptions.pojo.history.element;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
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
    private final List<OrderInfo> orderInfo;
    private final int totalPrice;

    public HistoryElement(LocalDate date, List<OrderInfo> orderInfo) {
        this.date = date;
        this.orderInfo = orderInfo;
        this.totalPrice = orderInfo.stream()
                .map(o -> o.getPrice())
                .reduce(0, Integer::sum);
    }
}
