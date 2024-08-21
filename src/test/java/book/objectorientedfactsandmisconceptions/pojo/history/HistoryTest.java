package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HistoryTest {

    final Menu americano = Menu.AMERICANO;
    final Menu espresso = Menu.ESPRESSO;
    final Menu cafeLatte = Menu.CAFE_LATTE;

    @Test
    @DisplayName("판매 내역 확인 테스트")
    void historyTest() {
        // given
        LocalDateTime date13 = LocalDateTime.of(2024, 8, 13, 0, 0);
        OrderItem orderItemAmericano13 = new OrderItem(americano, 3);
        OrderItem orderItemCafeLatte13 = new OrderItem(cafeLatte, 1);
        History history13 = new History(date13, List.of(orderItemAmericano13, orderItemCafeLatte13));


        LocalDateTime date15 = LocalDateTime.of(2024, 8, 15, 0, 0);
        OrderItem orderItemAmericano15 = new OrderItem(americano, 2);
        OrderItem orderItemEspresso15 = new OrderItem(espresso, 2);
        OrderItem orderItemCafeLatte15 = new OrderItem(cafeLatte, 2);
        History history15 = new History(date15, List.of(orderItemAmericano15, orderItemEspresso15, orderItemCafeLatte15));

        List<History> histories = List.of(history13, history15);

        //when
        SalesHistory history = SalesHistory.of(histories);

        //then
        assertThat(history.getHistories()).isEqualTo(histories);
        assertThat(history.getHistories()).hasSize(2);
        assertThat(history.getTotalPrice()).isEqualTo(41500);

        assertThat(history13.getTotalPrice()).isEqualTo(16500);
        assertThat(history13.getOrderItem()).hasSize(2);
        assertThat(history13.getDate()).isEqualTo(date13);


        assertThat(history15.getTotalPrice()).isEqualTo(25000);
        assertThat(history15.getOrderItem()).hasSize(3);
        assertThat(history15.getDate()).isEqualTo(date15);
    }

    @Test
    @DisplayName("일일 판매 내역 확인 테스트")
    void historyOfDayTest() {
        // given
        LocalDate date13 = LocalDate.of(2024, 8, 13);
        LocalDateTime date1311 = LocalDateTime.of(date13, LocalTime.of(11, 0));
        OrderItem orderItemAmericano1311 = new OrderItem(americano, 1);
        History historyOfDay1311 = new History(date1311, List.of(orderItemAmericano1311));

        LocalDateTime date1313 = LocalDateTime.of(date13, LocalTime.of(13, 0));
        OrderItem orderItemEspresso1313 = new OrderItem(espresso, 1);
        History historyOfDay1313 = new History(date1313, List.of(orderItemEspresso1313));

        LocalDateTime date1315 = LocalDateTime.of(date13, LocalTime.of(15, 0));
        OrderItem orderItemCafeLatte1315 = new OrderItem(cafeLatte, 1);
        History historyOfDay1315 = new History(date1315, List.of(orderItemCafeLatte1315));

        List<History> histories = List.of(
                historyOfDay1311,
                historyOfDay1313,
                historyOfDay1315);

        // when
        SalesHistory history = SalesHistory.of(histories);

        //then
        assertThat(history.getHistories()).isEqualTo(histories);
        assertThat(history.getHistories()).isNotNull();
        assertThat(history.getHistories()).hasSize(3);
        assertThat(history.getTotalPrice()).isEqualTo(12500);

        assertThat(historyOfDay1311.getTotalPrice()).isEqualTo(4000);
        assertThat(historyOfDay1311.getOrderItem()).hasSize(1);
        assertThat(historyOfDay1311.getDate().toLocalTime()).isEqualTo(LocalTime.of(11, 0));


        assertThat(historyOfDay1313.getTotalPrice()).isEqualTo(4000);
        assertThat(historyOfDay1313.getOrderItem()).hasSize(1);
        assertThat(historyOfDay1313.getDate().toLocalTime()).isEqualTo(LocalTime.of(13, 0));


        assertThat(historyOfDay1315.getTotalPrice()).isEqualTo(4500);
        assertThat(historyOfDay1315.getOrderItem()).hasSize(1);
        assertThat(historyOfDay1315.getDate().toLocalTime()).isEqualTo(LocalTime.of(15, 0));
    }

    @Test
    @DisplayName("각 주문에 대한 계산이 계상되어 price에 저장된다.")
    void calculateTotalPriceTest() {
        //given
        LocalDateTime date = LocalDateTime.of(2024, 8, 13, 0, 0);
        OrderItem orderItemAmericano = new OrderItem(americano, 3);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        //when
        History history = new History(date, List.of(orderItemAmericano, orderItemCafeLatte));

        //then
        assertThat(history.getTotalPrice()).isEqualTo(16500);
    }

    @Test
    @DisplayName("각 주문에 대한 주문 금액을 지정하면 주문이 많더라도 지정된 금액이 저장된다. ")
    void priceByConstructorTest() {
        //given
        OrderItem orderItemAmericano = new OrderItem(americano, 3);
        OrderItem orderItemCafeLatte = new OrderItem(cafeLatte, 1);

        //when
        History history = new History(List.of(orderItemAmericano, orderItemCafeLatte), 4000);

        //then
        assertThat(history.getTotalPrice()).isEqualTo(4000);
    }

}