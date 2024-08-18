package book.objectorientedfactsandmisconceptions.pojo.coffee;

/**
 * 커피 도메인 객체
 */
public class Coffee {

    private String name;
    private int price;


    private Coffee(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Coffee makeAmericano() {
        return new Coffee("아메리카노", 4000);
    }

    public static Coffee makeEspresso() {
        return new Coffee("에스프레소", 4000);
    }

    public static Coffee makeCaffeLatte() {
        return new Coffee("카페라떼", 4500);
    }

}
