package ch.bfh.psychologin.reporter.reporter.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 14.01.2017.
 */
public class KeystrokeSession {
    private long name;
    private List<Keystroke> series;

    public KeystrokeSession() {
        this.series = new ArrayList<>();
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public List<Keystroke> getSeries() {
        return series;
    }

    public void setSeries(List<Keystroke> series) {
        this.series = series;
    }
}
