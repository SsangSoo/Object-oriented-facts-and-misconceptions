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

    @Override
    public List<Coffee> makeCoffee(List<OrderItem> orderItems) {
        List<Coffee> coffees = new ArrayList<>();

        for(OrderItem orderItem : orderItems) {
            String coffee = orderItem.coffee();
            int quantity = orderItem.quantity();

            for (int i = 0; i < quantity; i++) {
                coffees.add(Coffee.create(Menu.of(coffee)));
            }
        }
        return coffees;
    }

}
