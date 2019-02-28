package view.weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@JsonPropertyOrder({
        "chill",
        "direction",
        "speed"
})
public class Wind implements Serializable {

    private Integer chill;

    private Integer direction;

    private Float speed;

    @JsonGetter("chill")
    public Integer getChill() {
        return chill;
    }

    @JsonSetter("chill")
    public void setChill(Integer chill) {
        this.chill = chill;
    }

    @JsonGetter("direction")
    public Integer getDirection() {
        return direction;
    }

    @JsonSetter("direction")
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    @JsonGetter("speed")
    public Float getSpeed() {
        return speed;
    }

    @JsonSetter("speed")
    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
