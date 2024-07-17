package book.objectorientedfactsandmisconceptions.pojo.domain.customer;

import book.objectorientedfactsandmisconceptions.pojo.domain.Menu;
import book.objectorientedfactsandmisconceptions.pojo.domain.order.Order;
import book.objectorientedfactsandmisconceptions.pojo.domain.barista.Barista;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.usecase.CustomerUsecase;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class Customer implements CustomerUsecase {

    private String name;    // 이름
    private Map<LocalDate, OrderHistory> orderHistory = new HashMap<>();

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
        OrderHistory todayOrderHistory = orderHistory.get(LocalDate.now());
        todayOrderHistory.getOrders().addAll(List.of(orders));
        orderHistory.put(LocalDate.now(), todayOrderHistory);
    }

    // 주문한 내역의 요금을 구한다.
    private int getTotalPrice(Order[] orders) {
        int orderTotalPrice = 0;
        for(int index = 0; index < orders.length; index++) {
            Order order = orders[index];
            orderTotalPrice += order.getMenu().getPrice() * order.getCount();
        }
        return orderTotalPrice;
    }

    // 3. 주문 내역을 확인할 수 있다.
    public List<OrderHistory> getOrderHistory() {
        return orderHistoris;
    }

}
