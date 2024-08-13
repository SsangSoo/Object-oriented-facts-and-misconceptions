package book.objectorientedfactsandmisconceptions.pojo.domain.coffee.usecase;

import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.CoffeeOrder;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Menu;

/**
 * 커피 주문의 책임
 * 1. 고객이 주문할 커피와 수량 데이터를 가지고 있을 책임
 */
public interface CoffeeOrderUsecase {

    /**
     * 커피 주문 내역을 담는다.
     * @param menu  : 메뉴(이름)
     * @param count : 수량
     * @return      : 커피 주문
     */
    CoffeeOrder addOrder(String menu, int count);


    /**
     * 커피 주문 내역을 담는다.
     * @param menu  : 메뉴
     * @param count : 수량
     * @return      : 커피 주문
     */
    CoffeeOrder addOrder(Menu menu, int count);

}
