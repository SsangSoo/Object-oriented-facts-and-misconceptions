package book.objectorientedfactsandmisconceptions.pojo.coffee;

/**
 * 커피 도메인 객체
 */
public class Coffee {

    private final String name;
    private final int price;


    private Coffee(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Coffee create(Menu menu) {
        return new Coffee(menu.getName(), menu.getPrice());
    }

}
