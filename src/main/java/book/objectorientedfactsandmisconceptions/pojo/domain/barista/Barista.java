package book.objectorientedfactsandmisconceptions.pojo.domain.barista;


import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.domain.order.Order;
import book.objectorientedfactsandmisconceptions.pojo.usecase.BaristaResponsibillity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class Barista implements BaristaResponsibillity {

    private String name;
    public Map<LocalDate, List<Order>> saleHistoris = new HashMap<>();    // 판매 내역


    private Barista(String name) {
        this.name = name;
    }

    public static Barista of(String name) {
        return new Barista(name);
    }

    // 1. 주문받은 커피를 제조함.
        // 제조하면서 판매 내역을 담는다.
    @Override
    public List<Coffee> makeCoffee(Order[] orders) {
        List<Coffee> coffees = new ArrayList<>();

        for(int index = 0; index < orders.length; index++) {
            Order order = orders[index];
            Menu choiceCoffee = Menu.valueOf(order.getMenu().getName());
            addingCoffee(coffees, choiceCoffee, order.getCount());
        }
        this.saleHistoris.put(LocalDate.now(), List.of(orders)); // 제조하면서 판매 내역에 담음.
        return coffees;
    }

    // 주문 메뉴 개수만큼 커피를 제조하여 담는다.
    private void addingCoffee(List<Coffee> coffees, Menu choiceCoffee, int count) {
        for(int i=0; i < count; i++) {
            coffees .add(Coffee.of(choiceCoffee.getName(), choiceCoffee.getPrice()));
        }
    }

}