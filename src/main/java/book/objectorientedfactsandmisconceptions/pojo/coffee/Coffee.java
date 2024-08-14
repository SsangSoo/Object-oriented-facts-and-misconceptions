package book.objectorientedfactsandmisconceptions.pojo.coffee;

import lombok.Getter;

@Getter
public class Coffee {

    private String name;
    private int price;

    private Coffee(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Coffee createCoffee(Menu coffee) {
        return new Coffee(coffee.getCoffee(), coffee.getPrice());
    }
}
