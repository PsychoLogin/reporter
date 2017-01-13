package ch.bfh.psychologin.reporter.reporter.boundry;

import ch.bfh.psychologin.reporter.reporter.entity.Sessions;
import ch.bfh.psychologin.reporter.reporter.entity.StaticSessionData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
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
        List<StaticSessionData> resultList = entityManager
                .createNamedQuery(StaticSessionData.FIND_ALL, StaticSessionData.class)
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

    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{name}/location")
    @GET()
    public Response getUsersLocation(@PathParam("name") String username) {

        List<HashMap<String, Object>> response = new ArrayList<>();

        EntityManager em = emf.createEntityManager();

        List<Object[]> sessionDatas = em
                .createNamedQuery(StaticSessionData.USER_DATA_LOCATION)
                .setParameter("username", username)
                .getResultList();

        em.close();

        mapToNameValue(response, sessionDatas);


        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(response)
                .build();
    }


    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{name}/browser")
    @GET()
    public Response getUsersBrowser(@PathParam("name") String username) {

        List<HashMap<String, Object>> response = new ArrayList<>();

        EntityManager em = emf.createEntityManager();

        List<Object[]> sessionDatas = em
                .createNamedQuery(StaticSessionData.USER_DATA_BROWSER)
                .setParameter("username", username)
                .getResultList();

        em.close();

        mapToNameValue(response, sessionDatas);


        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(response)
                .build();
    }

    private void mapToNameValue(List<HashMap<String, Object>> response, List<Object[]> sessionDatas) {
        sessionDatas.forEach(row -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("value", row[0]);
            map.put("name", row[1]);
            response.add(map);
        });
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @GET
    public Response getUsers() {
        List<HashMap<String, Object>> response = new ArrayList<>();

        EntityManager entityManager = emf.createEntityManager();
        List<Object[]> resultList = entityManager
                .createNamedQuery(Sessions.USER_LOGINS)
                .getResultList();

        entityManager.close();

        resultList.forEach(row -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("value", row[0]);
            map.put("id", row[1]);
            map.put("name", row[2]);
            response.add(map);
        });

        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(response)
                .build();
    }
}
