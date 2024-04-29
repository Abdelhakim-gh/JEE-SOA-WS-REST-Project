package test;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/test")
public class TestController {
	
	@GET()
	public String Hello() {
		return "Hello World !";
	}
	
}
