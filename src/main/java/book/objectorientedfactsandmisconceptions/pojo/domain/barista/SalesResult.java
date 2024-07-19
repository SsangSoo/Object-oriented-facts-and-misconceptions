package book.objectorientedfactsandmisconceptions.pojo.domain.barista;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * 판매 내역을 보여주기 위한 정보
 */
@Getter
public class SalesResult {

    private LocalDate saleDate;
    private int totalPrice;
//    private List<CoffeeCount> coffees;

    @Builder
    private SalesResult(LocalDate saleDate, int totalPrice
//            , List<CoffeeCount> coffees
    ) {
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
//        this.coffees = coffees;
    }

    public SalesResult of(LocalDate saleDate, int totalPrice
//            , List<CoffeeCount> coffees
    ) {
        return SalesResult.builder()
                .saleDate(saleDate)
                .totalPrice(totalPrice)
//                .coffees(coffees)
                .build();
    }

}
