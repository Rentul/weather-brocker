package model.weather;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Weather_Broadcast")
public class WeatherBroadcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Version
    private Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(
            mappedBy = "weatherBroadcast",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Forecast> forecasts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public List<Forecast> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<>();
        }
        return forecasts;
    }

    public void setForecasts(final List<Forecast> forecasts) {
        this.forecasts = forecasts;
        for(Forecast forecast: getForecasts()) {
            forecast.setWeatherBroadcast(this);
        }
    }

    public void addForecast(final Forecast forecast) {
        getForecasts().add(forecast);
        forecast.setWeatherBroadcast(this);
    }

    public void removeForecast(final Forecast forecast) {
        getForecasts().remove(forecast);
    }

}
