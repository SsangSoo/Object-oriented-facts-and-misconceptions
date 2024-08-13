package book.objectorientedfactsandmisconceptions.pojo.barista;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BaristaListTest {

    @Test
    @DisplayName("바리스타가 생성되지 않아도 map에서 조회할 땐 저장이 되어 조회된다.")
    void getBaristaTest() {
        // given
        Barista barista = null;

        //when
        Barista nullBarista = BaristaList.getBarista("null");

        //then
        assertThat(nullBarista).isNotNull();
        assertThat(nullBarista.getName()).isEqualTo("null");

    }

}