package controller.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.weather.WeatherService;
import view.weather.WeatherBroadcast;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    public WeatherController() {
        weatherService = null;
    }

    public WeatherController(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }


    public void setWeatherService(WeatherService weatherService) {
        if (weatherService == null) {
            throw new RuntimeException("WeatherService is null");
        }
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/{city:[\\w]+}", method = {GET})
    public WeatherBroadcast getWeatherBroadcastByCity(final @PathVariable("city") String city) {
        try {
            return weatherService.getWeatherBroadcast(city);
        } catch (Exception e) {
            throw new RuntimeException("Error in weather controller: " + e.getMessage());
        }
    }

}
