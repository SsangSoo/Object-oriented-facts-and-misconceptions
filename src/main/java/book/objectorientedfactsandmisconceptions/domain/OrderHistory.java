package book.objectorientedfactsandmisconceptions.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 주문내역
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory {

    private LocalDateTime orderDate;
    private int totalPrice;

}

