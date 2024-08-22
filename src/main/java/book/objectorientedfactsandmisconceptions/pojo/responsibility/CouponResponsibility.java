package book.objectorientedfactsandmisconceptions.pojo.responsibility;

/**
 * 쿠폰의 책임
 */
public interface CouponResponsibility {


    /**
     * 스탬프 개수를 받으면 쿠폰과 스탬프를 계산하여 저장해야 한다.
     *  스탬프 개수를 받으면 스탬프 개수를 계산해서 쿠폰, 스탬프 계산한 결과를 얻을 수 있다.
     * @param stamp
     */
    void addStamp(int stamp) throws IllegalStateException;

    /**
     * 쿠폰 적용
     */
    boolean applyCoupon(Integer coupon);
}
