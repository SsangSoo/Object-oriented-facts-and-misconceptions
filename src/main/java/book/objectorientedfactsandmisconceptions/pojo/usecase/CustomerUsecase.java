package book.objectorientedfactsandmisconceptions.pojo.usecase;

import book.objectorientedfactsandmisconceptions.pojo.domain.Menu;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.order.Order;

import java.util.List;

/**
 * 손님의 책임
 * 책임 1. 메뉴판을 통해서 메뉴 목록을 확인할 수 있다.
 * 책임 2. 바리스타에게 커피를 주문할 수 있다(다수의 주문 가능).
 *      주문내역에 담겨야하고,
 *      바리스타로부터 제조된 커피를 얻어올 수 있어야한다.
 * 책임 3. 주문 내역을 확인할 수 있다.
 */
public interface CustomerUsecase {

    Menu[] getCoffeeMenu();

    List<Coffee> orderCoffee(Order[] orders, String name);
}
