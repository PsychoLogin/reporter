package ch.bfh.psychologin.reporter.reporter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by othma on 02.01.2017.
 */
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    private long id;


    @Column(name = "start")
    private Date session_start;
    @Column(name = "stop")
    private Date session_stop;

    @Column(name = "blog_user_id", insertable = false, updatable = false)
    private long blogUserId;

    @ManyToOne(optional = false)
    @JoinColumn(name="blog_user_id")
    private BlogUser blogUser;

    @OneToMany(mappedBy="session")
    private List<Action> actions;

}
