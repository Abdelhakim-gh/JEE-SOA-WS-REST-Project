package matiere;

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

@Path("/Matieres")
public class MatiereController {

	MatiereService matiereService = new MatiereService();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getMatieres() {
		// Assuming is an instance of a service class that retrieves the user from the database
		ArrayList<Matiere> matieres = matiereService.getMatieres();
		
//		System.out.println(matieres.toString());
		
		// Check if the user exists
		if (matieres != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(matieres);
			// Return the object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Matieres not found").build();
		}
	}
	
	@GET	
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getMatiere(@PathParam("id") int id) {
	    // Assuming is an instance of a service class that retrieves the user from the database
	    Matiere matiere = matiereService.getMatiere(id);
	    
//	    System.out.println(matiere.toString());

	    // Check if the user exists
	    if (matiere != null) {
		    Jsonb jsonb = JsonbBuilder.create();
		    String json = jsonb.toJson(matiere);
	        // Return the user object as JSON
	        return Response.status(Response.Status.OK).entity(json).build();
	    } else {
	        // If user does not exist, return a 404 Not Found response
	        return Response.status(Response.Status.NOT_FOUND).entity("Matiere not found").build();
	    }
	}
	
	@GET
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response addMatiere(
		        @QueryParam("id_matiere") int id_matiere,
		        @QueryParam("label") String label,
		        @QueryParam("prix") float prix,
		        @QueryParam("section_id") int section_id
			 ) {
	    
		Matiere created_matiere = matiereService.addMatiere(id_matiere, label, prix, section_id);
//		System.out.println(created_matiere.toString());
		
		// Check if the user exists
		if (created_matiere != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(created_matiere);
			// Return object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to add Matiere !").build();
		}
	}
	
	@GET
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response editMatiere(
		        @QueryParam("id_matiere") int id_matiere,
		        @QueryParam("label") String label,
		        @QueryParam("prix") float prix	        
			 ) {
	    
		Matiere edit_matiere = matiereService.editMatiere(id_matiere, label, prix);
//		System.out.println(edit_matiere.toString());
		
		// Check if the user exists
		if (edit_matiere != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(edit_matiere);
			// Return the object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to edit Matiere !").build();
		}
	}
	
	@DELETE
	@Path("/remove/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response removeMatiere(@PathParam("id") int id_matiere) {
	    
		int nb = matiereService.deleteMatiere(id_matiere);
	    if (nb == 0) {
	        return Response.status(Response.Status.NOT_FOUND)
	                .entity("No Matiere found with the specified ID.")
	                .build();
	    } else if (nb < 0) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity("Failed to remove Matiere . Please try again later.")
	                .build();
	    } else {
	        return Response.status(Response.Status.OK)
	                .entity("Matiere removed successfully.")
	                .build();
	    }
	}
	
}
