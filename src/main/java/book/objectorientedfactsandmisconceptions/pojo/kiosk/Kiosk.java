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

/**
 * 키오스크
 *
 */
public class Kiosk implements KioskResponsibility {

    private final Map<String, Customer> customerRepository = new HashMap<>();
    private final List<HistoryElement> orderRepository = new ArrayList<>();

    private final Barista barista;

    public Kiosk(Barista barista) {
        this.barista = barista;
    }

    private Long idValue = 0L;

    @Override
    public List<Coffee> orderCoffee(OrderInfo orderInfo, boolean orderAsMember, boolean orderWithCoupon, String phone) {
        // 비회원인 경우
        if(!orderAsMember || phone == null) {
            orderRepository.put(LocalDateTime.now(), orderInfo);
            return barista.makeCoffee(orderInfo.getItems());
        }

        // 회원 조회 및 등록
        Customer findCustomer = customerRepository.get(phone);
        Integer stamp = orderInfo.getItems().stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);

        if (Objects.isNull(findCustomer)) {
            customerRepository.put(phone, Customer.create(++idValue, phone, stamp));
            findCustomer = customerRepository.get(phone);
        }
        findCustomer.getCouponInfo().addStamp(stamp);


        // 쿠폰을 사용할 경우
        if(orderWithCoupon) {
            CouponInfo customerCoupon = findCustomer.getCouponInfo();
            int coupon = customerCoupon.getCoupon();
            customerCoupon.applyCoupon();
            orderInfo.applyCoupon(coupon);
        }

        // 판매 내역에 저장
        orderRepository.put(LocalDateTime.now(), orderInfo);
        // 구매 내역에 저장
        findCustomer.addOrderInfo(orderInfo);

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
