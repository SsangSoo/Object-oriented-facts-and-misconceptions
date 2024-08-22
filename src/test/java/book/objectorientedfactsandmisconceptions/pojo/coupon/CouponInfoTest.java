package book.objectorientedfactsandmisconceptions.pojo.coupon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException.CANNOT_USE_COUPON;
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
        assertThat(couponInfo.getCoupon()).isZero();
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

    @Test
    @DisplayName("쿠폰 초기화 시 쿠폰이 계산된다.")
    void initCouponTest() {
        //given // when
        CouponInfo couponInfo = CouponInfo.initCoupon(13);

        //then
        assertThat(couponInfo.getStamp()).isEqualTo(3);
        assertThat(couponInfo.getCoupon()).isEqualTo(1);
    }

    @Test
    @DisplayName("쿠폰 적용시 적용된 쿠폰 수 만큼 빼버려진다.")
    void applyCouponTest() {
        //given
        CouponInfo couponInfo = CouponInfo.initCoupon(13);

        //when
        couponInfo.applyCoupon(1);

        //then
        assertThat(couponInfo.getCoupon()).isZero();
        assertThat(couponInfo.getStamp()).isEqualTo(3);
    }

    @Test
    @DisplayName("적용하려는 쿠폰보다 가지고 있는 쿠폰 개수가 적다면 예외가 발생한다.")
    void applyCouponExceptionTest() {
        //given
        CouponInfo couponInfo = CouponInfo.initCoupon(13);
        assertThat(couponInfo.getCoupon()).isEqualTo(1);
        assertThat(couponInfo.getStamp()).isEqualTo(3);

        //when // then
        assertThatThrownBy(() -> couponInfo.applyCoupon(2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(CANNOT_USE_COUPON.getMessage());

    }

}