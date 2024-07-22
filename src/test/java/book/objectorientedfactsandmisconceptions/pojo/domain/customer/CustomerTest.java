package book.objectorientedfactsandmisconceptions.pojo.domain.customer;

import book.objectorientedfactsandmisconceptions.pojo.domain.global.CoffeeOrder;
import book.objectorientedfactsandmisconceptions.pojo.domain.global.Menu;
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
    @DisplayName("커피를 주문한 내역을 일자에 맞게 확인할 수 있다.")
    void getPaymentHistoryAtDayTest() {
        //given
        CoffeeOrder threeAmericano = CoffeeOrder.of("아메리카노", 3);        // 주문할 커피
        todtn.orderCoffee(List.of(threeAmericano), "쌩스타");                 // 커피 주문

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
        todtn.orderCoffee(List.of(oneEspresso, oneCafeLatte), "쌩스타", LocalDate.of(2024, 1,30));

        CoffeeOrder twoAmericano = CoffeeOrder.of("아메리카노", 2);        // 주문할 커피
        todtn.orderCoffee(List.of(twoAmericano), "쌩스타", LocalDate.of(2024, 3,1));

        // when
        PaymentHistory paymentHistoryAtYear = todtn.getPaymentHistoryAtYear(2024);

        // then
        List<OrderHistory> orderHistoryList = paymentHistoryAtYear.getOrderHistoryList();

        assertThat(orderHistoryList.size()).isEqualTo(2);

//        OrderHistory orderHistory = orderHistoryList.getFirst();                        // 주문 내역 안에 12000원과 주문한 날짜(오늘)
//        assertThat(orderHistory).extracting("orderedDate", "totalPrice")
//                .containsExactlyInAnyOrder(LocalDate.now(), 12000);
//
//        assertThat(orderHistory.getCoffeeOrderList().size()).isEqualTo(1);
//        CoffeeOrder coffeeOrder = orderHistory.getCoffeeOrderList().getFirst();
//        assertThat(coffeeOrder).extracting("menu", "count")
//                .containsExactlyInAnyOrder(Menu.AMERICANO, 3);
//
//        assertThat(coffeeOrder.getMenu()).extracting("coffee", "price")
//                .containsExactlyInAnyOrder("아메리카노", 4000);
    }

}