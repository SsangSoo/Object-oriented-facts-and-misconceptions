package book.objectorientedfactsandmisconceptions.pojo.customer;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.coupon.CouponInfo;
import book.objectorientedfactsandmisconceptions.pojo.history.History;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    @DisplayName("고객을 생성할 수 있다.")
    void createCustomerTest() {
        //given
        String phone = "01012341234";

        //when
        Customer customer = Customer.create(phone);

        //then
        assertThat(customer).isNotNull();
        assertThat(customer.getPhone()).isEqualTo(phone);
        assertThat(customer.getOrderRepository()).hasSize(0);

        CouponInfo couponInfo = customer.getCouponInfo();
        assertThat(couponInfo.getCoupon()).isZero();
        assertThat(couponInfo.getStamp()).isZero();

    }

    @Test
    @DisplayName("고객의 주문 정보를 저장할 수 있다.")
    void addInfoTest() {
        //given
        String phone = "01012341234";
        Customer customer = Customer.create(phone);

        LocalDateTime date = LocalDateTime.of(2024, 8, 13, 0, 0);
        OrderItem orderItemAmericano = new OrderItem(Menu.AMERICANO, 3);
        OrderItem orderItemCafeLatte = new OrderItem(Menu.CAFE_LATTE, 1);

        History history = new History(date, List.of(orderItemAmericano, orderItemCafeLatte));

        //when
        customer.addOrderInfo(history);

        //then
        assertThat(customer).isNotNull();
        assertThat(customer.getPhone()).isEqualTo(phone);
        assertThat(customer.getOrderRepository()).hasSize(1);
    }
}