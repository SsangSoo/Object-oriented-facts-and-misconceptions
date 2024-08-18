package book.objectorientedfactsandmisconceptions.pojo.barista;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
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

            if(coffee.equals("아메리카노")) {
                for(int glass = 0; glass<quantity; glass++) {
                    coffees.add(Coffee.makeAmericano());
                }
            }
            if(coffee.equals("에스프레소")) {
                for(int glass = 0; glass<quantity; glass++) {
                    coffees.add(Coffee.makeEspresso());
                }
            }
            if(coffee.equals("카페라떼")) {
                for (int glass = 0; glass < quantity; glass++) {
                    coffees.add(Coffee.makeCaffeLatte());
                }
            }
        }
        return coffees;

    }
}
