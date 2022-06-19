package ru.dacha.mvc_exercise;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.Assert.assertNotNull;


public class OpenExchangeRatesImplpTests {


    OpenExchangeRatesImpl openExchangeRates = new OpenExchangeRatesImpl();

    @Test
    public void currenciesCoded() {


        List<String> response = openExchangeRates.currenciesCoded("https://openexchangerates.org/api/currencies.json?app_id=e0b0bc1a7d644980b0baf3017ff564d7");
        assertNotNull(response);
    }
}
