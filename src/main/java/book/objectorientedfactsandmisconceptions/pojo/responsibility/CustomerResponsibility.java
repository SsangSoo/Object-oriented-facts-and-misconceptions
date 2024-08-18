package book.objectorientedfactsandmisconceptions.pojo.responsibility;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.coupon.CouponInfo;

import java.util.List;

/**
 * 손님의 책임
 */
public interface CustomerResponsibility {


    /**
     * 쿠폰을 확인할 수 있다.
     * @return
     */
     CouponInfo myCoupon();


}
