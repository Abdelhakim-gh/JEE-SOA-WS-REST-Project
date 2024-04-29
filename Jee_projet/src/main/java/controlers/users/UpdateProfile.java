package controlers.users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.dao.UserDAO;

import java.io.IOException;

/**
 * Servlet implementation class UpdateProfile
 */
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_user = Integer.parseInt(request.getParameter("id_user"));
		String role = request.getParameter("role");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email"); 
		String password = request.getParameter("password");
		String tel = request.getParameter("tel");
		
		System.out.println("\nid_user: "+id_user);
		System.out.println("role: "+role);
		System.out.println("nom: "+nom);
		System.out.println("prenom: "+prenom);
		System.out.println("email: "+email);
		System.out.println("password: "+password);
		System.out.println("tel: "+tel);
		
		UserDAO userDAO = new UserDAO();
		int nb = userDAO.updateUser(id_user, role, email, password, tel, nom, prenom);
		System.out.println("Update Profile NB: "+nb);
		if (nb == -1) {
			request.setAttribute("update_profile_error", "Profile failed to Upadate!");
			if (role.compareToIgnoreCase("admin")==0) {
				RequestDispatcher dis = request.getRequestDispatcher("admin_profile.jsp");
				dis.forward(request, response);	
			}
			else if (role.compareToIgnoreCase("prof")==0) {
				RequestDispatcher dis = request.getRequestDispatcher("prof_profile.jsp");
				dis.forward(request, response);	
			}
			else {
				RequestDispatcher dis = request.getRequestDispatcher("etudiant_profile.jsp");
				dis.forward(request, response);	
			}
		}
		else {
			// Construct the URL with query parameters
			String redirectUrl = "Login?email=" + email + "&password=" + password;
			// Redirect the client to the Login servlet with parameters
			response.sendRedirect(redirectUrl);
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
