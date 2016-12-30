package ch.bfh.psychologin.reporter.reporter.boundry;

import ch.bfh.psychologin.reporter.reporter.entity.StaticSessionData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Jan on 28.12.2016.
 */
@Stateless
@Path("report")
public class ReportResource {

    @PersistenceUnit(unitName = "pyslogin")
    EntityManagerFactory emf;


    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    @GET
    public Response getStaticSessionData() {
        EntityManager entityManager = emf.createEntityManager();
        List<StaticSessionData> resultList = entityManager.createNamedQuery(StaticSessionData.FIND_ALL, StaticSessionData.class)
                .getResultList();

        entityManager.close();
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(resultList)
                .build();
    }
}
