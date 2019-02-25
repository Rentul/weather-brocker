package model.weather;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Observation")
public class Observation {

    @Id
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private WeatherBroadcast weatherBroadcast;

    @Column(name = "pubDate")
    private Timestamp pubDate;

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeatherBroadcast getWeatherBroadcast() {
        return weatherBroadcast;
    }

    public void setWeatherBroadcast(WeatherBroadcast weatherBroadcast) {
        this.weatherBroadcast = weatherBroadcast;
    }

    public Timestamp getPubDate() {
        return pubDate;
    }

    public void setPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
    }

}