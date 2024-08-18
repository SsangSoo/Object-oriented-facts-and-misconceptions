package book.objectorientedfactsandmisconceptions.pojo;

import book.objectorientedfactsandmisconceptions.pojo.coffee.Menu;
import lombok.Getter;

/**
 * 주문 정보
 */
@Getter
public class OrderInfo {

    private final String coffee;  // 커피 이름
    private final int quantity;   // 수량
    private final int price;

    public OrderInfo(Menu menu, int quantity) {
        this.coffee = menu.getName();
        this.quantity = quantity;
        this.price = menu.getPrice() * quantity;
    }

}
