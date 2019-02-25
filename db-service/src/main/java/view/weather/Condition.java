package view.weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@JsonPropertyOrder({
        "text",
        "code",
        "temperature"
})
public class Condition implements Serializable {

    private String text;

    private Integer code;

    private Integer temperature;

    @JsonGetter("text")
    public String getText() {
        return text;
    }

    @JsonSetter("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonGetter("code")
    public Integer getCode() {
        return code;
    }

    @JsonSetter("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonGetter("temperature")
    public Integer getTemperature() {
        return temperature;
    }

    @JsonSetter("temperature")
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "text='" + text + '\'' +
                ", code=" + code +
                ", temperature=" + temperature +
                '}';
    }
}
