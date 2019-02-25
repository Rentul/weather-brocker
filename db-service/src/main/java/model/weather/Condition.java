package model.weather;

import javax.persistence.*;

@Entity
@Table(name = "Condition")
public class Condition {

    @Id
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Observation observation;

    @Column(name = "text", length = 50, nullable = false)
    private String text;

    @Column(name = "code")
    private Integer code;

    @Column(name = "temperature")
    private Integer temperature;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

}
