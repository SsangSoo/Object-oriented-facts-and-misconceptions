package book.objectorientedfactsandmisconceptions.pojo.customer;

import book.objectorientedfactsandmisconceptions.pojo.history.History;
import book.objectorientedfactsandmisconceptions.pojo.coupon.CouponInfo;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.CustomerResponsibility;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 손님
 */
@Getter
public class Customer implements CustomerResponsibility {

    private final String phone;
    private final List<History> orderRepository = new ArrayList<>();
    private final CouponInfo couponInfo;

    private Customer(String phone, Integer stamp) {
        this.phone = phone;
        this.couponInfo = CouponInfo.initCoupon(stamp);
    }

    public static Customer create(String phone) {
        return new Customer(phone, null);
    }

    public void addOrderInfo(History history) {
        orderRepository.add(history);
    }


}
