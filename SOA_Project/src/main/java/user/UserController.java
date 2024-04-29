package user;

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

@Path("/Users")
public class UserController {
	
	UserService userService = new UserService();
	
	@SuppressWarnings("unused")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getUsers() {
		// Assuming userService is an instance of a service class that retrieves the user from the database
		ArrayList<User> users = userService.getUsers();
		
		// System.out.println(users.toString());
		
		// Check if the user exists
		if (users != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(users);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Users not found").build();
		}
	}

	@SuppressWarnings("unused")
	@GET	
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") int id) {
	    // Assuming userService is an instance of a service class that retrieves the user from the database
	    User user = userService.getUser(id);
	    
	    // System.out.println(user.toString());

	    // Check if the user exists
	    if (user != null) {
		    Jsonb jsonb = JsonbBuilder.create();
		    String json = jsonb.toJson(user);
	        // Return the user object as JSON
	        return Response.status(Response.Status.OK).entity(json).build();
	    } else {
	        // If user does not exist, return a 404 Not Found response
	        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
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
	            @QueryParam("role") String role) {
	    
		User user = new User(id_user ,nom, prenom, email, tel, password, role);
	    Map<String, Object> created_user = new HashMap<>();
		created_user = userService.addUser(user);
		
		// Check if the user exists
		if (created_user != null) {
			Jsonb jsonb = JsonbBuilder.create();
			String json = jsonb.toJson(created_user);
			// Return the user object as JSON
			return Response.status(Response.Status.OK).entity(json).build();
		} else {
			// If user does not exist, return a 404 Not Found response
			return Response.status(Response.Status.NOT_FOUND).entity("Failed to add User !").build();
		}
	}	

//	@POST
//	@Path("/add")
//	@Consumes(MediaType.APPLICATION_JSON)
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response addUser(User user) {
//		User created_user = userService.addUser(user);
//		
//		System.out.println(created_user.toString());
//		
//		Jsonb jsonb = JsonbBuilder.create();
//		String json = jsonb.toJson(created_user);
//		
//		// Check if the user exists
//		if (created_user != null) {
//			// Return the user object as JSON
//			return Response.status(Response.Status.OK).entity(json).build();
//		} else {
//			// If user does not exist, return a 404 Not Found response
//			return Response.status(Response.Status.NOT_FOUND).entity("Failed to add User !").build();
//		}
//	}
	
@GET
@Path("/edit")
public Response editUser(
		@QueryParam("id_user") int id_user,
        @QueryParam("role") String role,
        @QueryParam("email") String email,
        @QueryParam("password") String password,
        @QueryParam("tel") String tel,
        @QueryParam("nom") String nom,
        @QueryParam("prenom") String prenom) {
	
	User edit_user = userService.updateUser(id_user, role, email, password, tel, nom, prenom);
	
	// Check if the user exists
	if (edit_user != null) {
		Jsonb jsonb = JsonbBuilder.create();
		String json = jsonb.toJson(edit_user);
		// Return the user object as JSON
		return Response.status(Response.Status.OK).entity(json).build();
	} else {
		// If user does not exist, return a 404 Not Found response
		return Response.status(Response.Status.NOT_FOUND).entity("Failed to edit User !").build();
	}
}
		
//		@PUT
//		@Path("/edit")
//		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//		public Response editUser(User user) {
//			
//			User edit_user = userService.updateUser(user);
//			
//			// Check if the user exists
//			if (edit_user != null) {
//				Jsonb jsonb = JsonbBuilder.create();
//				String json = jsonb.toJson(edit_user);
//				// Return the user object as JSON
//				return Response.status(Response.Status.OK).entity(json).build();
//			} else {
//				// If user does not exist, return a 404 Not Found response
//				return Response.status(Response.Status.NOT_FOUND).entity("Failed to edit User !").build();
//			}
//		}
		
//	@PUT
//	@Path("/edit/{id}")
//	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public Response editUser(@PathParam("id") int id_user, User user) {
//		user.setId_user(id_user);
//		User edit_user = userService.updateUser(user);
//		// Check if the user exists
//		if (edit_user != null) {
//			Jsonb jsonb = JsonbBuilder.create();
//			String json = jsonb.toJson(edit_user);
//			// Return the user object as JSON
//			return Response.status(Response.Status.OK).entity(json).build();
//		} else {
//			// If user does not exist, return a 404 Not Found response
//			return Response.status(Response.Status.NOT_FOUND).entity("Failed to edit User !").build();
//		}
//	}
		
	
	@DELETE
	@Path("/remove/{id}")
	public Response removeUser(@PathParam("id") int id_user) {
	    int nb = userService.deleteUser(id_user);
	    if (nb == 0) {
	        return Response.status(Response.Status.NOT_FOUND)
	                .entity("No user found with the specified ID.")
	                .build();
	    } else if (nb < 0) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity("Failed to remove user. Please try again later.")
	                .build();
	    } else {
	        return Response.status(Response.Status.OK)
	                .entity("User removed successfully.")
	                .build();
	    }
	}

}
