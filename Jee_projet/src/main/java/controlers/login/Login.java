package controlers.login;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Etudiant;
import models.Prof;
import models.User;
import models.Datatypes.Role;
import models.dao.AuthDAO;
import tools.DBInteraction;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * authentification method that takes email & password
	 * then check if there is any user match the credintent 
	 */
    @SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        AuthDAO auth = new AuthDAO();
        User user = auth.getUser(email, password);
        
        //System.out.println(user.toString());
        
        if (user != null) {
            // Get the HttpSession object from the request. If it doesn't exist, a new one will be created.
            HttpSession session = request.getSession();

            // Store user information in the session
            session.setAttribute("user", user);

            if (user.getRole().equalsIgnoreCase("admin")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin_espace.jsp");
                dispatcher.forward(request, response);
            } 
            else if (user.getRole().equalsIgnoreCase("prof")) {
                Prof prof = auth.getProf(user);
                
                System.out.println(prof.toString());
                
                if (prof != null) {
                    session.setAttribute("prof", prof);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("prof_espace.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("prof_not_found", "Prof not found");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                }
            } 
            else {
                Etudiant etudiant = auth.getEtudiant(user);
                
                System.out.println(etudiant.toString());
                
                if (etudiant != null) {
                    session.setAttribute("etudiant", etudiant);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("etudiant_espace.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("etudiant_not_found", "Etudiant not found");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } 
        else {
            request.setAttribute("user_not_found", "User not found");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
