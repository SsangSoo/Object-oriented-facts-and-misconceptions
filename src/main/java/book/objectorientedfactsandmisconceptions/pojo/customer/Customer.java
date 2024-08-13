package book.objectorientedfactsandmisconceptions.pojo.customer;

import book.objectorientedfactsandmisconceptions.pojo.coffee.CoffeeOrder;
import book.objectorientedfactsandmisconceptions.pojo.barista.Barista;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.history.OrderHistory;
import book.objectorientedfactsandmisconceptions.pojo.history.PaymentHistory;
import book.objectorientedfactsandmisconceptions.pojo.customer.usecase.CustomerInterface;
import book.objectorientedfactsandmisconceptions.pojo.repository.BaristaRepository;
import book.objectorientedfactsandmisconceptions.pojo.repository.CustomerRepository;
import lombok.Getter;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Getter
public class Customer implements CustomerInterface {

    private String name;    // 고객 이름
    private List<OrderHistory> orderHistories = new ArrayList<>();

    private final Map<String, Barista> baristaMap = BaristaRepository.getBaristaMap();


    private Customer(String name) {
        this.name = name;
    }

    /**
     * Override 성 메서드
     * @param name
     * @return
     */
    public static Customer createCustomer(String name) {
        Map<String, Customer> customerMap = CustomerRepository.getCustomerMap();
        customerMap.put(name, new Customer(name));
        return customerMap.get(name);
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
    public List<Coffee> orderCoffee(List<CoffeeOrder> coffeeOrders, String barista, LocalDate orderDate) {
        Barista findBarista = BaristaRepository.getBarista(barista);
        List<Coffee> coffees = findBarista.makeCoffee(coffeeOrders, orderDate);

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
