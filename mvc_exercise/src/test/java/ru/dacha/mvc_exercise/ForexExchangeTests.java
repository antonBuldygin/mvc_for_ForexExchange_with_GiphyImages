package ru.dacha.mvc_exercise;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;


@SpringBootTest
public class ForexExchangeTests {
    String testValue = "mvs";
    Integer integer = 1000;
    @Mock
    private Map<String, Double> rates = new TreeMap<>();

    @InjectMocks
    ForexExchange forexExchange;

    @Test
    public void ForexTest() {


        forexExchange.setBase(testValue);
        forexExchange.setDisclaimer(testValue);
        forexExchange.setLicense(testValue);
        forexExchange.setTimestamp(integer);
        forexExchange.setRates(rates);

        String checkToString = "ForexExchange{" +
                "disclaimer='" + testValue + '\'' +
                ", license='" + testValue + '\'' +
                ", timestamp=" + integer +
                ", base='" + testValue + '\'' +
                ", rates=" + rates +
                '}';

        assertEquals(testValue, forexExchange.getBase());
        assertEquals(testValue, forexExchange.getDisclaimer());
        assertEquals(testValue, forexExchange.getLicense());
        assertEquals(integer, forexExchange.getTimestamp());
        assertEquals(rates, forexExchange.getRates());
        assertEquals(forexExchange.toString(), checkToString);
    }
}
