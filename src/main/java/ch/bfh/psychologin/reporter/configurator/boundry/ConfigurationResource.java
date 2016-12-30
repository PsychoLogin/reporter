package ch.bfh.psychologin.reporter.configurator.boundry;

import ch.bfh.psychologin.reporter.configurator.entity.StaticAnalyseConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jan on 30.12.2016.
 */

@Path("config")
public class ConfigurationResource {

    @PersistenceUnit(unitName = "psylogin_config")
    EntityManagerFactory emf;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("static")
    @GET
    public Response getStaticConfiguration() {

        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<StaticAnalyseConfig> namedQuery = entityManager.createNamedQuery(StaticAnalyseConfig.GET_CONFIG, StaticAnalyseConfig.class);
        StaticAnalyseConfig result = namedQuery.getSingleResult();
        entityManager.close();

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(result)
                .build();
    }
}
