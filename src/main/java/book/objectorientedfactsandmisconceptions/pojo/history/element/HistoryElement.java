package book.objectorientedfactsandmisconceptions.pojo.history.element;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import lombok.Getter;

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

    public HistoryElement(List<OrderItem> orderItem, Integer totalPrice) {
        this.date = LocalDateTime.now();
        this.orderItem = orderItem;
        this.totalPrice = totalPrice;
    }

    public HistoryElement(List<OrderItem> orderItem) {
        this.date = LocalDateTime.now();
        this.orderItem = orderItem;
        this.totalPrice = calculateTotalPrice(orderItem);
    }

    private static Integer calculateTotalPrice(List<OrderItem> orderItem) {
        return orderItem.stream()
                .map(o -> o.getQuantity() * Menu.of(o.getCoffee()).getPrice())
                .reduce(0, Integer::sum);
    }
}
