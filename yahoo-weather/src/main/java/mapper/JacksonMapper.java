package mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.weather.WeatherBroadcast;

import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class JacksonMapper {

    public WeatherBroadcast mapJsonToWeatherBroadcast(final String json) {

        final ObjectMapper mapper = new ObjectMapper();
        WeatherBroadcast weatherBroadcast = new WeatherBroadcast();
        try {
            weatherBroadcast = mapper.readValue(json, WeatherBroadcast.class);
        } catch (IOException e) {
            throw new RuntimeException("Error in JacksonMapper while mapping Json to WeatherBroadcast", e);
        }
        return weatherBroadcast;
    }
}
