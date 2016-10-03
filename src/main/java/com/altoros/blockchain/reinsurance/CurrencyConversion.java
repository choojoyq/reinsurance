package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikita Gorbachevski
 */
public class CurrencyConversion {

    private static final CurrencyConversion currencyConversion = new CurrencyConversion();

    private final Map<String, Map<String, BigDecimal>> currencyConversionRates;

    private CurrencyConversion() {
        this.currencyConversionRates = new HashMap<>();

        Map<String, BigDecimal> cadTo = new HashMap<>();
        cadTo.put("USD", BigDecimal.valueOf(0.8));
        cadTo.put("CAD", BigDecimal.valueOf(1));

        Map<String, BigDecimal> usdTo = new HashMap<>();
        usdTo.put("USD", BigDecimal.valueOf(1));
        usdTo.put("CAD", BigDecimal.valueOf(1.25));

        this.currencyConversionRates.put("CAD", cadTo);
        this.currencyConversionRates.put("USD", usdTo);
    }

    public static CurrencyConversion getInstance() {
        return currencyConversion;
    }

    public BigDecimal convert(String currencyFrom, String currencyTo, BigDecimal amount) {
        Map<String, BigDecimal> currencyFromConversionRates = this.currencyConversionRates.get(currencyFrom);
        if (currencyFromConversionRates == null) {
            throw new RuntimeException("Unknown currency=" + currencyFrom);
        }
        BigDecimal rate = currencyFromConversionRates.get(currencyTo);
        if (rate == null) {
            throw new RuntimeException("Unknown currency=" + currencyTo);
        }
        return amount.multiply(rate);
    }
}
