package book.objectorientedfactsandmisconceptions.pojo.order;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class OrderItemTest {

    final Menu americano = Menu.AMERICANO;
    final Menu espresso = Menu.ESPRESSO;
    final Menu cafeLatte = Menu.CAFE_LATTE;

    @Test
    @DisplayName("OrderItem생성 테스트")
    void creatOrderItemTest() {
        //given //when
        OrderItem createOrder = new OrderItem("아메리카노", 2);
        OrderItem createOrderByMenuString = new OrderItem(americano.getName(), 2);
        OrderItem createOrderByMenu = new OrderItem(americano, 2);

        //then
        assertThat(createOrder.getQuantity()).isEqualTo(2);
        assertThat(createOrder.getCoffee()).isEqualTo("아메리카노");
        assertThat(createOrderByMenuString.getCoffee()).isEqualTo("아메리카노");
        assertThat(createOrderByMenuString.getQuantity()).isEqualTo(2);
        assertThat(createOrderByMenu.getCoffee()).isEqualTo("아메리카노");
        assertThat(createOrderByMenu.getQuantity()).isEqualTo(2);
    }

    @Test
    @DisplayName("OrderItem생성시 없는 메뉴를 선택하면 예외가 발생한다.")
    void creatOrderItemExceptionTest() {
        //given
        String coffee = "아프리카노";

        // when // then
        assertThatThrownBy(() -> new OrderItem(coffee, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BusinessException.INVALID_COFFEE.getMessage() + " : " + coffee);

    }

}