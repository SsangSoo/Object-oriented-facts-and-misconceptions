package book.objectorientedfactsandmisconceptions.pojo.order;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderInfo {

    private List<OrderItem> items;
    private boolean couponUse;
    private int totalPrice;

    public OrderInfo(List<OrderItem> items, boolean couponUse) {
        this.couponUse = couponUse;
        this.items = items;
        this.totalPrice = items.stream()
                .map(item -> Menu.of(item.getCoffee()).getPrice() * item.getQuantity())
                .reduce(0, Integer::sum);
    }

}
