package ch.bfh.psychologin.reporter.reporter.boundry;

import ch.bfh.psychologin.reporter.reporter.entity.Sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Jan on 28.12.2016.
 */
@Stateless
@Path("report")
public class ReportResource {

    @PersistenceUnit(unitName = "pyslogin")
    EntityManagerFactory emf;

    @GET
    public List<Sessions> getSomething() {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Sessions> query = entityManager.createQuery("SELECT s FROM Sessions s", Sessions.class);
        return query.getResultList();

    }
}
