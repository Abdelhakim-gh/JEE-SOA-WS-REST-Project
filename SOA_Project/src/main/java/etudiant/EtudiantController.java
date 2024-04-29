package etudiant;

import java.util.ArrayList;
import java.util.HashMap;
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
import etudiant.*;
import user.User;

@Path("/Etudiants")
public class EtudiantController {

	EtudiantService etudiantService = new EtudiantService();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getEtudiants() {
		// Assuming etudiantService is an instance of a service class that retrieves the user from the database
		ArrayList<Etudiant> etudiants = etudiantService.getEtudiants();
		
		System.out.println(etudiants.toString());
		
		// Check if the user exists
		if (etudiants != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(etudiants);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Etudtiants not found").build();
		}
	}
	
	@SuppressWarnings("unused")
	@GET	
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getEtudiant(@PathParam("id") int id) {
	    // Assuming etudiantService is an instance of a service class that retrieves the user from the database
	    Etudiant etudiant = etudiantService.getEtudiant(id);
	    
//	    System.out.println(etudiant.toString());

	    // Check if the user exists
	    if (etudiant != null) {
		    Jsonb jsonb = JsonbBuilder.create();
		    String json = jsonb.toJson(etudiant);
	        // Return the user object as JSON
	        return Response.status(Response.Status.OK).entity(json).build();
	    } else {
	        // If user does not exist, return a 404 Not Found response
	        return Response.status(Response.Status.NOT_FOUND).entity("Etudiant not found").build();
	    }
	}
	
	@GET
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response addUser(
		        @QueryParam("id_user") int id_user,
		        @QueryParam("nom") String nom,
		        @QueryParam("prenom") String prenom,
		        @QueryParam("email") String email,
		        @QueryParam("tel") String tel,
		        @QueryParam("password") String password,
		        @QueryParam("id_etud") int id_etud,		        
		        @QueryParam("cne") String cne,
		        @QueryParam("section_id") int section_id
		        
			 ) {
	    
	    User user = new User(id_user, nom, prenom, email, tel, password, "etudiant");
		Etudiant created_etud = etudiantService.addEtudiant(user, id_etud, cne, section_id);
		System.out.println(created_etud.toString());
		// Check if the user exists
		if (created_etud != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(created_etud);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to add Etudiant !").build();
		}
	}	
	
	@GET
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response editEtudiant(
		@QueryParam("id_etud") int id_etud,
		@QueryParam("classe_id") int classe_id,
		@QueryParam("payement") int payement
		) {
		etudiantService.updateEtudiant(id_etud, payement, classe_id);
		// Return the user object as JSON
		return Response.status(Response.Status.OK).entity("Etudiant edit successfully").build();
	}
	
	@DELETE
	@Path("/remove/{id}")
	public Response removeEtudiant (@PathParam("id") int id_etud) {
	    int nb = etudiantService.deleteEtudiant(id_etud);
	    if (nb == 0) {
	        return Response.status(Response.Status.NOT_FOUND)
	                .entity("No Etudiant  found with the specified ID.")
	                .build();
	    } else if (nb < 0) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity("Failed to remove Etudiant . Please try again later.")
	                .build();
	    } else {
	        return Response.status(Response.Status.OK)
	                .entity("Etudiant removed successfully.")
	                .build();
	    }
	}
	
}
