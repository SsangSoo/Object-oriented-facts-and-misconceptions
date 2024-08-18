package book.objectorientedfactsandmisconceptions.pojo.order;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderInfo {

    private final List<OrderItem> items;
    private int totalPrice;

    public OrderInfo(List<OrderItem> items) {
        this.items = items;
        this.totalPrice = items.stream()
                .map(item -> Menu.of(item.coffee()).getPrice() * item.quantity())
                .reduce(0, Integer::sum);
    }

    // 쿠폰 적용
    public void applyCoupon(int coupon) {
        this.totalPrice -= coupon * 4000;
    }
}
