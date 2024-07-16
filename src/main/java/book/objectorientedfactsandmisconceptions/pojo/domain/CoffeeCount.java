package book.objectorientedfactsandmisconceptions.pojo.domain;

import lombok.Getter;

@Getter
public class CoffeeCount {

    private String coffee;
    private int count;

    private CoffeeCount(String coffee, int count) {
        this.coffee = coffee;
        this.count = count;
    }

    public CoffeeCount of(String coffee, int count) {
        return new CoffeeCount(coffee, count);
    }

}
