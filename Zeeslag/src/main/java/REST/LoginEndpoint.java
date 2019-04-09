package REST;


import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginEndpoint {


    private final Gson gson = new Gson();

    // region get
    @Path("/{login}")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public Response getLogin(@PathParam("login") String login) {

        boolean success = Info.getInstance().login(Info.getInstance().splitter(login));
        return Response.status(200).entity(gson.toJson(success)).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMessage(Combi combi){
        Info.getInstance().getCombis().add(combi);

        return Response.status(200).entity(gson.toJson(Info.getInstance().getCombis())).build();
    }
}
