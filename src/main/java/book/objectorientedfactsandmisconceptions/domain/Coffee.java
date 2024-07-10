package book.objectorientedfactsandmisconceptions.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Coffee {

    private String name;
    private int price;


    public static Coffee of(String name, int price) {
        return new Coffee(name, price);
    }

    private Coffee(int price, String name) {
        this.price = price;
        this.name = name;
    }

}
