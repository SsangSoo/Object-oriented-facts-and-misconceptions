package book.objectorientedfactsandmisconceptions.pojo.coupon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CouponInfoTest {

    @Test
    @DisplayName("스탬프의 개수를 더하면 쿠폰도 계산되어 저장된다.")
    void addStampTest() {
        CouponInfo couponInfo = CouponInfo.initCoupon(3);

        couponInfo.addStamp(3); // 6
        assertThat(couponInfo.getCoupon()).isZero();
        assertThat(couponInfo.getStamp()).isEqualTo(6);

        couponInfo.addStamp(-2); // 4
        assertThat(couponInfo.getCoupon()).isZero();
        assertThat(couponInfo.getStamp()).isEqualTo(4);

        couponInfo.addStamp(10); // 14
        assertThat(couponInfo.getCoupon()).isEqualTo(1);
        assertThat(couponInfo.getStamp()).isEqualTo(4);

        couponInfo.addStamp(-3); // 11
        assertThat(couponInfo.getCoupon()).isEqualTo(0);
        assertThat(couponInfo.getStamp()).isEqualTo(1);
    }

    @Test
    @DisplayName("스탬프의 개수보다 더 많은 개수를 빼버리면 예외가 발행한다")
    void addStampExceptionTest() {
        //given
        CouponInfo couponInfo = CouponInfo.initCoupon(3);

        //when, then
        assertThatThrownBy(() ->
                couponInfo.addStamp(-4))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("스탬프 취소가 불가능한 상태입니다.");

    }

}