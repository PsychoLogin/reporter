package ch.bfh.psychologin.reporter.reporter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by othma on 02.01.2017.
 */

@NamedQueries(
        {
                @NamedQuery(name = Action.GET_KEY_DATA, query = "select a From Action a where a.sessionId IN (SELECT id from Session s where s.blogUser.userName = :blogUserName) and a.actionTypeId = 38 ORDER BY a.timestamp ASC")
        }
)
@Entity
@Table(name = "actions")
public class Action {

    public static final String GET_KEY_DATA = "getKeystrokes";
    @Id
    private long id;

    @Column(name = "session_id", insertable = false, updatable = false)
    private long sessionId;

    @ManyToOne(optional = false)
    @JoinColumn(name="session_id")
    private Session session;

    @Column(name = "action_type_id", insertable = false, updatable = false)
    private long actionTypeId;

    @ManyToOne(optional = false)
    @JoinColumn(name="action_type_id")
    private ActionType actionType;

    @Column(name = "time_stamp")
    private Date timestamp;

    public long getId() {
        return id;
    }

    public long getSessionId() {
        return sessionId;
    }

    public Session getSession() {
        return session;
    }

    public long getActionTypeId() {
        return actionTypeId;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
