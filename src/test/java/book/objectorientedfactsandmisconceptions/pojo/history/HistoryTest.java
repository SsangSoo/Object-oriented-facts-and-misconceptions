package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;
import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElementOfDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class HistoryTest {

    final Menu AMERICANO = Menu.AMERICANO;
    final Menu ESPRESSO = Menu.ESPRESSO;
    final Menu CAFE_LATTE = Menu.CAFE_LATTE;

    @Test
    @DisplayName("판매 내역 확인 테스트")
    void historyTest() {
        // given
        LocalDate date13 = LocalDate.of(2024, 8, 13);
        OrderItem orderItemAmericano13 = new OrderItem(AMERICANO, 3);
        OrderItem orderItemCafeLatte13 = new OrderItem(CAFE_LATTE, 1);
        HistoryElement historyElement13 = new HistoryElement(date13, List.of(orderItemAmericano13, orderItemCafeLatte13));


        LocalDate date15 = LocalDate.of(2024, 8, 15);
        OrderItem orderItemAmericano15 = new OrderItem(AMERICANO, 2);
        OrderItem orderItemEspresso15 = new OrderItem(ESPRESSO, 2);
        OrderItem orderItemCafeLatte15 = new OrderItem(CAFE_LATTE, 2);
        HistoryElement historyElement15 = new HistoryElement(date15, List.of(orderItemAmericano15, orderItemEspresso15, orderItemCafeLatte15));

        List<HistoryElement> histories = List.of(historyElement13, historyElement15);

        //when
        History history = History.of(histories);

        //then
        assertThat(history.getHistoriesOfDay()).isNull();
        assertThat(history.getHistories()).isEqualTo(histories);
        assertThat(history.getHistories()).hasSize(2);
        assertThat(history.getTotalPrice()).isEqualTo(41500);

        assertThat(historyElement13.getTotalPrice()).isEqualTo(16500);
        assertThat(historyElement13.getOrderItem()).hasSize(2);
        assertThat(historyElement13.getDate()).isEqualTo(date13);


        assertThat(historyElement15.getTotalPrice()).isEqualTo(25000);
        assertThat(historyElement15.getOrderItem()).hasSize(3);
        assertThat(historyElement15.getDate()).isEqualTo(date15);
    }

    @Test
    @DisplayName("일일 판매 내역 확인 테스트")
    void historyOfDayTest() {
        // given
        LocalDate date13 = LocalDate.of(2024, 8, 13);
        LocalDateTime date1311 = LocalDateTime.of(date13, LocalTime.of(11, 0));
        OrderItem orderItemAmericano1311 = new OrderItem(AMERICANO, 1);
        HistoryElementOfDay historyElementOfDay1311 = new HistoryElementOfDay(date1311.toLocalTime(), List.of(orderItemAmericano1311));

        LocalDateTime date1313 = LocalDateTime.of(date13, LocalTime.of(13, 0));
        OrderItem orderItemEspresso1313 = new OrderItem(ESPRESSO, 1);
        HistoryElementOfDay historyElementOfDay1313 = new HistoryElementOfDay(date1313.toLocalTime(), List.of(orderItemEspresso1313));

        LocalDateTime date1315 = LocalDateTime.of(date13, LocalTime.of(15, 0));
        OrderItem orderItemCafeLatte1315 = new OrderItem(CAFE_LATTE, 1);
        HistoryElementOfDay historyElementOfDay1315 = new HistoryElementOfDay(date1315.toLocalTime(), List.of(orderItemCafeLatte1315));

        List<HistoryElementOfDay> historyElementOfDay = List.of(
                historyElementOfDay1311,
                historyElementOfDay1313,
                historyElementOfDay1315);

        // when
        History history = History.ofDay(historyElementOfDay);

        //then
        assertThat(history.getHistoriesOfDay()).isEqualTo(historyElementOfDay);
        assertThat(history.getHistories()).isNull();
        assertThat(history.getHistoriesOfDay()).hasSize(3);
        assertThat(history.getTotalPrice()).isEqualTo(12500);

        assertThat(historyElementOfDay1311.getTotalPriceOfDay()).isEqualTo(4000);
        assertThat(historyElementOfDay1311.getOrderItem()).hasSize(1);
        assertThat(historyElementOfDay1311.getOrderTime()).isEqualTo(LocalTime.of(11, 0));


        assertThat(historyElementOfDay1313.getTotalPriceOfDay()).isEqualTo(4000);
        assertThat(historyElementOfDay1313.getOrderItem()).hasSize(1);
        assertThat(historyElementOfDay1313.getOrderTime()).isEqualTo(LocalTime.of(13, 0));


        assertThat(historyElementOfDay1315.getTotalPriceOfDay()).isEqualTo(4500);
        assertThat(historyElementOfDay1315.getOrderItem()).hasSize(1);
        assertThat(historyElementOfDay1315.getOrderTime()).isEqualTo(LocalTime.of(15, 0));


    }

}