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

    private BaristaRepository() {}

    private static final Map<String, Barista> baristaMap = new HashMap<>();

    public static void addBarista(Barista barista) {
        baristaMap.put(barista.getName(), barista);
    }

    public static Barista getBarista(String barista) {
        Barista getBarista = baristaMap.get(barista);
        if(Objects.isNull(getBarista)) {
            addBarista(Barista.of(barista));
            getBarista = baristaMap.get(barista);
        }
        return getBarista;
    }
}
