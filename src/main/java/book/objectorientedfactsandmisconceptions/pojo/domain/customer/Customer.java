package book.objectorientedfactsandmisconceptions.pojo.domain.customer;

import book.objectorientedfactsandmisconceptions.pojo.domain.Menu;
import book.objectorientedfactsandmisconceptions.pojo.domain.order.Order;
import book.objectorientedfactsandmisconceptions.pojo.domain.barista.Barista;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 손님의 책임
 * 책임 1. 메뉴판을 통해서 메뉴 목록을 확인할 수 있다.
 * 책임 2. 바리스타에게 커피를 주문할 수 있다(다수의 주문 가능).
 *      주문내역에 담겨야하고,
 *      바리스타로부터 제조된 커피를 얻어올 수 있어야한다.
 * 책임 3. 주문 내역을 확인할 수 있다.
 */
@Getter
public class Customer {

    private String name;                                            // 이름

    public Customer(String name) {
        this.name = name;
    }

    // 책임 1. 메뉴판을 통해서 메뉴 목록을 확인할 수 있다.
    public Menu[] getCoffeeMenu() {
        Menu[] sellingCoffeese = Menu.values();

        for (Menu menu : sellingCoffeese) {
            System.out.println("커피 = " + menu.getName() + ", 가격 = " + menu.getPrice());
        }
        return Menu.values();
    }

    // 2. 바리스타에게 커피를 주문할 수 있다.
    public List<Coffee> orderCoffee(Order[] orders, String name) {
        Barista barista = Barista.of(name);
        List<Coffee> coffees = barista.makeCoffee(orders);

        // 주문내역에 추가
        OrderHistory todayOrderHistory = orderHistoris.stream()
                .filter(orderHistory -> orderHistory.getOrderDate().isEqual(LocalDate.now()))
                .toList().getFirst();
        todayOrderHistory.addPrice(getTotalPrice(orders));

        return coffees;
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
