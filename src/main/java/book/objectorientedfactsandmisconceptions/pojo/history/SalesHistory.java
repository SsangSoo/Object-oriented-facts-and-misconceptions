package book.objectorientedfactsandmisconceptions.pojo.history;

import book.objectorientedfactsandmisconceptions.pojo.coffee.CoffeeOrder;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * 판매 내역을 보여주기 위한 정보
 */
@Getter
public class SalesHistory {

    private LocalDate date;
    private int totalPrice;
    private List<CoffeeOrder> orderList;


    @Builder
    private SalesHistory(LocalDate date, int totalPrice, List<CoffeeOrder> orderList) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.orderList = orderList;
    }

    public SalesHistory of(LocalDate date, int totalPrice, List<CoffeeOrder> orderList) {
        return SalesHistory.builder()
                .date(date)
                .totalPrice(totalPrice)
                .orderList(orderList)
                .build();
    }

}
