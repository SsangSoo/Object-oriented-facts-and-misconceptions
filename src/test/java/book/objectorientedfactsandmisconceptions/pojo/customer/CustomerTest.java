package book.objectorientedfactsandmisconceptions.pojo.customer;

import book.objectorientedfactsandmisconceptions.pojo.coffee.CoffeeOrder;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.customer.Customer;
import book.objectorientedfactsandmisconceptions.pojo.history.OrderHistory;
import book.objectorientedfactsandmisconceptions.pojo.history.PaymentHistory;
import book.objectorientedfactsandmisconceptions.pojo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CustomerTest {

    Customer todtn = Customer.createCustomer("쌩수");      // 고객

    @BeforeEach
    void createCustomer() {
        todtn.getOrderHistories().clear();
    }

    @Test
    @DisplayName("커피 메뉴를 확인할 수 있다.")
    void getCoffeeMenu() {
        // given -> BeforeEach에서 세팅

        // when
        Menu[] coffeeMenu = todtn.getCoffeeMenu();

        // then
        assertThat(coffeeMenu.length).isEqualTo(3);
        Menu coffeeMenu1 = coffeeMenu[0];
        Menu coffeeMenu2 = coffeeMenu[1];
        Menu coffeeMenu3 = coffeeMenu[2];

        assertThat(coffeeMenu1).extracting("coffee", "price")
                .containsExactlyInAnyOrder("아메리카노", 4000);
        assertThat(coffeeMenu2).extracting("coffee", "price")
                .containsExactlyInAnyOrder("에스프레소", 4000);
        assertThat(coffeeMenu3).extracting("coffee", "price")
                .containsExactlyInAnyOrder("카페라떼", 4500);

        assertThat(coffeeMenu1).isEqualTo(Menu.AMERICANO);
        assertThat(coffeeMenu2).isEqualTo(Menu.ESSPRESSO);
        assertThat(coffeeMenu3).isEqualTo(Menu.CAFELATTE);
    }

    @Test
    @DisplayName("없는 커피 메뉴를 확인하려고 하면 예외가 발생한다.")
    void invalidCoffeeMenu() {
        //given
        String invalidMenu = "헤이즐넛라떼";

        //when
        //then
        assertThatThrownBy(() -> Menu.of(invalidMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("커피를 주문한 내역을 일자에 맞게 확인할 수 있다.")
    void getPaymentHistoryAtDayTest() {
        //given
        CoffeeOrder threeAmericano = CoffeeOrder.of("아메리카노", 3);        // 주문할 커피
        todtn.orderCoffee("쌩스타", LocalDate.now(), threeAmericano);                 // 커피 주문

        //when
        PaymentHistory paymentHistoryAtDay = todtn.getPaymentHistoryAtDay(LocalDate.now());

        //then
        List<OrderHistory> orderHistoryList = paymentHistoryAtDay.getOrderHistoryList();
        assertThat(orderHistoryList.size()).isEqualTo(1);                       // 한 개의 주문내역

        OrderHistory orderHistory = orderHistoryList.getFirst();                        // 주문 내역 안에 12000원과 주문한 날짜(오늘)
        assertThat(orderHistory).extracting("orderedDate", "totalPrice")
                .containsExactlyInAnyOrder(LocalDate.now(), 12000);

        assertThat(orderHistory.getCoffeeOrderList().size()).isEqualTo(1);
        CoffeeOrder coffeeOrder = orderHistory.getCoffeeOrderList().getFirst();
        assertThat(coffeeOrder).extracting("menu", "count")
                .containsExactlyInAnyOrder(Menu.AMERICANO, 3);

        assertThat(coffeeOrder.getMenu()).extracting("coffee", "price")
                .containsExactlyInAnyOrder("아메리카노", 4000);
    }

    @Test
    @DisplayName("년도에 맞는 주문내역을 확인할 수 있다.")
    void getPaymentHistoryAtYear() {
        // given
        CoffeeOrder oneEspresso = CoffeeOrder.of("에스프레소", 1);        // 주문할 커피
        CoffeeOrder oneCafeLatte = CoffeeOrder.of("카페라떼", 1);        // 주문할 커피
        todtn.orderCoffee("쌩스타", LocalDate.of(2024, 1,30), oneEspresso, oneCafeLatte);

        CoffeeOrder twoAmericano = CoffeeOrder.of("아메리카노", 2);        // 주문할 커피
        todtn.orderCoffee("쌩스타", LocalDate.of(2024, 3,1), twoAmericano);

        // when
        PaymentHistory paymentHistoryAtYear = todtn.getPaymentHistoryAtYear(2024);

        // then
        List<OrderHistory> orderHistoryList = paymentHistoryAtYear.getOrderHistoryList();
        assertThat(orderHistoryList.size()).isEqualTo(2);

        OrderHistory orderHistory = orderHistoryList.getFirst();
        assertThat(orderHistory).extracting("orderedDate", "totalPrice")
                .containsExactlyInAnyOrder( LocalDate.of(2024, 1,30), 8500);

        OrderHistory twoAmericanoOrderHistory = orderHistoryList.get(1);
        assertThat(twoAmericanoOrderHistory).extracting("orderedDate", "totalPrice")
                .containsExactlyInAnyOrder( LocalDate.of(2024, 3,1), 8000);

    }

    @Test
    @DisplayName("월에 해당하는 주문내역을 확인할 수 있다.")
    void getPaymentHistoryAtMonth() {
        // given
        CoffeeOrder oneEspresso = CoffeeOrder.of("에스프레소", 1);        // 주문할 커피
        CoffeeOrder oneCafeLatte = CoffeeOrder.of("카페라떼", 1);        // 주문할 커피
        todtn.orderCoffee("쌩스타", LocalDate.of(2024, 1,30), oneEspresso, oneCafeLatte);

        CoffeeOrder twoAmericano = CoffeeOrder.of("아메리카노", 2);        // 주문할 커피
        todtn.orderCoffee("쌩스타", LocalDate.of(2024, 3,1), twoAmericano);

        CoffeeOrder twoCafeLatte = CoffeeOrder.of("카페라떼", 2);        // 주문할 커피
        todtn.orderCoffee("쌩술타", LocalDate.of(2024, 7,1), twoCafeLatte);

        // when
        PaymentHistory paymentHistoryAtYear = todtn.getPaymentHistoryAtMonth(2024, 7);

        // then
        List<OrderHistory> orderHistoryList = paymentHistoryAtYear.getOrderHistoryList();

        assertThat(orderHistoryList.size()).isEqualTo(1);

        OrderHistory orderHistory = orderHistoryList.getFirst();
        assertThat(orderHistory).extracting("orderedDate", "totalPrice")
                .containsExactlyInAnyOrder(LocalDate.of(2024, 7, 1), 9000);

        CoffeeOrder coffeeOrder = orderHistory.getCoffeeOrderList().getFirst();

        assertThat(coffeeOrder).extracting("menu", "count")
                .containsExactlyInAnyOrder(Menu.CAFELATTE, 2);
    }

    @Test
    @DisplayName("손님을 등록하고 조회할 수 있다.")
    void createMemberTest() {
        //given
        String customerName = "쌩수";

        //when
        Customer customer = Customer.createCustomer(customerName);

        //then
        Customer findCustomer = CustomerRepository.getCustomer(customerName);
        assertThat(customer).isEqualTo(findCustomer);
    }
}