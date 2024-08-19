package book.objectorientedfactsandmisconceptions.pojo.order;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderInfo {

    private LocalDateTime date;
    private List<OrderItem> items;
    private boolean couponUse;
    private int totalPrice;

    public OrderInfo(List<OrderItem> items, boolean couponUse, Integer coupon) {
        this.date = LocalDateTime.now();
        this.couponUse = couponUse;
        this.items = items;
        this.totalPrice = items.stream()
                .map(item -> Menu.of(item.getCoffee()).getPrice() * item.getQuantity())
                .reduce(0, Integer::sum);
        if(couponUse) {
            applyCoupon(coupon);
        }
    }

    // 쿠폰 적용
    public void applyCoupon(int coupon) {
        this.totalPrice -= coupon * 4000;
    }
}
