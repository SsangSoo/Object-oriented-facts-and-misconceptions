package book.objectorientedfactsandmisconceptions.pojo.coupon;

import book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.CouponResponsibility;
import lombok.Getter;

/**
 * 쿠폰 정보
 */
@Getter
public class CouponInfo implements CouponResponsibility {

    private int stamp;
    private int coupon;

    private CouponInfo(int stamp) {
        this.stamp = stamp;
    }

    public static CouponInfo initCoupon(int stamp) {
        CouponInfo couponInfo = new CouponInfo(stamp);
        couponInfo.coupon = stamp / 10;
        couponInfo.stamp = couponInfo.coupon > 0 ? stamp - (couponInfo.coupon * 10) : stamp;
        return couponInfo;
    }

    public void addStamp(int stamp) {
        if(stamp < 0) {
            int stampTemp = this.stamp + (this.coupon * 10) + stamp;
            int couponTemp = 0;

            if(stampTemp < 0) {
                throw new IllegalStateException(BusinessException.IMPOSSIBLE_CANCEL.getMessage());
            }

            CouponInfo couponInfo = initCoupon(this.stamp);
            this.stamp = couponInfo.stamp;
            this.coupon = couponInfo.coupon;
        }

        this.stamp += stamp;

        if(this.stamp > 10) {
            this.coupon = this.stamp / 10;
            this.stamp = this.stamp - (this.coupon * 10);
        }
    }

    public void applyCoupon() {
        this.coupon = 0;
    }



}
