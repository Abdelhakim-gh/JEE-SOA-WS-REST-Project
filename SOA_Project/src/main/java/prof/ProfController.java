package prof;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import user.User;

@Path("/Profs")
public class ProfController {

	ProfService profService = new ProfService();
	
	@SuppressWarnings("unused")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getProfs() {
		// Assuming userService is an instance of a service class that retrieves the user from the database
		ArrayList<Prof> profs = profService.getProfs();
		
//		System.out.println(profs.toString());
		
		// Check if the user exists
		if (profs != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(profs);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Profs not found").build();
		}
	}

	@SuppressWarnings("unused")
	@GET	
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") int id) {
	    // Assuming userService is an instance of a service class that retrieves the user from the database
	    Prof prof = profService.getProf(id);
	    
	    System.out.println(prof.toString());

	    // Check if the user exists
	    if (prof != null) {
		    Jsonb jsonb = JsonbBuilder.create();
		    String json = jsonb.toJson(prof);
	        // Return the user object as JSON
	        return Response.status(Response.Status.OK).entity(json).build();
	    } else {
	        // If user does not exist, return a 404 Not Found response
	        return Response.status(Response.Status.NOT_FOUND).entity("Prof not found").build();
	    }
	}
	
	@GET
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response addProf(
			    @QueryParam("id_user") int id_user,
			    @QueryParam("id_prof") int id_prof,
			    @QueryParam("nom") String nom,
			    @QueryParam("prenom") String prenom,
			    @QueryParam("email") String email,
			    @QueryParam("tel") String tel,
			    @QueryParam("password") String password) {
	    
		User user = new User(id_user ,nom, prenom, email, tel, password, "prof");
		System.out.println(user);

		Prof created_prof = profService.addProf(user, id_prof);
		
		System.out.println(created_prof.toString());
		
		// Check if the user exists
		if (created_prof != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(created_prof);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to add Prof !").build();
		}
	}	

	@GET
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response editProf(
		    @QueryParam("id_prof") int id_prof,
		    @QueryParam("salaire") float salaire,
		    @QueryParam("payement") int payement,
		    @QueryParam("matieres") String matieres
		) {
		profService.updateProf(id_prof, salaire, payement, matieres);
		// Return the user object as JSON
		return Response.status(Response.Status.OK).entity("Prof edit successfully").build();
	}
	
	@DELETE
	@Path("/remove/{id}")
	public Response removeEtudiant (@PathParam("id") int id_prof) {
	    int nb = profService.deleteProf(id_prof);
	    if (nb == 0) {
	        return Response.status(Response.Status.NOT_FOUND)
	                .entity("No Prof found with the specified ID.")
	                .build();
	    } else if (nb < 0) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity("Failed to remove Prof. Please try again later.")
	                .build();
	    } else {
	        return Response.status(Response.Status.OK)
	                .entity("Prof removed successfully.")
	                .build();
	    }
	}
	
	
}
