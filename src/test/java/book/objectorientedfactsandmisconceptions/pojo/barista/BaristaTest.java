package book.objectorientedfactsandmisconceptions.pojo.barista;

import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BaristaTest {

    final Menu americano = Menu.AMERICANO;
    final Menu espresso = Menu.ESPRESSO;
    final Menu cafeLatte = Menu.CAFE_LATTE;


    @Test
    @DisplayName("커피를 제조할 수 있다")
    void makeCoffeeTest() {
        //given

        OrderItem orderAmericano = new OrderItem(americano, 2);
        OrderItem orderEspresso = new OrderItem(espresso, 1);

        //when
        List<Coffee> coffees = Barista.makeCoffee(List.of(orderAmericano, orderEspresso));

        //then
        assertThat(coffees).hasSize(3);
        assertThat(coffees.get(0)).extracting("name", "price")
                .containsExactlyInAnyOrder("아메리카노", 4000);
        assertThat(coffees.get(1)).extracting("name", "price")
                .containsExactlyInAnyOrder("아메리카노", 4000);
        assertThat(coffees.get(2)).extracting("name", "price")
                .containsExactlyInAnyOrder("에스프레소", 4000);


    }
}