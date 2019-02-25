package model.weather;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Location")
public class Location {

    @Id
    @Column(name = "woeid")
    private Long woeid;             //id

    @Version
    private Integer version;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "region", length = 100, nullable = false)
    private String region;

    @Column(name = "country", length = 100, nullable = false)
    private String country;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "long")
    private Double longitude;

    @Column(name = "timezone_id", length = 50, nullable = false)
    private String timezoneId;

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
        //
        weatherBroadcast.setLocation(this);
    }

    public void removeWeatherBroadcast(final WeatherBroadcast weatherBroadcast) {
        getWeatherBroadcasts().remove(weatherBroadcast);
    }

    public WeatherBroadcast getLatestWeatherBroadcast() throws Exception {

        int weatherBroadcastListSize = getWeatherBroadcasts().size();

        if(weatherBroadcastListSize == 0) {
            throw new Exception("There are no broadcasts for " + getCity());
        }

        return getWeatherBroadcasts().get(weatherBroadcastListSize - 1);
    }

}
