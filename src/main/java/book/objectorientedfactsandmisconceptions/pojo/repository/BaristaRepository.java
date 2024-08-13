package book.objectorientedfactsandmisconceptions.pojo.repository;

import book.objectorientedfactsandmisconceptions.pojo.barista.Barista;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 바리스타 저장소
 */
@Getter
public class BaristaRepository {

    private static Map<String, Barista> baristaMap = new HashMap<>();

    public static Map<String, Barista> getBaristaMap() {
        return baristaMap;
    }

    public static void addBarista(Barista barista) {
        baristaMap.put(barista.getName(), barista);
    }

    public static Barista getBarista(String barista) {
        Barista findBarista = baristaMap.get(barista);
        if(Objects.isNull(findBarista)) {
            addBarista(Barista.of(barista));
            findBarista = baristaMap.get(barista);
        }
        return findBarista;
    }
}
