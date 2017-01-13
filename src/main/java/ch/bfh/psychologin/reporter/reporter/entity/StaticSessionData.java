package ch.bfh.psychologin.reporter.reporter.entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Jan on 28.12.2016.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = StaticSessionData.FIND_ALL, query = "SELECT s from StaticSessionData s JOIN FETCH s.session"),
        @NamedQuery(name = StaticSessionData.USER_DATA_BROWSER, query = "SELECT count(s), s.browser as browser from StaticSessionData s where s.session.blogUser.userName = :username group by s.browser"),
        @NamedQuery(name = StaticSessionData.USER_DATA_LOCATION, query = "SELECT count(s), s.location as browser from StaticSessionData s where s.session.blogUser.userName = :username group by s.location")

})
@Table(name = "static_session_datas")
public class StaticSessionData {

    public static final String FIND_ALL = "StaticSessionData.findAll";
    public static final String USER_DATA_BROWSER = "userDataBrowser";
    public static final String USER_DATA_LOCATION = "userDataLocation";
    @Id
    @Column(name = "session_id")
    private long sessionId;

    @OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "session_id")
    private Sessions session;

    @Column(name = "os")
    private String operatingSystem;

    @Column(name = "lang")
    private String language;

    @Column(name = "browser")
    private String browser;

    @Column(name = "location")
    private String location;

    @Column(name = "referrer")
    private String referrer;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Sessions getSession() {
        return session;
    }

    public void setSession(Sessions session) {
        this.session = session;
    }
}
