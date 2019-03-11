package view.weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

/**
 * Местонахождение
 */
@JsonPropertyOrder({
        "woeid",
        "city",
        "region",
        "country",
        "lat",
        "long",
        "timezone_id"
})
public class Location implements Serializable {

    /**
     * Id
     */
    private Long woeid;

    /**
     * Город
     */
    private String city;

    /**
     * Регион
     */
    private String region;

    /**
     * Страна
     */
    private String country;

    /**
     * Широта
     */
    private Double lat;

    /**
     * Долгота
     */
    private Double longitude;

    /**
     * Id временной зоны
     */
    private String timezoneId;

    @JsonGetter("woeid")
    public Long getWoeid() {
        return woeid;
    }

    @JsonSetter("woeid")
    public void setWoeid(Long woeid) {
        this.woeid = woeid;
    }

    @JsonGetter("city")
    public String getCity() {
        return city;
    }

    @JsonSetter("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonGetter("region")
    public String getRegion() {
        return region;
    }

    @JsonSetter("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonGetter("country")
    public String getCountry() {
        return country;
    }

    @JsonSetter("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonGetter("lat")
    public Double getLat() {
        return lat;
    }

    @JsonSetter("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @JsonGetter("long")
    public Double getLongitude() {
        return longitude;
    }

    @JsonSetter("long")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonGetter("timezone_id")
    public String getTimezoneId() {
        return timezoneId;
    }

    @JsonSetter("timezone_id")
    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "woeid=" + woeid +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", lat=" + lat +
                ", longitude=" + longitude +
                ", timezoneId='" + timezoneId + '\'' +
                '}';
    }
}
