package rest;

import Fetcher.UrlFetcher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoredDTO;
import dtos.CatDTO;
import dtos.DogDTO;
import dtos.OurDTO;
import entities.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import facades.UserFacade;
import utils.EMF_Creator;
import utils.HttpUtils;

/**
 * @author lam@cphbusiness.dk
 */
@Path("/info")
public class DemoResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final UserFacade userFacade = UserFacade.getUserFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {
        //return userFacade.getAllUsers();
        return "Hello";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(String user) {
        User u1 = gson.fromJson(user, User.class);
        userFacade.createUser(u1.getUserName(), u1.getUserPass());
        return "{\"msg\": \"Welcome: " + u1.getUserName() + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUrls() throws IOException, ExecutionException, InterruptedException {
        Gson gson = new Gson();
        String bored = HttpUtils.fetchData("https://www.boredapi.com/api/activity");
        BoredDTO boredDTO = gson.fromJson(bored, BoredDTO.class);
        String cat = HttpUtils.fetchData("https://catfact.ninja/fact");
        CatDTO catDTO = gson.fromJson(cat, CatDTO.class);
        String dog = HttpUtils.fetchData("https://dog.ceo/api/breeds/image/random");
        DogDTO dogDTO = gson.fromJson(dog, DogDTO.class);

        OurDTO dataFeched = UrlFetcher.runParrallel();
        String combinedJSON = gson.toJson(dataFeched);
        return combinedJSON;
    }
}