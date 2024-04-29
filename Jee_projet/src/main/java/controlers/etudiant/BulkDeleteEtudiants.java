package controlers.etudiant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Etudiant;
import models.dao.ClasseDAO;
import models.dao.EtudiantDAO;
import models.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class BulkDeleteEtudiants
 */
public class BulkDeleteEtudiants extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BulkDeleteEtudiants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// delete all etudiant of classe
		int id_classe = Integer.parseInt(request.getParameter("id_classe"));
		ClasseDAO classeDao = new ClasseDAO();
		EtudiantDAO etudiantDAO = new EtudiantDAO();
		ArrayList<Etudiant> etudiants = etudiantDAO.getEtudiantByClass(id_classe);
		
		for (Etudiant e : etudiants) {
			// delete all matieres enseigner 
		    etudiantDAO.delete_matieres_inscrit(e.getId_etud());
			
		    // get etudiant info
			int user_id = e.getUser().getId_user();
			
			// delete prof 
			int nb = etudiantDAO.deleteEtudiant(e.getId_etud());
			if (nb == -1) {
				request.setAttribute("delete_etudiant_error", "etudiant failed to delete!");
				RequestDispatcher dis = request.getRequestDispatcher("admin_classes.jsp");
				dis.forward(request, response);	 
			}
			else {
				UserDAO userDAO = new UserDAO();
				// delete user
				userDAO.deleteUser(user_id);
			}
		}
		
		int nb = classeDao.deleteClasse(id_classe);
		if (nb == -1) {
			request.setAttribute("delete_classe_error", "classe failed to delete!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_classes.jsp");
			dis.forward(request, response);
		}
		else {
			response.sendRedirect("admin_classes.jsp");
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
