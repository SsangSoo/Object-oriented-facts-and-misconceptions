package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 주문당 내역
 */
@Getter
public class History {

    private final LocalDateTime date;
    private final List<OrderItem> orderItem;
    private final int totalPrice;

    public History(LocalDateTime date, List<OrderItem> orderItem, Integer totalPrice) {
        this.date = Objects.isNull(date) ? LocalDateTime.now() : date;
        this.orderItem = orderItem;
        this.totalPrice = Objects.isNull(totalPrice) ? calculateTotalPrice(orderItem) : totalPrice;
    }

    public History(LocalDateTime date, List<OrderItem> orderItem) {
        this(date, orderItem, null);
    }


    public History(List<OrderItem> orderItem, Integer totalPrice) {
        this(null, orderItem, totalPrice);
    }

    public History(List<OrderItem> orderItem) {
        this(null, orderItem, null);
    }

    public History(OrderInfo orderInfo, LocalDateTime date) {
        this(date, orderInfo.getItems(), orderInfo.getPaymentPrice());
    }

    public History(OrderInfo orderInfo) {
        this(orderInfo.getItems(), orderInfo.getPaymentPrice());
    }


    private static Integer calculateTotalPrice(List<OrderItem> orderItem) {
        return orderItem.stream()
                .map(o -> o.getQuantity() * Menu.of(o.getCoffee()).getPrice())
                .reduce(0, Integer::sum);
    }
}
