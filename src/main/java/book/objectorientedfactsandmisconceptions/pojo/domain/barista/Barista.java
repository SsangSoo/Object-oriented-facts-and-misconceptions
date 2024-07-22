package book.objectorientedfactsandmisconceptions.pojo.domain.barista;


import book.objectorientedfactsandmisconceptions.pojo.domain.global.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.global.Menu;
import book.objectorientedfactsandmisconceptions.pojo.domain.global.CoffeeOrder;
import book.objectorientedfactsandmisconceptions.pojo.responsebillity.BaristaResponsibillity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class Barista implements BaristaResponsibillity {

    private String name;
    public Map<LocalDate, List<CoffeeOrder>> saleHistoris = new HashMap<>();    // 판매 내역


    private Barista(String name) {
        this.name = name;
    }

    public static Barista of(String name) {
        return new Barista(name);
    }

    // 1. 주문받은 커피를 제조함.
        // 제조하면서 판매 내역을 담는다.
    @Override
    public List<Coffee> makeCoffee(List<CoffeeOrder> coffeeOrders) {
        List<Coffee> coffees = new ArrayList<>();

        for(int index = 0; index < coffeeOrders.size(); index++) {
            CoffeeOrder coffeeOrder = coffeeOrders.get(index);
            Menu choiceCoffee = coffeeOrder.getMenu();
            addingCoffee(coffees, choiceCoffee, coffeeOrder.getCount());
        }
        this.saleHistoris.put(LocalDate.now(), coffeeOrders); // 제조하면서 판매 내역에 담음.
        return coffees;
    }

    // 주문 메뉴 개수만큼 커피를 제조하여 담는다.
    private void addingCoffee(List<Coffee> coffees, Menu choiceCoffee, int count) {
        for(int i=0; i < count; i++) {
            coffees .add(Coffee.of(choiceCoffee.getCoffee(), choiceCoffee.getPrice()));
        }
    }


    private static String getMakeCoffeeMenu(String coffee) {
        String makeCoffee = "AMERICANO";
        for(Menu menu : Menu.values()) {
            if(menu.getCoffee().equals(coffee)) {
                makeCoffee = menu.name();
            }
        }
        return makeCoffee;
    }
}