package ru.dacha.mvc_exercise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class GifImageServiceTests {

    @Test
    public void gifGiveImage(){

ImageGetter imageGetter = new GifImageService();

String response =imageGetter.gifGiveImage("https://api.giphy.com/v1/gifs/random?api_key=ItvrOnhUhUjpR1cQAX5Ve6c17nevW3I6&tag=rich");
        assertNotNull(response);
    }
}
