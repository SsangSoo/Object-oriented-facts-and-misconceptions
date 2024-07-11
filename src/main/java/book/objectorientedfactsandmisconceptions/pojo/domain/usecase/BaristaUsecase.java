package book.objectorientedfactsandmisconceptions.pojo.domain.usecase;

import book.objectorientedfactsandmisconceptions.pojo.domain.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.domain.Order;

import java.util.List;

/**
 * 바리스타의 책임
 * 1. 커피를 제조한다.
 *
 */
public interface BaristaUsecase {

    // 1. 커피를 제조한다.
    List<Coffee> makeCoffee(Order[] orders);

}
