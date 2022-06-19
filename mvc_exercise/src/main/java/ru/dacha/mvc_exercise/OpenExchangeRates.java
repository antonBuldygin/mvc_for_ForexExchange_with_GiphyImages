package ru.dacha.mvc_exercise;

import java.util.List;

public interface OpenExchangeRates {

    ForexExchange requestForCurrencies(String url);

    List<String> currenciesCoded(String url);
}
