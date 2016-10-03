package com.altoros.blockchain.reinsurance;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyConversionTest {

    @Test
    public void testCurrencyConversion() {
        BigDecimal amount = new BigDecimal(40_000_000);
        // 0.8 is cad to usd conversion rate
        BigDecimal convertedAmount = CurrencyConversion.getInstance().convert("CAD", "USD", amount);
        Assert.assertEquals(0, new BigDecimal(32_000_000).compareTo(convertedAmount));

        convertedAmount = CurrencyConversion.getInstance().convert("USD", "USD", amount);
        Assert.assertEquals(0, new BigDecimal(40_000_000).compareTo(convertedAmount));
    }
}
