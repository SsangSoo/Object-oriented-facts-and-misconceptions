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
        this.coffee = Menu.of(coffee).getName();
        this.quantity = quantity;
    }

    public OrderItem(Menu menu, int quantity) {
        this(menu.getName(), quantity);
    }
}
