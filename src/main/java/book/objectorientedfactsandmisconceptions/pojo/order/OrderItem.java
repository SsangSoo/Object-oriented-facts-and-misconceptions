package book.objectorientedfactsandmisconceptions.pojo.order;


import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import lombok.Getter;

/**
 * 주문 정보
 */
@Getter
public class OrderItem {

    private String coffee;
    private int quantity;


    public OrderItem(String coffee, int quantity) {
        this.coffee = coffee;
        this.quantity = quantity;
    }
}
