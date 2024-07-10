package book.objectorientedfactsandmisconceptions.domain;

import book.objectorientedfactsandmisconceptions.domain.usecase.SellHistoryUsecase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SellHistory implements SellHistoryUsecase {

    private Map<LocalDate, List<Coffee>> sellingHistory = new HashMap<>();

    @Override
    public void addSellHistory(List<Coffee> coffees) {
        List<Coffee> todayCoffees = sellingHistory.get(LocalDate.now());
        todayCoffees.addAll(coffees);
    }

    @Override
    public List<OrderHistory> getSellHistory() {
        return List.of();
    }
}
