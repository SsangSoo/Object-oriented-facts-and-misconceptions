package book.objectorientedfactsandmisconceptions.pojo.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Coffee {

    private String name;
    private int price;


    public static Coffee of(String name, int price) {
        return new Coffee(name, price);
    }

    private Coffee(String name, int price) {
        this.name = name;
        this.price = price;
    }

}
