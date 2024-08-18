package book.objectorientedfactsandmisconceptions.pojo.responsibility;

import book.objectorientedfactsandmisconceptions.pojo.OrderInfo;
import book.objectorientedfactsandmisconceptions.pojo.coffee.Coffee;
import book.objectorientedfactsandmisconceptions.pojo.history.element.HistoryElement;

import java.time.LocalDate;
import java.util.List;

/**
 * 키오스크
 *
 */
public class Kiosk implements KioskResponsibility {
    @Override
    public List<Coffee> orderCoffee(List<OrderInfo> orderInfo, boolean orderAsMember, boolean orderWithCoupon) {
        return null;
    }

    @Override
    public HistoryElement historyPurchaseOfYear(int year, Long id) {
        return null;
    }

    @Override
    public HistoryElement historyPurchaseOfMonth(int year, int month, Long id) {
        return null;
    }

    @Override
    public HistoryElement historyPurchaseOfDay(LocalDate date, Long id) {
        return null;
    }

    @Override
    public HistoryElement historySalesOfYear(int year) {
        return null;
    }

    @Override
    public HistoryElement historySalesOfMonth(int year, int month) {
        return null;
    }

    @Override
    public HistoryElement historySalesOfDay(LocalDate date) {
        return null;
    }
}
