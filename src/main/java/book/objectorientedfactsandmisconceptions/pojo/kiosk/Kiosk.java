package book.objectorientedfactsandmisconceptions.pojo.kiosk;

import book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException;
import book.objectorientedfactsandmisconceptions.pojo.history.SalesHistory;
import book.objectorientedfactsandmisconceptions.pojo.history.PurchaseHistory;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.barista.Barista;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.customer.Customer;
import book.objectorientedfactsandmisconceptions.pojo.history.History;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.KioskResponsibility;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException.*;

/**
 * 키오스크
 *
 */
@Getter
public class Kiosk implements KioskResponsibility {

    private static Kiosk instance;

    private Kiosk() {}

    public static Kiosk getInstance() {
        if(instance == null) {
            return new Kiosk();
        }
        return instance;
    }

    private final Map<String, Customer> customerRepository = new HashMap<>();
    private final List<History> orderRepository = new ArrayList<>();

    @Override
    public List<Coffee> orderCoffee(OrderInfo orderInfo, boolean orderAsMember, boolean orderWithCoupon, Integer useCoupon, String phone) {
        if(orderWithCoupon && useCoupon == null) {
            throw new IllegalStateException(BusinessException.INVALID_STATE.getMessage());
        }

        // 비회원인 경우
        if(!orderAsMember && phone == null) {
            orderRepository.add(new History(orderInfo.getItems()));
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
            customerRepository.put(phone, Customer.create(phone));
            findCustomer = customerRepository.get(phone);
        }
        // 쿠폰을 사용할 경우
        // 쿠폰의 검증을 원래 키오스크에 할당했으나 쿠폰 검증의 책임은 쿠폰한테 있다고 판단함.
        if(orderWithCoupon && findCustomer.getCouponInfo().applyCoupon(useCoupon)) {
                orderInfo.applyCoupon(useCoupon);
        }
        // 스탬프 적립 및 계산
        calculateStamp(orderInfo, findCustomer);

        // 판매 내역에 저장
        orderRepository.add(new History(orderInfo));
        // 구매 내역에 저장
        findCustomer.addOrderInfo(new History(orderInfo));

        return Barista.makeCoffee(orderInfo.getItems());


    }

    private static void calculateStamp(OrderInfo orderInfo, Customer findCustomer) {
        Integer stamp = orderInfo.getItems().stream()
                .map(OrderItem::getQuantity)
                .reduce(0, Integer::sum);
        findCustomer.getCouponInfo().addStamp(stamp);
    }




    @Override
    public PurchaseHistory historyPurchaseOfYear(int year, String phone) {
        Customer findCustomer = customerRepository.get(phone);

        if(Objects.isNull(findCustomer)) {
            throw new IllegalStateException(CANNOT_FOUND_CUSTOMER.getMessage());
        }

        List<History> historyList = findCustomer.getOrderRepository().stream()
                .filter(historyElement -> historyElement.getDate().getYear() == year)
                .toList();

        return PurchaseHistory.of(findCustomer.getPhone(), historyList);
    }

    @Override
    public PurchaseHistory historyPurchaseOfMonth(int year, int month, String phone) {
        Customer findCustomer = customerRepository.get(phone);

        if(Objects.isNull(findCustomer)) {
            throw new IllegalStateException(CANNOT_FOUND_CUSTOMER.getMessage());
        }

        List<History> historyList = findCustomer.getOrderRepository().stream()
                .filter(historyElement -> historyElement.getDate().getYear() == year)
                .filter(historyElement -> historyElement.getDate().getMonth() == Month.of(month))
                .toList();

        return PurchaseHistory.of(findCustomer.getPhone(), historyList);
    }


    @Override
    public PurchaseHistory historyPurchaseOfDay(LocalDate date, String phone) {
        Customer findCustomer = customerRepository.get(phone);

        if(Objects.isNull(findCustomer)) {
            throw new IllegalStateException(CANNOT_FOUND_CUSTOMER.getMessage());
        }

        List<History> historyList = findCustomer.getOrderRepository().stream()
                .filter(historyElement -> historyElement.getDate().toLocalDate().isEqual(date))
                .toList();

        return PurchaseHistory.of(findCustomer.getPhone(), historyList);
    }

    @Override
    public SalesHistory historySalesOfYear(int year) {
        List<History> historyList = orderRepository.stream()
                .filter(historyElement -> historyElement.getDate().getYear() == year)
                .toList();

        return SalesHistory.of(historyList);
    }

    @Override
    public SalesHistory historySalesOfMonth(int year, int month) {
        List<History> historyList = orderRepository.stream()
                .filter(historyElement -> historyElement.getDate().getYear() == year)
                .filter(historyElement -> historyElement.getDate().getMonth() == Month.of(month))
                .toList();
        return SalesHistory.of(historyList);

    }

    @Override
    public SalesHistory historySalesOfDay(LocalDate date) {
        List<History> historyList = orderRepository.stream()
                .filter(historyElement -> historyElement.getDate().toLocalDate().isEqual(date))
                .toList();
        return SalesHistory.of(historyList);

    }
}
