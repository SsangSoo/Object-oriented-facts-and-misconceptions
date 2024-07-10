package book.objectorientedfactsandmisconceptions.domain.usecase;

import book.objectorientedfactsandmisconceptions.domain.Coffee;

import java.util.List;

/**
 * 바리스타의 책임
 * 1. 커피를 제조한다.
 *
 */
public interface BaristaUsecase {

    // 1. 커피를 제조한다.
    List<Coffee> makeCoffee(String name, int count);

}
