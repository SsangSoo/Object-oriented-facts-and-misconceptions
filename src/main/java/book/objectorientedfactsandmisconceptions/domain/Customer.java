package book.objectorientedfactsandmisconceptions.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String name;                        // 이름

    public List<OrderHistory> orderHistoris;    // 주문내역

    public Customer(String name) {
        this.name = name;
    }

    // 손님의 책임
    // 1. 메뉴판을 통해서 메뉴 목록을 확인할 수 있다.
    public Menu[] getCoffeeMenu() {
        Menu[] sellingCoffeese = Menu.values();

        for (Menu menu : sellingCoffeese) {
            System.out.println("커피 = " + menu.getName() + ", 가격 = " + menu.getPrice());
        }
        return Menu.values();
    }

    // 2. 바리스타에게 커피를 주문할 수 있다.
        // 주문내역에 담겨야하고, // 다수의 주문 가능
        // 바리스타로부터 제조된 커피를 얻어올 수 있어야한다.
    public List<Coffee> orderCoffee(String name, int count) {
        Barista barista = new Barista();
        List<Coffee> coffees = barista.makeCoffee(name, count);


    }

        // 3. 주문 내역을 확인할 수 있다.




}
