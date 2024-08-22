package book.objectorientedfactsandmisconceptions.pojo.coupon;

import book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.CouponResponsibility;
import lombok.Getter;

import static book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException.*;

/**
 * 쿠폰 정보
 */
@Getter
public class CouponInfo implements CouponResponsibility {

    private int stamp;
    private int coupon;

    private CouponInfo() {
    }

    private CouponInfo(int stamp) {
        this.stamp = stamp;
    }

    public static CouponInfo initCoupon(Integer stamp) {
        if(stamp == null) {
            return new CouponInfo();
        }
        CouponInfo couponInfo = new CouponInfo(stamp);
        couponInfo.coupon = stamp / 10;
        couponInfo.stamp = couponInfo.coupon > 0 ? stamp - (couponInfo.coupon * 10) : stamp;
        return couponInfo;
    }

    public void addStamp(int stamp) throws IllegalStateException {
        if(stamp < 0) {
            int stampTemp = this.stamp + (this.coupon * 10) + stamp;

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

    @Override
    public boolean applyCoupon(Integer coupon) {
        if(coupon == null || this.coupon < coupon) {
            throw new IllegalStateException(CANNOT_USE_COUPON.getMessage());
        }
        this.coupon -= coupon;
        return true;
    }



}
