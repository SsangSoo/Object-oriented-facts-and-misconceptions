package book.objectorientedfactsandmisconceptions.pojo.domain.customer;

import book.objectorientedfactsandmisconceptions.pojo.domain.barista.Barista;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.CoffeeOrder;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.history.OrderHistory;
import book.objectorientedfactsandmisconceptions.pojo.domain.history.PaymentHistory;
import book.objectorientedfactsandmisconceptions.pojo.domain.customer.usecase.CustomerUsecase;
import lombok.Getter;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
public class Customer implements CustomerUsecase {

    private String name;    // 고객 이름
    private List<OrderHistory> orderHistories = new ArrayList<>();

    private Customer(String name) {
        this.name = name;
    }

    public static Customer of(String name) {
        return new Customer(name);
    }

    /**
     * Override 성 메서드
     * @param name
     * @return
     */
    public static Customer createCustomer(String name) {
        return of(name);
    }

    @Override
    public Menu[] getCoffeeMenu() {
        Menu[] sellingCoffeese = Menu.values();

        for (Menu menu : sellingCoffeese) {
            System.out.println("커피 = " + menu.getCoffee() + ", 가격 = " + menu.getPrice()); // REST API 로 구현시 Response로
        }
        return Menu.values();
    }

    @Override
    public List<Coffee> orderCoffee(List<CoffeeOrder> coffeeOrders, String BaristaName, LocalDate orderDate) {
        Barista barista = Barista.of(BaristaName);
        List<Coffee> coffees = barista.makeCoffee(coffeeOrders, orderDate);

        // 주문내역에 추가
        addOrderHistory(coffeeOrders, orderDate);

        return coffees;
    }

    @Override
    public PaymentHistory getPaymentHistoryAtYear(int year) {
        List<OrderHistory> result = orderHistories.stream()
                .filter(orderHistory -> orderHistory.getOrderedDate().getYear() == year)
                .toList();

        return PaymentHistory.of(result);

    }

    @Override
    public PaymentHistory getPaymentHistoryAtMonth(int year, int month) {
        List<OrderHistory> result = orderHistories.stream()
                .filter(orderHistory -> orderHistory.getOrderedDate().getYear() == year)
                .filter(orderHistory -> orderHistory.getOrderedDate().getMonthValue() == month)
                .toList();
        return PaymentHistory.of(result);

    }

    @Override
    public PaymentHistory getPaymentHistoryAtDay(LocalDate date) {
        List<OrderHistory> result = orderHistories.stream()
                .filter(orderHistory -> orderHistory.getOrderedDate().isEqual(ChronoLocalDate.from(date)))
                .toList();
        return PaymentHistory.of(result);
    }



    /**
     * 주문내역 추가 private Method
     */
    private void addOrderHistory(List<CoffeeOrder> coffeeOrders, LocalDate orderDate) {
        orderHistories.add(OrderHistory.of(coffeeOrders, orderDate));
    }

    /**
     * 주문한 내역의 요금을 구한다.
     */
    private int getTotalPrice(CoffeeOrder[] coffeeOrders) {
        int orderPrice = 0;
        for(int index = 0; index < coffeeOrders.length; index++) {
            CoffeeOrder coffeeOrder = coffeeOrders[index];
            orderPrice += coffeeOrder.getMenu().getPrice() * coffeeOrder.getCount();
        }
        return orderPrice;
    }

}
