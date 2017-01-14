package ch.bfh.psychologin.reporter.reporter.entity;

import org.hibernate.sql.JoinType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Jan on 14.01.2017.
 */

@NamedQuery(name = Alert.GET_ALL, query = "SELECT a from Alert a JOIN FETCH a.blogUser")
@Entity
@Table(name = "alerts")
public class Alert {

    public static final String GET_ALL = "getAll";

    @Id
    private long id;

    @Column(name = "severity")
    private String severity;

    @Column(name = "time_stamp")
    private Date timeStamp;

    @JoinColumn(name = "blog_user_id")
    @ManyToOne
    private BlogUser blogUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BlogUser getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(BlogUser blogUser) {
        this.blogUser = blogUser;
    }
}
