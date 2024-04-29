package classe;

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

@Path("Classes")
public class ClasseController {

	ClasseService classeService = new ClasseService();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getClasses() {
		// Assuming etudiantService is an instance of a service class that retrieves the user from the database
		ArrayList<Classe> classes = classeService.getClasses();
		
//		System.out.println(classes.toString());
		
		// Check if the user exists
		if (classes != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(classes);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Classes not found").build();
		}
	}
	
	@GET	
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getClasse(@PathParam("id") int id) {
	    // Assuming is an instance of a service class that retrieves the user from the database
	    Classe classe = classeService.getClasse(id);
	    
//	    System.out.println(classe.toString());

	    // Check if the user exists
	    if (classe != null) {
		    Jsonb jsonb = JsonbBuilder.create();
		    String json = jsonb.toJson(classe);
	        // Return the user object as JSON
	        return Response.status(Response.Status.OK).entity(json).build();
	    } else {
	        // If user does not exist, return a 404 Not Found response
	        return Response.status(Response.Status.NOT_FOUND).entity("Classe not found").build();
	    }
	}
	
	@GET
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response addClasse(
		        @QueryParam("id_classe") int id_classe,
		        @QueryParam("label") String label,
		        @QueryParam("section_id") int section_id
		        
			 ) {
	    
		Classe created_classe = classeService.addClasse(id_classe, label, section_id);
//		System.out.println(created_classe.toString());
		
		// Check if the user exists
		if (created_classe != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(created_classe);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to add Classe !").build();
		}
	}
	
	@GET
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response editClasse(
		        @QueryParam("id_classe") int id_classe,
		        @QueryParam("label") String label		        
			 ) {
	    
		Classe edit_classe = classeService.editClasse(id_classe, label);
//		System.out.println(edit_classe.toString());
		
		// Check if the user exists
		if (edit_classe != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(edit_classe);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to edit Classe !").build();
		}
	}
	
	@DELETE
	@Path("/remove/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response removeClasse(
			 	@PathParam("id") int id_classe,
		        @QueryParam("nb_etud") int nb_etud		        
			 ) {
	    if (nb_etud > 0) {
	    	return Response.status(Response.Status.CONFLICT)
	    			.entity("Cant remove classe with Etudiant in it, Please try again later.")
	    			.build();
	    } else {
		    int nb = classeService.deleteClasse(id_classe);
		    if (nb == 0) {
		        return Response.status(Response.Status.NOT_FOUND)
		                .entity("No Classe found with the specified ID.")
		                .build();
		    } else if (nb < 0) {
		        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		                .entity("Failed to remove Classe . Please try again later.")
		                .build();
		    } else {
		        return Response.status(Response.Status.OK)
		                .entity("Classe removed successfully.")
		                .build();
		    }
	    }
	}
	
}
