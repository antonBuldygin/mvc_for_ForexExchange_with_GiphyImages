package ru.dacha.mvc_exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class GreetingController {

    @Autowired
    private GifImageService imageUrl;

    @Autowired
    private Service service;

    @Autowired
    private OpenExchangeRates apiRequest;
    @Value("${giphy.url.general}")
    private String urlGeneral;
    @Value("${giphy.rich}")
    private String rich;

    @Value("${giphy.broke}")
    private String broke;

    @Value("${giphy.zero}")
    private String travolta;
    //        "https://api.giphy.com/v1/gifs/random?api_key=ItvrOnhUhUjpR1cQAX5Ve6c17nevW3I6&tag=travolta";
    @Value("${giphy.api.key}")
    private String apiKey;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Rich or Broke?") String name, Model model) {

        model.addAttribute("name", name);

        service.setTeacher(apiRequest.currenciesCoded("https://openexchangerates.org/api/currencies.json?app_id=e0b0bc1a7d644980b0baf3017ff564d7"));
        model.addAttribute("teachers", service.getTeacher());

        Double currentRates = apiRequest
                .requestForCurrencies("https://openexchangerates.org/api/latest.json?app_id=e0b0bc1a7d644980b0baf3017ff564d7")
                .getRates().get(service.getRes());


        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar previousDay = Calendar.getInstance();
        String today = formater.format(previousDay.getTime());
        previousDay.add(Calendar.DAY_OF_YEAR, -1);
        String date = formater.format(previousDay.getTime());

        Double previousDayRates = apiRequest
                .requestForCurrencies("https://openexchangerates.org/api/historical/" + date + ".json?app_id=e0b0bc1a7d644980b0baf3017ff564d7")
                .getRates().get(service.getRes());

        model.addAttribute("currencyPD", previousDayRates);
        model.addAttribute("today", today);
        model.addAttribute("date", date);
        model.addAttribute("type", service.getRes());
        model.addAttribute("currency", currentRates);


        String urlforGif = previousDayRates - currentRates > 0 ? urlGeneral+"/random?api_key="+apiKey+"&tag="+broke : previousDayRates - currentRates < 0 ? urlGeneral+"/random?api_key="+apiKey+"&tag="+rich : urlGeneral+"/random?api_key="+apiKey+"&tag="+travolta;

        model.addAttribute("url", imageUrl.gifGiveImage(urlforGif));

        String status = previousDayRates - currentRates > 0 ? "BROKE" : previousDayRates - currentRates < 0 ? "RICH" : "TRAVOLTA";
        model.addAttribute("RichOrBroke", status);


        System.out.println(currentRates.toString());

        System.out.println("hi");

        return "greeting";
    }

    @PostMapping("/greeting")
    public String createFormProcessing(
            @ModelAttribute CurrencyCode code,
            final Model model
    ) {
        service.setRes(code.getCode());
        return "redirect:/greeting";
    }

    public static class CurrencyCode {

        private String code;

        public String getCode() {
            return code;
        }

        public void setTeachers(String code) {
            this.code = code;
        }

    }
}
