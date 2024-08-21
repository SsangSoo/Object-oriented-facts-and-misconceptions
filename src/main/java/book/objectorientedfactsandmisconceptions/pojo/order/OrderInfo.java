package book.objectorientedfactsandmisconceptions.pojo.order;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderInfo {

    private List<OrderItem> items;
    private boolean couponUse;
    private int discount;
    private int totalPrice;
    private int paymentPrice;

    public OrderInfo(List<OrderItem> items, boolean couponUse) {
        this.couponUse = couponUse;
        this.items = items;
        this.totalPrice = items.stream()
                .map(item -> Menu.of(item.getCoffee()).getPrice() * item.getQuantity())
                .reduce(0, Integer::sum);
        this.paymentPrice = this.totalPrice;
    }

    /**
     * 쿠폰 적용 후 주문금액 계산
     * @param useCoupon : 사용할 쿠폰 개수
     */
    public void applyCoupon(int useCoupon) {
        if(!this.couponUse)  {
            throw new IllegalStateException(BusinessException.CANNOT_USE_COUPON.getMessage());
        }
        this.discount = useCoupon * 4000;
        this.paymentPrice = this.totalPrice - this.discount;
    }


}
