package model.weather;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;

/**
 * Атмосфера
 */
@Entity
@Table(name = "Atmosphere")
public class Atmosphere {

    /**
     * Id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Влажность
     */
    @Column(name = "humidity")
    private Integer humidity;

    /**
     * Видимость
     */
    @Column(name = "visibility")
    private Float visibility;

    /**
     * Давление
     */
    @Column(name = "pressure")
    private Float pressure;

    /**
     * Rising
     */
    @Column(name = "rising")
    private Integer rising;

    /**
     * Наблюдения
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Observation observation;

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

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getVisibility() {
        return visibility;
    }

    public void setVisibility(Float visibility) {
        this.visibility = visibility;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Integer getRising() {
        return rising;
    }

    public void setRising(Integer rising) {
        this.rising = rising;
    }

}
