package book.objectorientedfactsandmisconceptions.pojo.history.element;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 내역
 *  월, 년 내역 조회시 필요
 */
@Getter
public class HistoryElement {

    private final LocalDateTime date;
    private final List<OrderItem> orderItem;
    private final int totalPrice;

    public HistoryElement(LocalDateTime date, List<OrderItem> orderItem) {
        this.date = date;
        this.orderItem = orderItem;
        this.totalPrice = orderItem.stream()
                .map(o -> o.getQuantity() * Menu.of(o.getCoffee()).getPrice())
                .reduce(0, Integer::sum);
    }
}
