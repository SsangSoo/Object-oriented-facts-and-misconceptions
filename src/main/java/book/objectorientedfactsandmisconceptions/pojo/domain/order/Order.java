package book.objectorientedfactsandmisconceptions.pojo.domain.order;

import book.objectorientedfactsandmisconceptions.pojo.domain.Menu;
import lombok.Getter;

/**
 * 주문 정보를 담기 위한 객체
 */
@Getter
public class Order {

    private Menu menu;
    private int count;

    private Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public static Order of(String name, int count) {
        return new Order(Menu.valueOf(name), count);
    }
}
