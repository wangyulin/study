package spittr;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by wangyulin on 17/01/2017.
 */
public class Spittle {
    private final Long id;
    private final String message;
    private final Date time;
    private Double latitude;
    private Double longitude;

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message, Date time, Double longitude, Double latitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals ( this, that);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode ( this );
    }
}
