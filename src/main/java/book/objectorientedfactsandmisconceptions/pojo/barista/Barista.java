package book.objectorientedfactsandmisconceptions.pojo.barista;


import book.objectorientedfactsandmisconceptions.pojo.coffee.CoffeeOrder;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.barista.usecase.BaristaInterface;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;


@Getter
public class Barista implements BaristaInterface {

    private String name;
    private final Map<LocalDate, List<CoffeeOrder>> saleHistoris = new HashMap<>();    // 판매 내역

    private Barista(String name) {
        this.name = name;
    }

    public static Barista of(String name) {
        return new Barista(name);
    }

    // 1. 주문받은 커피를 제조함.
        // 제조하면서 판매 내역을 담는다.
    @Override
    public List<Coffee> makeCoffee(List<CoffeeOrder> coffeeOrders, LocalDate orderDate) {
        List<Coffee> coffees = new ArrayList<>();

        for(int index = 0; index < coffeeOrders.size(); index++) {
            CoffeeOrder coffeeOrder = coffeeOrders.get(index);
            Menu choiceCoffee = coffeeOrder.getMenu();
            addingCoffee(coffees, choiceCoffee, coffeeOrder.getCount());
        }
        this.saleHistoris.put(getLocalDate(orderDate), coffeeOrders); // 제조하면서 판매 내역에 담음.
        return coffees;
    }

    // 주문 일자가 null이라면 오늘 날짜로 설정
    private static LocalDate getLocalDate(LocalDate orderDate) {
        if(orderDate == null) {
            orderDate = LocalDate.now();
        }
        return orderDate;
    }

    // 주문 메뉴 개수만큼 커피를 제조하여 담는다.
    private void addingCoffee(List<Coffee> coffees, Menu choiceCoffee, int count) {
        for(int i=0; i < count; i++) {
            coffees .add(Coffee.of(choiceCoffee.getCoffee(), choiceCoffee.getPrice()));
        }
    }

}