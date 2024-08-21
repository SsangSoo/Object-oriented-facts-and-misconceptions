package book.objectorientedfactsandmisconceptions.pojo.order;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class OrderInfoTest {

    final Menu americano = Menu.AMERICANO;
    final Menu espresso = Menu.ESPRESSO;
    final Menu cafeLatte = Menu.CAFE_LATTE;


    @Test
    @DisplayName("OrderInfo 생성 테스트")
    void createOrderInfoTest() {
        OrderItem orderItemAmericano = new OrderItem(americano, 3);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        List<OrderItem> orderItems = List.of(orderItemAmericano, orderItemCafeLatte);

        //when
        OrderInfo orderInfo = new OrderInfo(orderItems, true);

        //then
        assertThat(orderInfo.isCouponUse()).isTrue();
        assertThat(orderInfo.getItems()).isNotNull();
        assertThat(orderInfo.getItems()).hasSize(2);

        assertThat(orderInfo.getItems().get(0).getCoffee()).isEqualTo(americano.getName());
        assertThat(orderInfo.getItems().get(0).getQuantity()).isEqualTo(3);
        assertThat(orderInfo.getItems().get(1).getCoffee()).isEqualTo(cafeLatte.getName());
        assertThat(orderInfo.getItems().get(1).getQuantity()).isEqualTo(1);

        assertThat(orderInfo.getDiscount()).isZero();
        assertThat(orderInfo.getPaymentPrice()).isEqualTo(16500);
        assertThat(orderInfo.getTotalPrice()).isEqualTo(16500);

    }

    @Test
    @DisplayName("OrderInfo 생성 테스트")
    void createOrderInfoTest2() {
        OrderItem orderItemAmericano = new OrderItem(americano, 3);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        List<OrderItem> orderItems = List.of(orderItemAmericano, orderItemCafeLatte);

        //when
        OrderInfo orderInfo = new OrderInfo(orderItems, false);

        //then
        assertThat(orderInfo.isCouponUse()).isFalse();
    }

    @Test
    @DisplayName("쿠폰 사용여부가 false일 때 쿠폰을 사용하려고 하면 예외가 발생한다.")
    void applyCouponExceptionTest() {
        OrderItem orderItemAmericano = new OrderItem(americano, 3);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        List<OrderItem> orderItems = List.of(orderItemAmericano, orderItemCafeLatte);
        OrderInfo orderInfo = new OrderInfo(orderItems, false);

        //when //then
        assertThatThrownBy(() -> orderInfo.applyCoupon(2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("쿠폰을 사용할 수 없습니다.");

    }

    @Test
    @DisplayName("쿠폰 사용 테스트")
    void applyCouponTest() {
        OrderItem orderItemAmericano = new OrderItem(americano, 3);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        List<OrderItem> orderItems = List.of(orderItemAmericano, orderItemCafeLatte);
        OrderInfo orderInfo = new OrderInfo(orderItems, true);

        //when
        orderInfo.applyCoupon(2);

        // then
        assertThat(orderInfo.getDiscount()).isEqualTo(8000);
        assertThat(orderInfo.getTotalPrice()).isEqualTo(16500);
        assertThat(orderInfo.getPaymentPrice()).isEqualTo(8500);
        assertThat(orderInfo.isCouponUse()).isTrue();


    }
}