package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PurchaseHistoryTest {

    final Menu americano = Menu.AMERICANO;
    final Menu espresso = Menu.ESPRESSO;
    final Menu cafeLatte = Menu.CAFE_LATTE;

    @Test
    @DisplayName("구매내역 저장 후, 결과 확인")
    void purchaseHistoryTest1() {
        //given
        String phone = "01012341234";
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
        PurchaseHistory purchaseHistory = PurchaseHistory.of(phone, histories);

        //then

        assertThat(purchaseHistory.getPhone()).isEqualTo(phone);
        assertThat(purchaseHistory.getHistories()).hasSize(2);
        assertThat(purchaseHistory.getTotalPrice()).isEqualTo(41500);
    }

    @Test
    @DisplayName("쿠폰을 6장 사용했다고 가정하고 구매내역 확인")
    void purchaseHistoryTest2() {
        //given
        String phone = "01012341234";
        LocalDateTime date13 = LocalDateTime.of(2024, 8, 13, 0, 0);
        OrderItem orderItemAmericano13 = new OrderItem(americano, 3);
        OrderItem orderItemCafeLatte13 = new OrderItem(cafeLatte, 1);
        History history13 = new History(date13, List.of(orderItemAmericano13, orderItemCafeLatte13));


        LocalDateTime date15 = LocalDateTime.of(2024, 8, 15, 0, 0);
        OrderItem orderItemAmericano15 = new OrderItem(americano, 2);
        OrderItem orderItemEspresso15 = new OrderItem(espresso, 2);
        OrderItem orderItemCafeLatte15 = new OrderItem(cafeLatte, 2);
        History history15 = new History(date15, List.of(orderItemAmericano15, orderItemEspresso15, orderItemCafeLatte15),1000);

        List<History> histories = List.of(history13, history15);

        //when
        PurchaseHistory purchaseHistory = PurchaseHistory.of(phone, histories);

        //then
        assertThat(purchaseHistory.getPhone()).isEqualTo(phone);
        assertThat(purchaseHistory.getHistories()).hasSize(2);
        assertThat(purchaseHistory.getTotalPrice()).isEqualTo(17500);
    }

}