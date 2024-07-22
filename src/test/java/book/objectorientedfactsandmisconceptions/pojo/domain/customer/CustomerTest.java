package book.objectorientedfactsandmisconceptions.pojo.domain.customer;

import book.objectorientedfactsandmisconceptions.pojo.domain.CoffeeOrder;
import book.objectorientedfactsandmisconceptions.pojo.domain.Customer;
import book.objectorientedfactsandmisconceptions.pojo.domain.OrderHistory;
import book.objectorientedfactsandmisconceptions.pojo.domain.PaymentHistory;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CustomerTest {

    @Test
    @DisplayName("커피를 주문한 내역을 확인할 수 있다.")
    void getPaymentHistoryAtDay() {
        //given
        Customer todtn = Customer.createCustomer("쌩수");                         // 고객
        CoffeeOrder threeAmericano = CoffeeOrder.of("아메리카노", 3);        // 주문할 커피
        todtn.orderCoffee(List.of(threeAmericano), "쌩스타");                 // 커피 주문

        //when
        PaymentHistory paymentHistoryAtDay = todtn.getPaymentHistoryAtDay(LocalDate.now());

        List<OrderHistory> orderHistoryList = paymentHistoryAtDay.getOrderHistoryList();

        //then
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

}