package book.objectorientedfactsandmisconceptions.pojo.barista;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
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
    public List<Coffee> makeCoffee(List<OrderInfo> orderInfos) {
        List<Coffee> coffees = new ArrayList<>();

        for(OrderInfo orderInfo : orderInfos) {
            String coffee = orderInfo.getCoffee();
            int quantity = orderInfo.getQuantity();

            for (int i = 0; i < quantity; i++) {
                coffees.add(Coffee.create(Menu.of(coffee)));
            }
        }
        return coffees;
    }

}
