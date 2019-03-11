package mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.weather.WeatherBroadcast;

import javax.ejb.Stateless;
import java.io.IOException;

/**
 * Маппер json
 */
@Stateless
public class JacksonMapper {

    /**
     * Маппинг json в WeatherBroadcast
     *
     * @param json  входящий json
     * @return      экземпляр WeatherBroadcast
     */
    public WeatherBroadcast mapJsonToWeatherBroadcast(final String json) {

        final ObjectMapper mapper = new ObjectMapper();
        WeatherBroadcast weatherBroadcast;
        try {
            weatherBroadcast = mapper.readValue(json, WeatherBroadcast.class);
        } catch (IOException e) {
            throw new RuntimeException("Error in JacksonMapper while mapping Json to WeatherBroadcast", e);
        }
        return weatherBroadcast;
    }
}
