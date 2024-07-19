package book.objectorientedfactsandmisconceptions.pojo.domain.customer;

import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.order.CoffeeOrder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CustomerTest {

    @Test
    void getPaymentHistoryAtDay() {
        //given
        Customer todtn = Customer.createCustomer("쌩수");                     // 고객
        CoffeeOrder threeAmericano = CoffeeOrder.of("아메리카노", 3);    // 주문할 커피
        todtn.orderCoffee(List.of(threeAmericano), "쌩스타");              // 커피 주문

        //when
        PaymentHistory paymentHistoryAtDay = todtn.getPaymentHistoryAtDay(LocalDate.now());

        List<OrderHistory> orderHistoryList = paymentHistoryAtDay.getOrderHistoryList();

        //then
        assertThat(orderHistoryList.size()).isEqualTo(1);

//        OrderHistory orderHistory = orderHistoryList.getFirst();
//        assertThat(orderHistory).isExactlyInstanceOf()
//                .
    }

}