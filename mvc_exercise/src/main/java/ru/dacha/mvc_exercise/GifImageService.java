package ru.dacha.mvc_exercise;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class GifImageService implements ImageGetter{
    public RestTemplate restTemplate = new RestTemplate();

    @Override
    public String gifGiveImage(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(
                url, String.class);


        JsonObject img = new JsonParser().parse(response.getBody()).getAsJsonObject();

        List<Map.Entry<String, JsonElement>> membersList = new ArrayList<>(img.entrySet());
        String type = membersList.get(0)
                .getKey();
        String name  = membersList.get(0)
                .getValue()
                .getAsJsonObject()
                .get("images")
                .getAsJsonObject()
                .get("original")
                .getAsJsonObject()
                .get("url")
                .getAsString();

        return name;
    }
}
