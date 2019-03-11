package view.weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.List;

/**
 * Прогноз погоды
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "location",
        "current_observation",
        "forecasts"
})
public class WeatherBroadcast implements Serializable {

    /**
     * Местонахождение
     */
    private Location location;

    /**
     * Наблюдения
     */
    private CurrentObservation currentObservation;

    /**
     * Прогнозы
     */
    private List<Forecast> forecasts;

    @JsonGetter("location")
    public Location getLocation() {
        return location;
    }

    @JsonSetter("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonGetter("current_observation")
    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    @JsonSetter("current_observation")
    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    @JsonGetter("forecasts")
    public List<Forecast> getForecasts() {
        return forecasts;
    }

    @JsonSetter("forecasts")
    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "WeatherBroadcast{" +
                "location=" + location +
                ", currentObservation=" + currentObservation +
                ", forecasts=" + forecasts +
                '}';
    }
}
