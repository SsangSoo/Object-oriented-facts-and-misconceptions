package book.objectorientedfactsandmisconceptions.pojo.kiosk;

import book.objectorientedfactsandmisconceptions.pojo.coupon.CouponInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.barista.Barista;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.customer.Customer;
import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.KioskResponsibility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException.IMPOSSIBLE_ORDER_BY_CUSTOMER;

/**
 * 키오스크
 *
 */
public class Kiosk implements KioskResponsibility {

    private final Map<String, Customer> customerRepository = new HashMap<>();
    private final List<HistoryElement> orderRepository = new ArrayList<>();
    private final Barista barista;

    private Kiosk(Barista barista) {
        this.barista = barista;
    }

    private Long idValue = 0L;

    @Override
    public List<Coffee> orderCoffee(OrderInfo orderInfo, boolean orderAsMember, boolean orderWithCoupon, Integer useCoupon, String phone) {
        // 비회원인 경우
        if(!orderAsMember && phone == null) {
            orderRepository.add(new HistoryElement(orderInfo.getItems()));
            return barista.makeCoffee(orderInfo.getItems());
        }
        // 회원으로 주문하는데, phone이 null이라면
        if(orderAsMember && phone == null) {
            throw new IllegalStateException(IMPOSSIBLE_ORDER_BY_CUSTOMER.getMessage());
        }

//        orderInfo.getItems();       // 주문 정보
//        orderInfo.getTotalPrice();  // 주문 금액
//        orderInfo.isCouponUse();    // 주문시 쿠폰 사용여부

        // 회원으로 주문
        // 회원 조회 및 등록
        Customer findCustomer = customerRepository.get(phone);
        if (Objects.isNull(findCustomer)) {
            customerRepository.put(phone, Customer.create(++idValue, phone, stamp));
            findCustomer = customerRepository.get(phone);
        }


        // 쿠폰을 사용할 경우
        if(orderWithCoupon) {
            int coupon = findCustomer.getCouponInfo().getCoupon();

            if(coupon < useCoupon) {
                throw new IllegalStateException("사용하려는 쿠폰이 가지고 있는 쿠폰보다 많으므로 쿠폰 적용이 불가능합니다.");
            }

            findCustomer.getCouponInfo().applyCoupon(useCoupon);

    ////////////// 구매내역 책임 고민하기.
            orderInfo.applyCoupon(coupon);
        }

        // 스탬프 적립
        Integer stamp = orderInfo.getItems().stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);

        findCustomer.getCouponInfo().addStamp(stamp);

        // 판매 내역에 저장
        orderRepository.put(LocalDateTime.now(), orderInfo);
        // 구매 내역에 저장
        findCustomer.addOrderInfo(new HistoryElement(orderInfo.getItems()));

        return barista.makeCoffee(orderInfo.getItems());


    }


    @Override
    public HistoryElement historyPurchaseOfYear(int year, Long id) {
        return null;
    }

    @Override
    public HistoryElement historyPurchaseOfMonth(int year, int month, Long id) {
        return null;
    }

    @Override
    public HistoryElement historyPurchaseOfDay(LocalDate date, Long id) {
        return null;
    }

    @Override
    public HistoryElement historySalesOfYear(int year) {
        return null;
    }

    @Override
    public HistoryElement historySalesOfMonth(int year, int month) {
        return null;
    }

    @Override
    public HistoryElement historySalesOfDay(LocalDate date) {
        return null;
    }
}
