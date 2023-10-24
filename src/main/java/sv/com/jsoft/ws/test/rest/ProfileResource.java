package sv.com.jsoft.ws.test.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import sv.com.jsoft.ws.test.services.TokenService;

@Path("/profile")
@RequestScoped
public class ProfileResource {

    @Inject
    TokenService tokenService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test(){
        return "Esta es una prueba";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public String register(String body){
        JsonObject json = new Gson().fromJson(body, JsonObject.class);
        JsonObject response = new JsonObject();

        response.addProperty("token",
                tokenService.generateToken(json.get("email").getAsString(),
                        json.get("user").getAsString(),
                        json.get("date").getAsString(),
                        json.get("role").getAsString()));

        return response.toString();
    }
}
