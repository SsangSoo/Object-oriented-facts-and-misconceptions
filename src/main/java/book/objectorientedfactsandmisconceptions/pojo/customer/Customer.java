package book.objectorientedfactsandmisconceptions.pojo.customer;

import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.coupon.CouponInfo;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.CustomerResponsibility;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 손님
 */
@Getter
public class Customer implements CustomerResponsibility {

    private final Long id;
    private final String phone;
    private final List<HistoryElement> orderRepository = new ArrayList<>();
    private final CouponInfo couponInfo;

    private Customer(Long id, String phone, Integer stamp) {
        this.id = id;
        this.phone = phone;
        this.couponInfo = stamp != null ? CouponInfo.initCoupon(stamp) : null;
    }

    public static Customer create(Long id, String name, Integer stamp) {
        return new Customer(id, name, stamp);
    }

    public void addOrderInfo(HistoryElement historyElement) {
        orderRepository.add(historyElement);
    }


}
