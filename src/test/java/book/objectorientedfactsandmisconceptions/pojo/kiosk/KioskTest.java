package book.objectorientedfactsandmisconceptions.pojo.kiosk;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.history.PurchaseHistory;
import book.objectorientedfactsandmisconceptions.pojo.history.SalesHistory;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class KioskTest {

    Kiosk kiosk = Kiosk.getInstance();

    final Menu americano = Menu.AMERICANO;
    final Menu espresso = Menu.ESPRESSO;
    final Menu cafeLatte = Menu.CAFE_LATTE;

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
    void historyPurchaseOfYear() {
    }

    @Test
    void historyPurchaseOfMonth() {
    }

    @Test
    void historyPurchaseOfDay() {
    }

    @Test
    void historySalesOfYear() {
    }

    @Test
    void historySalesOfMonth() {
    }

    @Test
    void historySalesOfDay() {
    }
}