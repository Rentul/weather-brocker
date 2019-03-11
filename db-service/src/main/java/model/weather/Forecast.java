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
import java.sql.Timestamp;

/**
 * Прогноз на день
 */
@Entity
@Table(name = "Forecast")
public class Forecast {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * День недели
     */
    @Column(name = "day", length = 8, nullable = false)
    private String day;

    /**
     * Дата
     */
    @Column(name = "date")
    private Timestamp date;

    /**
     * Нижний порог температуры
     */
    @Column(name = "low")
    private Integer low;

    /**
     * Верхний порог температуры
     */
    @Column(name = "high")
    private Integer high;

    /**
     * Описание погоды
     */
    @Column(name = "text", length = 50, nullable = false)
    private String text;

    /**
     * Код погоды
     */
    @Column(name = "code")
    private Integer code;

    /**
     * Прогноз погоды
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broadcast_id")
    private WeatherBroadcast weatherBroadcast;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
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

    public WeatherBroadcast getWeatherBroadcast() {
        return weatherBroadcast;
    }

    public void setWeatherBroadcast(WeatherBroadcast weatherBroadcast) {
        this.weatherBroadcast = weatherBroadcast;
    }

}
