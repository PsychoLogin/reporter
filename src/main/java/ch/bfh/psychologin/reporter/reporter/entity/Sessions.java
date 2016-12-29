package ch.bfh.psychologin.reporter.reporter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Jan on 28.12.2016.
 */
@Entity
@Table(name = "sessions")
public class Sessions implements Serializable {

    @Id
    private long id;

    @Column
    private Time  start;

    @Column
    private Time  stop;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getStop() {
        return stop;
    }

    public void setStop(Time stop) {
        this.stop = stop;
    }
}
