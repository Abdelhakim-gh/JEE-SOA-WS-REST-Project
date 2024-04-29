package controlers.prof;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Prof;
import models.dao.ProfDAO;
import models.dao.UserDAO;

import java.io.IOException;

/**
 * Servlet implementation class DeleteProf
 */
public class DeleteProf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id_prof = Integer.parseInt(request.getParameter("id_prof"));
		
		ProfDAO profDAO = new ProfDAO();
		
		// delete all matieres enseigner 
		profDAO.delete_matieres_enseigner(id_prof);
		
		// prof user info
		Prof prof = profDAO.getProf(id_prof);
		int user_id = prof.getUser().getId_user();
		
		// delete prof 
		int nb = profDAO.deleteProf(id_prof);
		if (nb == -1) {
			request.setAttribute("delete_prof_error", "Prof failed to delete!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_profs.jsp");
			dis.forward(request, response);	 
		}
		
		UserDAO userDAO = new UserDAO();
		// delete user
		userDAO.deleteUser(user_id);
		
		response.sendRedirect("admin_profs.jsp");	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
