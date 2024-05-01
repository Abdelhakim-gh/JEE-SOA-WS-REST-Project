package authentification;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import etudiant.Etudiant;
import prof.Prof;
import user.*;

@Path("Auth")
public class AuthController {

	AuthService authService = new AuthService();
	
	@GET
	@Path("/Login")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response loginUser(@QueryParam("email") String email, @QueryParam("password") String password) {
        
		if (email == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Email and password are required").build();
        }
        
        Object user = authService.userlogin(email, password);

        if (user != null) {
        
        	if (user instanceof Etudiant) {
        		user = (Etudiant) user;
        	} else if (user instanceof Prof) {
        		user = (Prof) user;
        	}        	
        	
    		Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(user);
            return Response.status(Response.Status.OK).entity(json).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").build();
        }
        
    }
	
	
}
