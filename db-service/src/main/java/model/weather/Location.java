package model.weather;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Местонахождение
 */
@Entity
@Table(name = "Location")
public class Location {

    /**
     * Id
     */
    @Id
    @Column(name = "woeid")
    private Long woeid;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Город
     */
    @Column(name = "city", length = 50, nullable = false)
    private String city;

    /**
     * Регион
     */
    @Column(name = "region", length = 100, nullable = false)
    private String region;

    /**
     * Страна
     */
    @Column(name = "country", length = 100, nullable = false)
    private String country;

    /**
     * Широта
     */
    @Column(name = "lat")
    private Double latitude;

    /**
     * Долгота
     */
    @Column(name = "long")
    private Double longitude;

    /**
     * Id временной зоны
     */
    @Column(name = "timezone_id", length = 50, nullable = false)
    private String timezoneId;

    /**
     * Прогнозы погоды
     */
    @OneToMany(
            mappedBy = "location",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WeatherBroadcast> weatherBroadcasts;

    public Long getWoeid() {
        return woeid;
    }

    public void setWoeid(final Long woeid) {
        this.woeid = woeid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLat(final Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(final String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public List<WeatherBroadcast> getWeatherBroadcasts() {
        if(weatherBroadcasts == null) {
            weatherBroadcasts = new ArrayList<>();
        }
        return weatherBroadcasts;
    }

    public void setWeatherBroadcasts(final List<WeatherBroadcast> weatherBroadcasts) {
        this.weatherBroadcasts = weatherBroadcasts;
    }

    public void addWeatherBroadcast(final WeatherBroadcast weatherBroadcast) {
        getWeatherBroadcasts().add(weatherBroadcast);
        weatherBroadcast.setLocation(this);
    }

    public void removeWeatherBroadcast(final WeatherBroadcast weatherBroadcast) {
        getWeatherBroadcasts().remove(weatherBroadcast);
    }

    public WeatherBroadcast getLatestWeatherBroadcast() {

        int weatherBroadcastListSize = getWeatherBroadcasts().size();

        if(weatherBroadcastListSize == 0) {
            throw new RuntimeException("Error in Location while getting weather broadcast: there are no broadcasts for " + getCity());
        }

        return getWeatherBroadcasts().get(weatherBroadcastListSize - 1);
    }

}
