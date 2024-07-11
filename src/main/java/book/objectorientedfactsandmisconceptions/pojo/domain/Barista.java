package book.objectorientedfactsandmisconceptions.pojo.domain;


import book.objectorientedfactsandmisconceptions.pojo.domain.usecase.BaristaUsecase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 바리스타
 * 책임 1. 주문 받은 커피를 제조한다.
 *      주문 받은 커피 내역을 판매 내역에 저장한다.
 */
@Getter
public class Barista implements BaristaUsecase {

    private String name;
    public List<SalesHistory> orderHistoris = new ArrayList<>();    // 주문내역

    private Barista(String name) {
        this.name = name;
    }

    public static Barista of(String name) {
        return new Barista(name);
    }

    // 1. 주문받은 커피를 제조함.
    @Override
    public List<Coffee> makeCoffee(Order[] orders) {
        List<Coffee> coffees = new ArrayList<>();

        for(int index = 0; index < orders.length; index++) {
            Order order = orders[index];
            Menu choiceCoffee = Menu.valueOf(order.getMenu().getName());
            addingCoffee(coffees, choiceCoffee, order.getCount());
        }
        return coffees;
    }

    // 주문 메뉴 개수만큼 커피를 제조하여 담는다.
    private void addingCoffee(List<Coffee> coffees, Menu choiceCoffee, int count) {
        for(int i=0; i < count; i++) {
            coffees .add(Coffee.of(choiceCoffee.getName(), choiceCoffee.getPrice()));
        }
    }

}