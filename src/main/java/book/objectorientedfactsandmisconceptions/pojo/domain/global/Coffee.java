package book.objectorientedfactsandmisconceptions.pojo.domain.global;


/**
 * 커피 객체
 * 책임 1. 제조된다.
 */
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
