package section;

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

@Path("/Sections")
public class SectionController {

	SectionService sectionService = new SectionService();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getSections() {
		// Assuming an instance of a service class that retrieves the user from the database
		ArrayList<Section> sections = sectionService.getSections();
		
//		System.out.println(sections.toString());
		
		// Check if exists
		if (sections != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(sections);
			// Return the object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Sections not found").build();
		}
	}
	
	@GET	
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getSection(@PathParam("id") int id) {
	    // Assuming instance of a service class that retrieves the user from the database
	    Section section = sectionService.getSection(id);
	    
//	    System.out.println(section.toString());

	    // Check if exists
	    if (section != null) {
		    Jsonb jsonb = JsonbBuilder.create();
		    String json = jsonb.toJson(section);
	        // Return the object as JSON
	        return Response.status(Response.Status.OK).entity(json).build();
	    } else {
	        // If user does not exist, return a 404 Not Found response
	        return Response.status(Response.Status.NOT_FOUND).entity("Section not found").build();
	    }
	}
	
	@GET
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response addSection(
		        @QueryParam("id_section") int id_section,
		        @QueryParam("niveau") String niveau,
		        @QueryParam("filiere") String filiere
			 ) {
	    
		Section created_section = sectionService.addSection(id_section, niveau, filiere);
//		System.out.println(created_section.toString());
		
		// Check if exists
		if (created_section != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(created_section);
			// Return object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to add Section !").build();
		}
	}
	
	@GET
	@Path("/edit")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response editSection(
		        @QueryParam("id_section") int id_section,
		        @QueryParam("niveau") String niveau,
		        @QueryParam("filiere") String filiere    
			 ) {
	    
		Section edit_section = sectionService.editSection(id_section, niveau, filiere);
//		System.out.println(edit_section.toString());
		
		// Check if exists
		if (edit_section != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(edit_section);
			// Return the object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to edit Section !").build();
		}
	}
	
	@DELETE
	@Path("/remove/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	 public Response removeSection(
			 	@PathParam("id") int id_section,
		        @QueryParam("nb_etuds") int nb_etuds,
		        @QueryParam("nb_matieres") int nb_matieres,
		        @QueryParam("nb_classes") int nb_classes) {
		
	    if (nb_matieres > 0 || nb_etuds > 0 || nb_classes > 0) {
	    	return Response.status(Response.Status.CONFLICT)
	    			.entity("Cant remove Section with Elements associated with it, Please try again later.")
	    			.build();
	    } else {
		    int nb = sectionService.deleteSection(id_section);
		    if (nb == 0) {
		        return Response.status(Response.Status.NOT_FOUND)
		                .entity("No Section found with the specified ID.")
		                .build();
		    } else if (nb < 0) {
		        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		                .entity("Failed to remove Section . Please try again later.")
		                .build();
		    } else {
		        return Response.status(Response.Status.OK)
		                .entity("Section removed successfully.")
		                .build();
		    }
	    }
	}
	
}
