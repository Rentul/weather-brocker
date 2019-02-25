package view.weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@JsonPropertyOrder({
        "day",
        "date",
        "low",
        "high",
        "text",
        "code"
})
public class Forecast implements Serializable {

    private String day;

    private Long date;

    private Integer low;

    private Integer high;

    private String text;

    private Integer code;

    @JsonGetter("day")
    public String getDay() {
        return day;
    }

    @JsonSetter("day")
    public void setDay(String day) {
        this.day = day;
    }

    @JsonGetter("date")
    public Long getDate() {
        return date;
    }

    @JsonSetter("date")
    public void setDate(Long date) {
        this.date = date;
    }

    @JsonGetter("low")
    public Integer getLow() {
        return low;
    }

    @JsonSetter("low")
    public void setLow(Integer low) {
        this.low = low;
    }

    @JsonGetter("high")
    public Integer getHigh() {
        return high;
    }

    @JsonSetter("high")
    public void setHigh(Integer high) {
        this.high = high;
    }

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

    @Override
    public String toString() {
        return "Forecast{" +
                "day='" + day + '\'' +
                ", date=" + date +
                ", low=" + low +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }
}
