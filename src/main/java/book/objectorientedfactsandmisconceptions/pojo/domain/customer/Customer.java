package book.objectorientedfactsandmisconceptions.pojo.domain.customer;

import book.objectorientedfactsandmisconceptions.pojo.domain.Menu;
import book.objectorientedfactsandmisconceptions.pojo.domain.order.Order;
import book.objectorientedfactsandmisconceptions.pojo.domain.barista.Barista;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.usecase.CustomerUsecase;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class Customer implements CustomerUsecase {

    private String name;    // 고객 이름
    private List<OrderHistory> orderHistories = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public Menu[] getCoffeeMenu() {
        Menu[] sellingCoffeese = Menu.values();

        for (Menu menu : sellingCoffeese) {
            System.out.println("커피 = " + menu.getName() + ", 가격 = " + menu.getPrice());
        }
        return Menu.values();
    }

    public List<Coffee> orderCoffee(Order[] orders, String name) {
        Barista barista = Barista.of(name);
        List<Coffee> coffees = barista.makeCoffee(orders);

        // 주문내역에 추가
        addOrderHistory(orders);

        return coffees;
    }

    // 주문내역 추가 private Method
    private void addOrderHistory(Order[] orders) {
        orderHistories.add(OrderHistory.of(orders));
    }

    // 주문한 내역의 요금을 구한다.
    private int getTotalPrice(Order[] orders) {
        int orderPrice = 0;
        for(int index = 0; index < orders.length; index++) {
            Order order = orders[index];
            orderPrice += order.getMenu().getPrice() * order.getCount();
        }
        return orderPrice;
    }

}
