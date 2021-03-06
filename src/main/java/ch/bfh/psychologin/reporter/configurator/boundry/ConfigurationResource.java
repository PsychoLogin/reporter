package ch.bfh.psychologin.reporter.configurator.boundry;

import ch.bfh.psychologin.reporter.configurator.entity.StaticAnalyseConfig;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jan on 30.12.2016.
 */

@Stateless
@Path("config")
public class ConfigurationResource {

    @PersistenceContext(unitName = "psylogin")
    EntityManager em;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("static")
    @GET
    public Response getStaticConfiguration() {

        TypedQuery<StaticAnalyseConfig> namedQuery = em.createNamedQuery(StaticAnalyseConfig.GET_CONFIG, StaticAnalyseConfig.class);
        StaticAnalyseConfig result = namedQuery.getSingleResult();

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(result)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("static")
    public Response updateStaticConfiguration(StaticAnalyseConfig config) {
        TypedQuery<StaticAnalyseConfig> namedQuery = em.createNamedQuery(StaticAnalyseConfig.GET_CONFIG, StaticAnalyseConfig.class);
        StaticAnalyseConfig result = namedQuery.getSingleResult();
        result.setMinimumNumberLogins(config.getMinimumNumberLogins());
        result.setPenaltyErrorLevel(config.getPenaltyErrorLevel());
        result.setPenaltyWarningLevel(config.getPenaltyWarningLevel());
        em.persist(result);
        return Response.noContent()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }
}
