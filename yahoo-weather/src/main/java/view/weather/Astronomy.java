package view.weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@JsonPropertyOrder({
        "sunrise",
        "sunset"
})
public class Astronomy implements Serializable {

    private String sunrise;

    private String sunset;

    @JsonGetter("sunrise")
    public String getSunrise() {
        return sunrise;
    }

    @JsonSetter("sunrise")
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    @JsonGetter("sunset")
    public String getSunset() {
        return sunset;
    }

    @JsonSetter("sunset")
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Astronomy{" +
                "sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
