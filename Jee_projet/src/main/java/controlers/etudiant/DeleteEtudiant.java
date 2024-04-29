package controlers.etudiant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Etudiant;
import models.Prof;
import models.dao.EtudiantDAO;
import models.dao.ProfDAO;
import models.dao.UserDAO;

import java.io.IOException;

/**
 * Servlet implementation class DeleteEtudiant
 */
public class DeleteEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEtudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_etud = Integer.parseInt(request.getParameter("id_etud"));
		
	    EtudiantDAO etudiantDAO = new EtudiantDAO();
		
		// delete all matieres enseigner 
	    etudiantDAO.delete_matieres_inscrit(id_etud);
		
		// prof user info
		Etudiant etudiant = etudiantDAO.getEtudiant(id_etud);
		int user_id = etudiant.getUser().getId_user();
		
		// delete prof 
		int nb = etudiantDAO.deleteEtudiant(id_etud);
		if (nb == -1) {
			request.setAttribute("delete_etudiant_error", "etudiant failed to delete!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_etudiants.jsp");
			dis.forward(request, response);	 
		}
		
		UserDAO userDAO = new UserDAO();
		// delete user
		userDAO.deleteUser(user_id);
		
		response.sendRedirect("admin_etudiants.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
