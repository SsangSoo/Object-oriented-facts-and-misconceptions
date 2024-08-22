package book.objectorientedfactsandmisconceptions.pojo.kiosk;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.customer.Customer;
import book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException;
import book.objectorientedfactsandmisconceptions.pojo.history.History;
import book.objectorientedfactsandmisconceptions.pojo.history.PurchaseHistory;
import book.objectorientedfactsandmisconceptions.pojo.history.SalesHistory;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class KioskTest {

    Kiosk kiosk = Kiosk.getInstance();
    Map<String, Customer> customerMap = kiosk.getCustomerRepository();


    final Menu americano = Menu.AMERICANO;
    final Menu espresso = Menu.ESPRESSO;
    final Menu cafeLatte = Menu.CAFE_LATTE;

    @BeforeEach
    void clearKioskMemory() {
        kiosk.getCustomerRepository().clear();
        kiosk.getOrderRepository().clear();
    }

    @Test
    @DisplayName("커피 주문 테스트 - 비회원")
    void noMemberOrderCoffeeTest() {
        //given
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        List<Coffee> coffees = kiosk.orderCoffee(orderInfo, false, false, null, null, null);

        //then
        assertThat(coffees).hasSize(2);
        assertThat(coffees.get(0)).extracting("name", "price").containsExactlyInAnyOrder("아메리카노", 4000);
        assertThat(coffees.get(1)).extracting("name", "price").containsExactlyInAnyOrder("카페라떼", 4500);

        assertThatThrownBy(() -> kiosk.historyPurchaseOfYear(2024, "010123"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("회원 정보를 찾을 수 없습니다.");

        SalesHistory salesHistory = kiosk.historySalesOfDay(LocalDate.now());
        assertThat(salesHistory.getHistories()).hasSize(1);
        assertThat(salesHistory.getTotalPrice()).isEqualTo(8500);

        SalesHistory salesHistoryByMonth = kiosk.historySalesOfMonth(2024, 8);
        assertThat(salesHistoryByMonth.getHistories()).hasSize(1);
        assertThat(salesHistoryByMonth.getTotalPrice()).isEqualTo(8500);

        SalesHistory salesHistoryByYear = kiosk.historySalesOfYear(2024);
        assertThat(salesHistoryByYear.getHistories()).hasSize(1);
        assertThat(salesHistoryByYear.getTotalPrice()).isEqualTo(8500);

    }

    @Test
    @DisplayName("커피 주문 테스트 - 회원이 아님에도 불구하고, 회원으로 주문하면 회원이 저장되어야 한다.")
    void memberOrderCoffeeTest() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        List<Coffee> coffees = kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        Map<String, Customer> customerRepository = kiosk.getCustomerRepository();
        Customer customer = customerRepository.get(phone);

        //then
        assertThat(coffees).hasSize(2);
        assertThat(customer)
                .isInstanceOf(Customer.class)
                .isNotNull();
        assertThat(customer.getPhone()).isEqualTo(phone);
        assertThat(customer.getCouponInfo())
                .extracting("stamp", "coupon")
                .containsExactlyInAnyOrder(2, 0);
        assertThat(customer.getOrderRepository()).hasSize(1);

    }


    @Test
    @DisplayName("커피 주문 테스트 - 회원으로 주문하는데, 핸드폰 번호를 입력하지 않으면 예외가 발생한다.")
    void exceptionTestByMemberOrderCoffee() {
        // given
        String phone = null;
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when // then
        assertThatThrownBy(() -> kiosk.orderCoffee(orderInfo, true, false, null, phone, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(BusinessException.IMPOSSIBLE_ORDER_BY_CUSTOMER.getMessage());
    }

    @Test
    @DisplayName("쿠폰을 적용하여 주문하려하는데, 쿠폰이 없으면 예외가 발생한다.")
    void exceptionTestWhenOrderCoffeeWithCoupons() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), true);

        //when // then
        assertThatThrownBy(() -> kiosk.orderCoffee(orderInfo, true, true, 1, phone, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(BusinessException.CANNOT_USE_COUPON.getMessage());
    }

    @Test
    @DisplayName("회원으로 커피 주문 HappyCase")
    void memberOrderCoffeeHappyCaseTest() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        List<Coffee> coffees = kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        Customer customer = kiosk.getCustomerRepository().get(phone);
        List<History> orderRepository = kiosk.getOrderRepository();

        History history = orderRepository.get(0);

        //then
        assertThat(coffees).hasSize(2);
        assertThat(customer.getPhone()).isEqualTo(phone);
        assertThat(customer.getCouponInfo())
                .extracting("coupon", "stamp")
                .containsExactlyInAnyOrder(2, 0);
        assertThat(customer)
                .isNotNull()
                .isInstanceOf(Customer.class);
        assertThat(orderRepository).hasSize(1);

        assertThat(history.getTotalPrice()).isEqualTo(8500);
    }

    @Test
    @DisplayName("주문 커피 값 검증 테스트")
    void OrderCoffeeValidationTest() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when //then
        assertThatThrownBy(() -> kiosk.orderCoffee(orderInfo, true, true, null, phone, null))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(BusinessException.INVALID_STATE.getMessage());
    }

    @Test
    @DisplayName("년별 구매내역을 확인할 수 있다.")
    void historyPurchaseOfYear() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), true);

        kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        //when
        PurchaseHistory purchaseHistory = kiosk.historyPurchaseOfYear(2024, phone);

        //then
        assertThat(purchaseHistory.getPhone()).isEqualTo(phone);
        assertThat(purchaseHistory.getHistories()).hasSize(1);
        assertThat(purchaseHistory.getTotalPrice()).isEqualTo(8500);

    }

    @Test
    @DisplayName("지정한 년도에 구매내역이 없으면 결과에 나오지 않는다.")
    void historyPurchaseOfYear2() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), true);

        kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        //when
        PurchaseHistory purchaseHistory2023 = kiosk.historyPurchaseOfYear(2023, phone);
        PurchaseHistory purchaseHistory2024 = kiosk.historyPurchaseOfYear(2024, phone);
        PurchaseHistory purchaseHistory2025 = kiosk.historyPurchaseOfYear(2025, phone);

        //then
        assertThat(purchaseHistory2023.getTotalPrice()).isZero();
        assertThat(purchaseHistory2023.getPhone()).isEqualTo(phone);
        assertThat(purchaseHistory2023.getHistories()).isEmpty();

        assertThat(purchaseHistory2024.getTotalPrice()).isEqualTo(8500);
        assertThat(purchaseHistory2024.getPhone()).isEqualTo(phone);
        assertThat(purchaseHistory2024.getHistories()).hasSize(1);

        assertThat(purchaseHistory2025.getTotalPrice()).isZero();
        assertThat(purchaseHistory2025.getPhone()).isEqualTo(phone);
        assertThat(purchaseHistory2025.getHistories()).isEmpty();
    }


    @Test
    @DisplayName("월별 구매내역을 확인할 수 있다.")
    void historyPurchaseOfMonth() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), true);

        kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        //when
        PurchaseHistory purchaseHistory = kiosk.historyPurchaseOfMonth(2024, 8, phone);

        //then
        assertThat(purchaseHistory.getHistories()).hasSize(1);
        assertThat(purchaseHistory.getTotalPrice()).isEqualTo(8500);
        assertThat(purchaseHistory.getPhone()).isEqualTo(phone);

    }

    @Test
    @DisplayName("지정한 월에 구매내역이 없으면 결과에 나오지 않는다.")
    void historyPurchaseOfMonth2() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), true);

        kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        //when
        PurchaseHistory purchaseHistory7 = kiosk.historyPurchaseOfMonth(2024, 7, phone);
        PurchaseHistory purchaseHistory8 = kiosk.historyPurchaseOfMonth(2024, 8, phone);
        PurchaseHistory purchaseHistory9 = kiosk.historyPurchaseOfMonth(2024, 9, phone);

        //then
        assertThat(purchaseHistory7.getHistories()).isEmpty();
        assertThat(purchaseHistory7.getTotalPrice()).isZero();
        assertThat(purchaseHistory7.getPhone()).isEqualTo(phone);

        assertThat(purchaseHistory8.getHistories()).hasSize(1);
        assertThat(purchaseHistory8.getTotalPrice()).isEqualTo(8500);
        assertThat(purchaseHistory8.getPhone()).isEqualTo(phone);

        assertThat(purchaseHistory9.getHistories()).isEmpty();
        assertThat(purchaseHistory9.getTotalPrice()).isZero();
        assertThat(purchaseHistory9.getPhone()).isEqualTo(phone);
    }
    
    

    @Test
    @DisplayName("지정한 일에 대한 구매내역을 확인할 수 있다.")
    void historyPurchaseOfDay() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), true);

        kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        //when
        PurchaseHistory purchaseHistory = kiosk.historyPurchaseOfDay(LocalDate.now(), phone);

        //then
        assertThat(purchaseHistory.getHistories()).hasSize(1);
        assertThat(purchaseHistory.getTotalPrice()).isEqualTo(8500);
        assertThat(purchaseHistory.getPhone()).isEqualTo(phone);

    }

    @Test
    @DisplayName("지정한 일에 구매내역이 없으면 결과가 나오지 않는다.")
    void historyPurchaseOfDay2() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), true);

        kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        //when
        PurchaseHistory purchaseHistory = kiosk.historyPurchaseOfDay(LocalDate.of(2024,1,1), phone);

        //then
        assertThat(purchaseHistory.getHistories()).isEmpty();
        assertThat(purchaseHistory.getTotalPrice()).isZero();
        assertThat(purchaseHistory.getPhone()).isEqualTo(phone);
    }

    @Test
    @DisplayName("지정한 년도에 대한 판매내역을 확인할 수 있다.")
    void historySalesOfYear() {
        // given
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        kiosk.orderCoffee(orderInfo, false, false, null, null, null);

        SalesHistory salesHistory = kiosk.historySalesOfYear(2024);


        //then
        assertThat(salesHistory.getHistories()).hasSize(1);
        assertThat(salesHistory.getTotalPrice()).isEqualTo(8500);

    }

    @Test
    @DisplayName("지정한 년도에 대한 판매내역이 없다면 결과에 나오지 않는다.")
    void historySalesOfYear2() {
        // given
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        kiosk.orderCoffee(orderInfo, false, false, null, null, null);

        SalesHistory salesHistory = kiosk.historySalesOfYear(2023);


        //then
        assertThat(salesHistory.getHistories()).isEmpty();
        assertThat(salesHistory.getTotalPrice()).isZero();

    }

    @Test
    @DisplayName("지정한 월에 대한 판매내역을 확인할 수 있다.")
    void historySalesOfMonth() {
        // given
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        kiosk.orderCoffee(orderInfo, false, false, null, null, null);

        SalesHistory salesHistory = kiosk.historySalesOfMonth(2024, 8);

        //then
        assertThat(salesHistory.getHistories()).hasSize(1);
        assertThat(salesHistory.getTotalPrice()).isEqualTo(8500);
    }

    @Test
    @DisplayName("지정한 월에 대한 판매내역이 없다면 결과에 나오지 않는다.")
    void historySalesOfMonth2() {
        // given
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        kiosk.orderCoffee(orderInfo, false, false, null, null, null);

        SalesHistory salesHistory = kiosk.historySalesOfMonth(2023, 7);

        //then
        assertThat(salesHistory.getHistories()).isEmpty();
        assertThat(salesHistory.getTotalPrice()).isZero();
    }


    @Test
    @DisplayName("지정한 일에 대한 판매내역을 확인할 수 있다.")
    void historySalesOfDay() {
        // given
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        kiosk.orderCoffee(orderInfo, false, false, null, null, null);

        SalesHistory salesHistory = kiosk.historySalesOfDay(LocalDate.now());

        //then
        assertThat(salesHistory.getHistories()).hasSize(1);
        assertThat(salesHistory.getTotalPrice()).isEqualTo(8500);
    }


    @Test
    @DisplayName("지정한 일에 대한 판매내역이 없다면 결과가 나오지 않는다.")
    void historySalesOfDay2() {
        // given
        OrderItem orderItemAmericano = new OrderItem(americano, 1);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        //when
        kiosk.orderCoffee(orderInfo, false, false, null, null, null);

        SalesHistory salesHistory = kiosk.historySalesOfDay(LocalDate.of(2024,1,1));

        //then
        assertThat(salesHistory.getHistories()).isEmpty();
        assertThat(salesHistory.getTotalPrice()).isZero();
    }

    @Test
    @DisplayName("쿠폰을 적용하여 주문할 수 있다.")
    void applyCouponOrderCoffeeTest() {
        // given
        String phone = "01012341234";
        OrderItem orderItemAmericano = new OrderItem(americano, 5);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 5);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        OrderItem orderItemEspresso = new OrderItem(cafeLatte, 1);
        OrderInfo applyCouponorderInfo = new OrderInfo(List.of(orderItemEspresso), true);

        //when
        kiosk.orderCoffee(applyCouponorderInfo, true, true, 1, phone, null);

        //then
        assertThat(applyCouponorderInfo.isCouponUse()).isTrue();
        assertThat(applyCouponorderInfo.getTotalPrice()).isEqualTo(4500);
        assertThat(applyCouponorderInfo.getPaymentPrice()).isEqualTo(500);
        assertThat(applyCouponorderInfo.getDiscount()).isEqualTo(4000);
        assertThat(applyCouponorderInfo.getItems()).hasSize(1);
    }

    @Test
    @DisplayName("쿠폰을 적용한 주문 내역의 총금액은 총금액 - 쿠폰 적용 할인 값이 계상된 금액이다.")
    void applyCouponOrderCoffeeHistoryTest() {
        // given
        String phone = "01012341234";
        LocalDateTime orderDateTime = LocalDateTime.of(2024, 8, 23, 4, 50);

        OrderItem orderItemAmericano = new OrderItem(americano, 5);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 5);

        OrderInfo orderInfo = new OrderInfo(List.of(orderItemAmericano, orderItemCafeLatte), false);

        kiosk.orderCoffee(orderInfo, true, false, null, phone, null);

        OrderItem orderItemEspresso = new OrderItem(cafeLatte, 1);
        OrderInfo applyCouponorderInfo = new OrderInfo(List.of(orderItemEspresso), true);


        //when
        kiosk.orderCoffee(applyCouponorderInfo, true, true, 1, phone, LocalDateTime.of(2024, 8,23, 4,50));

        //then
        SalesHistory salesHistory = kiosk.historySalesOfDay(LocalDate.of(2024, 8, 23));
        History historySales = salesHistory.getHistories().getFirst();

        PurchaseHistory purchaseHistory = kiosk.historyPurchaseOfDay(LocalDate.of(2024, 8, 23), phone);
        History historyPurchase = purchaseHistory.getHistories().getFirst();

        assertThat(historySales.getTotalPrice()).isEqualTo(500);
        assertThat(historySales.getOrderItem()).hasSize(1);
        assertThat(historySales.getDate()).isEqualTo(orderDateTime);

        assertThat(purchaseHistory.getTotalPrice()).isEqualTo(500);
        assertThat(purchaseHistory.getPhone()).isEqualTo(phone);
        assertThat(purchaseHistory.getHistories()).hasSize(1);

        assertThat(historyPurchase.getOrderItem()).hasSize(1);
        assertThat(historyPurchase.getDate()).isEqualTo(orderDateTime);
    }

}