package ru.dacha.mvc_exercise;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class OpenExchangeRatesImpl  implements OpenExchangeRates{

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate restTemplate = new RestTemplate();


    @Override
    public ForexExchange requestForCurrencies(String url) {
        return   restTemplate
                .getForObject(url, ForexExchange.class);
    }

    @Override
    public List<String> currenciesCoded(String url) {
        ParameterizedTypeReference<TreeMap<String, String>> responseType =
                new ParameterizedTypeReference<TreeMap<String, String>>() {
                };
        RequestEntity<Void> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        Map<String, String> jsonDictionary = new TreeMap<>();
        jsonDictionary = getRestTemplate().exchange(request, responseType).getBody();

        List<String> keys = new ArrayList<>(jsonDictionary.keySet());

        return keys;
    }
}
