package mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.weather.WeatherBroadcast;

import javax.ejb.Stateless;

@Stateless
public class JacksonMapper {

    public WeatherBroadcast mapJsonToWeatherBroadcast(final String json) {

        final ObjectMapper mapper = new ObjectMapper();
        WeatherBroadcast weatherBroadcast = new WeatherBroadcast();
        try {
            weatherBroadcast = mapper.readValue(json, WeatherBroadcast.class);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return weatherBroadcast;
    }
}
