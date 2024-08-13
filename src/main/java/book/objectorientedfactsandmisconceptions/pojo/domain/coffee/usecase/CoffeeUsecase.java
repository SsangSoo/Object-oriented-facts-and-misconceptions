package book.objectorientedfactsandmisconceptions.pojo.domain.coffee.usecase;

import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.Menu;

import java.util.List;

/**
 * 커피의 책임
 * 1. 생산될 책임
 */
public interface CoffeeUsecase {

    /**
     * 생산될 책임(한 잔)
     * @param coffee    : 메뉴
     * @return          : 단일 커피
     */
    Coffee makeCoffee(Menu coffee);


    
    
}
