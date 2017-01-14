package ch.bfh.psychologin.reporter.reporter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by othma on 02.01.2017.
 */
@Entity
@Table(name = "action_types")
public class ActionType {

    @Id
    private long id;

    @Column(name = "title")
    private String actionTypeTitle;

    @Column(name = "description")
    private String actionTypeDescription;

    @OneToMany(mappedBy="actionType")
    private List<Action> actions;
}
