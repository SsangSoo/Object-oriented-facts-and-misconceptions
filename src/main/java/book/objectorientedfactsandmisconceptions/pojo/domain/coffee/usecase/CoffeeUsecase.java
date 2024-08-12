package book.objectorientedfactsandmisconceptions.pojo.domain.coffee.usecase;

import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Menu;

import java.util.List;

/**
 * 커피의 책임
 * - 생산될 책임
 * 
 */
public interface CoffeeUsecase {

    /**
     * 생산될 책임(한 잔)
     * @param coffee    : 메뉴
     * @return          : 단일 커피
     */
    Coffee makeCoffee(Menu coffee);

    /**
     * 생산될 책임(여러 잔)
     * @param coffee        : 커피 메뉴
     * @param quantity      : 수량
     * @return              : 여러 잔의 커피
     */
    List<Coffee> makeCoffees(Menu coffee, Integer quantity);
    
    
}
