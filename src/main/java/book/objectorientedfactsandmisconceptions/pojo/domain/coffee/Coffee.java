package book.objectorientedfactsandmisconceptions.pojo.domain.coffee;

import book.objectorientedfactsandmisconceptions.pojo.domain.coffee.usecase.CoffeeUsecase;

public class Coffee implements CoffeeUsecase {

    private String name;
    private int price;


    public static Coffee of(String name, int price) {
        return new Coffee(name, price);
    }

    private Coffee(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public Coffee makeCoffee(Menu coffee) {
        return of(coffee.getCoffee(), coffee.getPrice());
    }
}
