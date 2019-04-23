package mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Юнит тест JacksonMapper
 */
public class JacksonMapperTest {

    JacksonMapper jacksonMapper;

    /**
     * Инициализация полей перед тестами
     */
    @Before
    public void setUp(){
        jacksonMapper = new JacksonMapper();
    }

    /**
     * Тест на удачный маппинг json в WeatherBroadcast
     */
    @Test
    public void mapJsonToWeatherBroadcastTest() {
        String json =
                "{" +
                    "\"location\":" +
                    "{" +
                        "\"woeid\":2124298," +
                        "\"city\":\"Volgograd\"," +
                        "\"region\":\" Volgograd Oblast\"," +
                        "\"country\":\"Russia\"," +
                        "\"lat\":48.656631," +
                        "\"long\":44.504749," +
                        "\"timezone_id\":\"Europe/Volgograd\"" +
                    "}," +
                    "\"current_observation\":" +
                    "{" +
                        "\"wind\":" +
                        "{" +
                            "\"chill\":64," +
                            "\"direction\":338," +
                            "\"speed\":8.08" +
                        "}," +
                        "\"atmosphere\":" +
                        "{" +
                            "\"humidity\":25," +
                            "\"visibility\":10.0," +
                            "\"pressure\":30.03," +
                            "\"rising\":0" +
                        "}," +
                        "\"astronomy\":" +
                        "{" +
                            "\"sunrise\":\"5:55 am\"" +
                            ",\"sunset\":\"8:06 pm\"" +
                        "}," +
                        "\"condition\":" +
                        "{" +
                            "\"text\":\"Mostly Cloudy\"," +
                            "\"code\":28," +
                            "\"temperature\":65" +
                        "}," +
                        "\"pubDate\":1556024400" +
                    "}," +
                    "\"forecasts\":" +
                    "[" +
                        "{" +
                            "\"day\":\"Mon\"," +
                            "\"date\":1555963200," +
                            "\"low\":42," +
                            "\"high\":66," +
                            "\"text\":\"Partly Cloudy\"," +
                            "\"code\":30" +
                        "}," +
                        "{" +
                            "\"day\":\"Tue\"," +
                            "\"date\":1556049600," +
                            "\"low\":50," +
                            "\"high\":65," +
                            "\"text\":\"Mostly Cloudy\"," +
                            "\"code\":28" +
                        "}" +
                    "]" +
                "}";

        Assert.assertNotNull(jacksonMapper.mapJsonToWeatherBroadcast(json));
    }

    /**
     * Тест на неудачный маппинг json в WeatherBroadcast в связи с неверным форматом json
     */
    @Test(expected = RuntimeException.class)
    public void mapJsonToWeatherBroadcastWrongJsonFailTest() {
        String json = "";

        Assert.assertNotNull(jacksonMapper.mapJsonToWeatherBroadcast(json));
    }

    /**
     * Тест на неудачный маппинг json в WeatherBroadcast в связи с неверным форматом json
     */
    @Test(expected = RuntimeException.class)
    public void mapJsonToWeatherBroadcastNullJsonFailTest() {
        String json = null;

        Assert.assertNotNull(jacksonMapper.mapJsonToWeatherBroadcast(json));
    }
}
