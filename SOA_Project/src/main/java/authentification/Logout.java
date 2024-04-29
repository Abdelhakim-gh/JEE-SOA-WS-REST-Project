package authentification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.User;

import java.io.IOException;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Your existing code...

        // Get the HttpSession object from the request
    	HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");	
        if (user != null) {
	        if (user.getRole().equalsIgnoreCase("etudiant")) {
	        	session.removeAttribute("etudiant");
	        	session.removeAttribute("user");
	        	session.invalidate();
	        } 
	        else if (user.getRole().equalsIgnoreCase("prof")) {
	        	session.removeAttribute("prof");
	        	session.removeAttribute("user");
	        	session.invalidate();
	        } 
	        else {
	        	session.removeAttribute("user");
	        	session.invalidate();
	        }
	        response.sendRedirect("views/login.jsp");
        } 
        else {
            // Handle the case where the user attribute is null
            response.sendRedirect("views/login.jsp");
        }

    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
