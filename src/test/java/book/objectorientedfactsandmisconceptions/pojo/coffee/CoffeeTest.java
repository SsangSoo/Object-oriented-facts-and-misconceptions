package book.objectorientedfactsandmisconceptions.pojo.coffee;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeTest {

    @Test
    @DisplayName("아메리카노를 만든다.")
    void makeAmericanoTest() {
        // given, when
        Coffee coffee = Coffee.makeAmericano();

        //then
        Assertions.assertThat(coffee).extracting("name", "price")
                .containsExactlyInAnyOrder("아메리카노", 4000);
    }


    @Test
    @DisplayName("에스프레소를 만든다.")
    void makeEspresso() {
        // given, when
        Coffee coffee = Coffee.makeEspresso();

        //then
        Assertions.assertThat(coffee).extracting("name", "price")
                .containsExactlyInAnyOrder("에스프레소", 4000);
    }

    @Test
    @DisplayName("카페라떼를 만든다.")
    void makeCaffeLatte() {
        // given, when
        Coffee coffee = Coffee.makeCaffeLatte();

        //then
        Assertions.assertThat(coffee).extracting("name", "price")
                .containsExactlyInAnyOrder("카페라떼", 4500);

    }
}