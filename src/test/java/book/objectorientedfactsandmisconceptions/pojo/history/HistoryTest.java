package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
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
        OrderInfo orderInfoAmericano13 = new OrderInfo(AMERICANO, 3);
        OrderInfo orderInfoCafeLatte13 = new OrderInfo(CAFE_LATTE, 1);
        HistoryElement historyElement13 = new HistoryElement(date13, List.of(orderInfoAmericano13, orderInfoCafeLatte13));


        LocalDate date15 = LocalDate.of(2024, 8, 15);
        OrderInfo orderInfoAmericano15 = new OrderInfo(AMERICANO, 2);
        OrderInfo orderInfoEspresso15 = new OrderInfo(ESPRESSO, 2);
        OrderInfo orderInfoCafeLatte15 = new OrderInfo(CAFE_LATTE, 2);
        HistoryElement historyElement15 = new HistoryElement(date15, List.of(orderInfoAmericano15, orderInfoEspresso15, orderInfoCafeLatte15));

        List<HistoryElement> histories = List.of(historyElement13, historyElement15);

        //when
        History history = History.of(histories);

        //then
        assertThat(history.getHistoriesOfDay()).isNull();
        assertThat(history.getHistories()).isEqualTo(histories);
        assertThat(history.getHistories()).hasSize(2);
        assertThat(history.getTotalPrice()).isEqualTo(41500);

        assertThat(historyElement13.getTotalPrice()).isEqualTo(16500);
        assertThat(historyElement13.getOrderInfo()).hasSize(2);
        assertThat(historyElement13.getDate()).isEqualTo(date13);


        assertThat(historyElement15.getTotalPrice()).isEqualTo(25000);
        assertThat(historyElement15.getOrderInfo()).hasSize(3);
        assertThat(historyElement15.getDate()).isEqualTo(date15);
    }

    @Test
    @DisplayName("일일 판매 내역 확인 테스트")
    void historyOfDayTest() {
        // given
        LocalDate date13 = LocalDate.of(2024, 8, 13);
        LocalDateTime date1311 = LocalDateTime.of(date13, LocalTime.of(11, 0));
        OrderInfo orderInfoAmericano1311 = new OrderInfo(AMERICANO, 1);
        HistoryElementOfDay historyElementOfDay1311 = new HistoryElementOfDay(date1311.toLocalTime(), List.of(orderInfoAmericano1311));

        LocalDateTime date1313 = LocalDateTime.of(date13, LocalTime.of(13, 0));
        OrderInfo orderInfoEspresso1313 = new OrderInfo(ESPRESSO, 1);
        HistoryElementOfDay historyElementOfDay1313 = new HistoryElementOfDay(date1313.toLocalTime(), List.of(orderInfoEspresso1313));

        LocalDateTime date1315 = LocalDateTime.of(date13, LocalTime.of(15, 0));
        OrderInfo orderInfoCafeLatte1315 = new OrderInfo(CAFE_LATTE, 1);
        HistoryElementOfDay historyElementOfDay1315 = new HistoryElementOfDay(date1315.toLocalTime(), List.of(orderInfoCafeLatte1315));

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
        assertThat(historyElementOfDay1311.getOrderInfo()).hasSize(1);
        assertThat(historyElementOfDay1311.getOrderTime()).isEqualTo(LocalTime.of(11, 0));


        assertThat(historyElementOfDay1313.getTotalPriceOfDay()).isEqualTo(4000);
        assertThat(historyElementOfDay1313.getOrderInfo()).hasSize(1);
        assertThat(historyElementOfDay1313.getOrderTime()).isEqualTo(LocalTime.of(13, 0));


        assertThat(historyElementOfDay1315.getTotalPriceOfDay()).isEqualTo(4500);
        assertThat(historyElementOfDay1315.getOrderInfo()).hasSize(1);
        assertThat(historyElementOfDay1315.getOrderTime()).isEqualTo(LocalTime.of(15, 0));


    }

}