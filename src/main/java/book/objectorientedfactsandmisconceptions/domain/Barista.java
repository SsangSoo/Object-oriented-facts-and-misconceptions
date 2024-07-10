package book.objectorientedfactsandmisconceptions.domain;


import book.objectorientedfactsandmisconceptions.domain.usecase.BaristaUsecase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Barista implements BaristaUsecase {



    // 커피를 주문하여 제조함.
    @Override
    public List<Coffee> makeCoffee(String name, int count) {
        List<Coffee> coffees = new ArrayList<>();

        Menu choiceCoffee = Menu.valueOf(name);

        for(int index = 0; index < count; index++) {
            coffees.add(Coffee.of(choiceCoffee.getName(), choiceCoffee.getPrice()));
        }


        return coffees;
    }

}