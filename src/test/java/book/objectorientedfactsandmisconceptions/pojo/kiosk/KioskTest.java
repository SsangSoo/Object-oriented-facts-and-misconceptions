package book.objectorientedfactsandmisconceptions.pojo.kiosk;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.customer.Customer;
import book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException;
import book.objectorientedfactsandmisconceptions.pojo.history.History;
import book.objectorientedfactsandmisconceptions.pojo.history.SalesHistory;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
        List<Coffee> coffees = kiosk.orderCoffee(orderInfo, false, false, null, null);

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
        List<Coffee> coffees = kiosk.orderCoffee(orderInfo, true, false, null, phone);

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
        assertThatThrownBy(() -> kiosk.orderCoffee(orderInfo, true, false, null, phone))
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
        assertThatThrownBy(() -> kiosk.orderCoffee(orderInfo, true, true, 1, phone))
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
        List<Coffee> coffees = kiosk.orderCoffee(orderInfo, true, false, null, phone);

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
        assertThatThrownBy(() -> kiosk.orderCoffee(orderInfo, true, true, null, phone))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(BusinessException.INVALID_STATE.getMessage());
    }

//    @Test
//    void historyPurchaseOfYear() {
//    }
//
//    @Test
//    void historyPurchaseOfMonth() {
//    }
//
//    @Test
//    void historyPurchaseOfDay() {
//    }
//
//    @Test
//    void historySalesOfYear() {
//    }
//
//    @Test
//    void historySalesOfMonth() {
//    }
//
//    @Test
//    void historySalesOfDay() {
//    }
}