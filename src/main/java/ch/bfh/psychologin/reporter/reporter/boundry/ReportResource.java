package ch.bfh.psychologin.reporter.reporter.boundry;

import ch.bfh.psychologin.reporter.reporter.control.ActionTransformer;
import ch.bfh.psychologin.reporter.reporter.entity.Action;
import ch.bfh.psychologin.reporter.reporter.entity.Alert;
import ch.bfh.psychologin.reporter.reporter.entity.Keystroke;
import ch.bfh.psychologin.reporter.reporter.entity.KeystrokeSession;
import ch.bfh.psychologin.reporter.reporter.entity.Sessions;
import ch.bfh.psychologin.reporter.reporter.entity.StaticSessionData;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
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

    @Inject
    ActionTransformer transformer;

    @PersistenceContext(unitName = "psylogin")
    EntityManager em;

    @Produces(MediaType.APPLICATION_JSON)
    @Path("alert")
    @GET
    public Response getAllArrays() {
        List<Alert> resultList = em
                .createNamedQuery(Alert.GET_ALL, Alert.class)
                .getResultList();

        return createResponse(resultList);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{name}/keystrokes")
    @GET
    public Response getKeystrokes(@PathParam("name") String name) {

        List<Action> resultList = em
                .createNamedQuery(Action.GET_KEY_DATA, Action.class)
                .setParameter("blogUserName", name)
                .getResultList();

        List<KeystrokeSession> keystrokesPerSession = transformer.transformToKeystrokesPerSession(resultList);

        return createResponse(keystrokesPerSession);
    }


    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    @GET
    public Response getStaticSessionData() {

        List<StaticSessionData> resultList = em
                .createNamedQuery(StaticSessionData.FIND_ALL, StaticSessionData.class)
                .getResultList();


        return createResponse(resultList);
    }



    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{name}/location")
    @GET()
    public Response getUsersLocation(@PathParam("name") String username) {

        List<HashMap<String, Object>> response = new ArrayList<>();


        List<Object[]> sessionDatas = em
                .createNamedQuery(StaticSessionData.USER_DATA_LOCATION)
                .setParameter("username", username)
                .getResultList();


        mapToNameValue(response, sessionDatas);



        return createResponse(response);
    }


    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{name}/browser")
    @GET()
    public Response getUsersBrowser(@PathParam("name") String username) {

        List<HashMap<String, Object>> response = new ArrayList<>();


        List<Object[]> sessionDatas = em
                .createNamedQuery(StaticSessionData.USER_DATA_BROWSER)
                .setParameter("username", username)
                .getResultList();


        mapToNameValue(response, sessionDatas);

        return createResponse(response);
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

        List<Object[]> resultList = em
                .createNamedQuery(Sessions.USER_LOGINS)
                .getResultList();

        resultList.forEach(row -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("value", row[0]);
            map.put("id", row[1]);
            map.put("name", row[2]);
            response.add(map);
        });

        return createResponse(response);

//        return Response.ok()
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                .header("Access-Control-Max-Age", "1209600")
//                .entity(response)
//                .build();
    }

    private Response createResponse(List<?> resultList) {
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
