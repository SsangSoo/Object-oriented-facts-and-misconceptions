package book.objectorientedfactsandmisconceptions.pojo.kiosk;

import book.objectorientedfactsandmisconceptions.pojo.coupon.CouponInfo;
import book.objectorientedfactsandmisconceptions.pojo.history.History;
import book.objectorientedfactsandmisconceptions.pojo.history.HistoryPurchase;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.barista.Barista;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.customer.Customer;
import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.KioskResponsibility;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException.*;

/**
 * 키오스크
 *
 */
public class Kiosk implements KioskResponsibility {

    public static final Map<String, Customer> customerRepository = new HashMap<>();
    public static final List<HistoryElement> orderRepository = new ArrayList<>();


    private Long idValue = 0L;

    @Override
    public List<Coffee> orderCoffee(OrderInfo orderInfo, boolean orderAsMember, boolean orderWithCoupon, Integer useCoupon, String phone) {
        // 비회원인 경우
        if(!orderAsMember && phone == null) {
            orderRepository.add(new HistoryElement(orderInfo.getItems()));
            return Barista.makeCoffee(orderInfo.getItems());
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
            customerRepository.put(phone, Customer.create(++idValue, phone));
            findCustomer = customerRepository.get(phone);
        }
        // 쿠폰을 사용할 경우
        // 쿠폰의 검증을 원래 키오스크에 할당했으나 쿠폰 검증의 책임은 쿠폰한테 있다고 판단함.
        if(orderWithCoupon) {
            if(findCustomer.getCouponInfo().applyCoupon(useCoupon)) {
                orderInfo.applyCoupon(useCoupon);
            }
        }
        // 스탬프 적립 및 계산
        calculateStamp(orderInfo, findCustomer);

        // 판매 내역에 저장
        orderRepository.add(new HistoryElement(orderInfo));
        // 구매 내역에 저장
        findCustomer.addOrderInfo(new HistoryElement(orderInfo));

        return Barista.makeCoffee(orderInfo.getItems());


    }

    private static void calculateStamp(OrderInfo orderInfo, Customer findCustomer) {
        Integer stamp = orderInfo.getItems().stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);
        findCustomer.getCouponInfo().addStamp(stamp);
    }




    @Override
    public HistoryPurchase historyPurchaseOfYear(int year, String phone) {
        Customer findCustomer = customerRepository.get(phone);

        if(Objects.isNull(findCustomer)) {
            throw new IllegalStateException(CANNOT_FOUND_CUSTOMER.getMessage());
        }

        List<HistoryElement> historyList = findCustomer.getOrderRepository().stream()
                .filter(historyElement -> historyElement.getDate().getYear() == year)
                .toList();

        return HistoryPurchase.of(findCustomer.getPhone(), historyList);
    }

    @Override
    public HistoryPurchase historyPurchaseOfMonth(int year, int month, String phone) {
        Customer findCustomer = customerRepository.get(phone);

        if(Objects.isNull(findCustomer)) {
            throw new IllegalStateException(CANNOT_FOUND_CUSTOMER.getMessage());
        }

        List<HistoryElement> historyList = findCustomer.getOrderRepository().stream()
                .filter(historyElement -> historyElement.getDate().getYear() == year)
                .filter(historyElement -> historyElement.getDate().getMonth() == Month.of(month))
                .toList();

        return HistoryPurchase.of(findCustomer.getPhone(), historyList);
    }


    @Override
    public HistoryPurchase historyPurchaseOfDay(LocalDate date, String phone) {
        Customer findCustomer = customerRepository.get(phone);

        if(Objects.isNull(findCustomer)) {
            throw new IllegalStateException(CANNOT_FOUND_CUSTOMER.getMessage());
        }

        List<HistoryElement> historyList = findCustomer.getOrderRepository().stream()
                .filter(historyElement -> historyElement.getDate().toLocalDate().isEqual(date))
                .toList();

        return HistoryPurchase.of(findCustomer.getPhone(), historyList);
    }

    @Override
    public History historySalesOfYear(int year) {
        List<HistoryElement> historyList = orderRepository.stream()
                .filter(historyElement -> historyElement.getDate().getYear() == year)
                .toList();

        return History.of(historyList);
    }

    @Override
    public History historySalesOfMonth(int year, int month) {
        List<HistoryElement> historyList = orderRepository.stream()
                .filter(historyElement -> historyElement.getDate().getYear() == year)
                .filter(historyElement -> historyElement.getDate().getMonth() == Month.of(month))
                .toList();
        return History.of(historyList);

    }

    @Override
    public History historySalesOfDay(LocalDate date) {
        List<HistoryElement> historyList = orderRepository.stream()
                .filter(historyElement -> historyElement.getDate().toLocalDate().isEqual(date))
                .toList();
        return History.of(historyList);

    }
}
