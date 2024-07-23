package book.objectorientedfactsandmisconceptions.pojo.domain.barista;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 바리스타 목록
 */
@Getter
public class BaristaList {

    private static Map<String, Barista> baristaMap = new HashMap<>();

    public static Map<String, Barista> getBaristaMap() {
        return baristaMap;
    }
}
