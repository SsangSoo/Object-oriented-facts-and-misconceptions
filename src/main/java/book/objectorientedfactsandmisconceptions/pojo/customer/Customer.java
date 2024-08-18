package book.objectorientedfactsandmisconceptions.pojo.customer;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.coupon.CouponInfo;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.CustomerResponsibility;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 손님
 */
@Getter
public class Customer implements CustomerResponsibility {

    private final Long id;
    private final String phone;

    private Map<LocalDateTime, OrderInfo> orderInfoMap = new HashMap<>();
    private CouponInfo couponInfo;

    public Customer(Long id, String name, Integer stamp) {
        this.id = id;
        this.phone = name;
        this.couponInfo = stamp != null ? CouponInfo.initCoupon(stamp) : null;
    }

    @Override
    public CouponInfo myCoupon() {
        return this.couponInfo;
    }


}
