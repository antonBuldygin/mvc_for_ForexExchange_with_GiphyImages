package ru.dacha.mvc_exercise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class GreetingControllerTest {
    @InjectMocks
    GreetingController greetingController;

    @Mock
    Model model;
    @Mock
    private Map<String, Double> mapMock = mock(Map.class);

    @Mock
    Service service;

    @Mock
    OpenExchangeRates openExchangeRates;

    @Mock
    ForexExchange forexExchange;

    @Mock
    GifImageService gifImageService;

    @Before
    public void setUp() throws Exception {
        openMocks(this);

    }

    @Test
    public void greeting() {


        when(openExchangeRates.requestForCurrencies(anyString())).thenReturn(forexExchange);
        when(service.getRes()).thenReturn("RUB");
        when(openExchangeRates.requestForCurrencies(anyString()).getRates()).thenReturn(mapMock);
        when(openExchangeRates.requestForCurrencies(anyString()).getRates().get(service.getRes())).thenReturn(54.3);

        String greeting = greetingController.greeting("", model);
        assertEquals("greeting", greeting);

        verify(model,times(9)).addAttribute(anyString(),any());

    }

}
