package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Nikita Gorbachevski
 */
public class DateUtils {

    private DateUtils() {

    }

    public static BigDecimal subtract(LocalDate d1, LocalDate d2) {
        return new BigDecimal(d1.toEpochDay() - d2.toEpochDay());
    }

    public static LocalDate max(LocalDate... dates) {
        LocalDate max = LocalDate.MIN;
        for (LocalDate date : dates) {
            if (date.compareTo(max) > 0) {
                max = date;
            }
        }
        return max;
    }

    public static LocalDate min(LocalDate... dates) {
        LocalDate min = LocalDate.MAX;
        for (LocalDate date : dates) {
            if (date.compareTo(min) < 0) {
                min = date;
            }
        }
        return min;
    }
}
