package book.objectorientedfactsandmisconceptions.pojo.domain.barista;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 판매 내역을 보여주기 위한 정보
 */
@Getter
public class SalesHistory {

    private LocalDate saleDate;
    private int totalPrice;
//    private List<CoffeeCount> coffees;

    @Builder
    private SalesHistory(LocalDate saleDate, int totalPrice
//            , List<CoffeeCount> coffees
    ) {
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
//        this.coffees = coffees;
    }

    public book.objectorientedfactsandmisconceptions.pojo.domain.barista.SalesHistory of(LocalDate saleDate, int totalPrice
//            , List<CoffeeCount> coffees
    ) {
        return book.objectorientedfactsandmisconceptions.pojo.domain.barista.SalesHistory.builder()
                .saleDate(saleDate)
                .totalPrice(totalPrice)
//                .coffees(coffees)
                .build();
    }

}
