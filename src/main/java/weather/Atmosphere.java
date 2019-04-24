package weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

/**
 * Атмосфера
 */
@JsonPropertyOrder({
        "humidity",
        "visibility",
        "pressure",
        "rising"
})
public class Atmosphere implements Serializable {

    /**
     * Влажность
     */
    private Integer humidity;

    /**
     * Видимость
     */
    private Float visibility;

    /**
     * Давление
     */
    private Float pressure;

    /**
     * Rising
     */
    private Integer rising;

    @JsonGetter("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonSetter("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @JsonGetter("visibility")
    public Float getVisibility() {
        return visibility;
    }

    @JsonSetter("visibility")
    public void setVisibility(Float visibility) {
        this.visibility = visibility;
    }

    @JsonGetter("pressure")
    public Float getPressure() {
        return pressure;
    }

    @JsonSetter("pressure")
    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    @JsonGetter("rising")
    public Integer getRising() {
        return rising;
    }

    @JsonSetter("rising")
    public void setRising(Integer rising) {
        this.rising = rising;
    }

    @Override
    public String toString() {
        return "Atmosphere{" +
                "humidity=" + humidity +
                ", visibility=" + visibility +
                ", pressure=" + pressure +
                ", rising=" + rising +
                '}';
    }
}
