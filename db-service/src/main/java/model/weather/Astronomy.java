package model.weather;

import javax.persistence.*;

@Entity
@Table(name = "Astronomy")
public class Astronomy {

    @Id
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Observation observation;

    @Column(name = "sunrise", length = 15, nullable = false)
    private String sunrise;

    @Column(name = "sunset", length = 15, nullable = false)
    private String sunset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

}
