package weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

/**
 * Наблюдения
 */
@JsonPropertyOrder({
        "wind",
        "atmosphere",
        "astronomy",
        "condition",
        "pubDate"
})
public class CurrentObservation implements Serializable {

    /**
     * Ветер
     */
    private Wind wind;

    /**
     * Атмосфера
     */
    private Atmosphere atmosphere;

    /**
     * Астрономия
     */
    private Astronomy astronomy;

    /**
     * Состояние
     */
    private Condition condition;

    /**
     * Время публикации прогноза
     */
    private Long pubDate;

    @JsonGetter("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonSetter("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @JsonGetter("atmosphere")
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    @JsonSetter("atmosphere")
    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    @JsonGetter("astronomy")
    public Astronomy getAstronomy() {
        return astronomy;
    }

    @JsonSetter("astronomy")
    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    @JsonGetter("condition")
    public Condition getCondition() {
        return condition;
    }

    @JsonSetter("condition")
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @JsonGetter("pubDate")
    public Long getPubDate() {
        return pubDate;
    }

    @JsonSetter("pubDate")
    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "CurrentObservation{" +
                "wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", condition=" + condition +
                ", pubDate=" + pubDate +
                '}';
    }
}
