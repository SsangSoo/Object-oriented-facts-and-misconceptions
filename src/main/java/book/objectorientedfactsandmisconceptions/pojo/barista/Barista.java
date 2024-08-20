package book.objectorientedfactsandmisconceptions.pojo.barista;

import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import book.objectorientedfactsandmisconceptions.pojo.responsibility.BaristaResponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * 바리스타
 */
public class Barista implements BaristaResponsibility {

    private Barista() {}


    /**
     * 커피를 제조할 수 있다.
     *  - 주문 정보에 따라 커피를 제조한다.
     * @param orderItems
     * @return
     */
    public static List<Coffee> makeCoffee(List<OrderItem> orderItems) {
        List<Coffee> coffees = new ArrayList<>();

        for(OrderItem orderItem : orderItems) {
            String coffee = orderItem.getCoffee();
            int quantity = orderItem.getQuantity();

            for (int i = 0; i < quantity; i++) {
                coffees.add(Coffee.create(Menu.of(coffee)));
            }
        }
        return coffees;
    }



}
