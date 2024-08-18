package book.objectorientedfactsandmisconceptions.pojo.responsibility;

import book.objectorientedfactsandmisconceptions.pojo.order.OrderItem;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;

import java.util.List;

/**
 * 바리스타의 책임
 *
 */
public interface BaristaResponsibility {

    /**
     * 커피를 제조할 수 있다.
     *    - 주문 정보에 따라 커피를 제조한다.
     * @param orderItems    : 주문정보
     * @return
     */
    List<Coffee> makeCoffee(List<OrderItem> orderItems);



}
