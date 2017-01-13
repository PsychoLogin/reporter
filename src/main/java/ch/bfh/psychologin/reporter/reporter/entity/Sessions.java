package ch.bfh.psychologin.reporter.reporter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Jan on 28.12.2016.
 */
@NamedQueries(
        {
                @NamedQuery(name = Sessions.USER_LOGINS, query = "SELECT count(*) as count , bu.id, bu.userName from Sessions s LEFT JOIN  s.blogUser bu GROUP BY s.blogUserId")
        }
)
@Entity
@Table(name = "sessions")
public class Sessions implements Serializable {

    public static final String USER_LOGINS = "UserLogins";
    @Id
    private long id;

    @Column(name = "blog_user_id", insertable = false, updatable = false)
    private long blogUserId;

    @ManyToOne
    @JoinColumn(name = "blog_user_id")
    private BlogUser blogUser;

    @Column
    private Time start;

    @Column
    private Time stop;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBlogUserId() {
        return blogUserId;
    }

    public void setBlogUserId(long blogUserId) {
        this.blogUserId = blogUserId;
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

    public BlogUser getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(BlogUser blogUser) {
        this.blogUser = blogUser;
    }
}
