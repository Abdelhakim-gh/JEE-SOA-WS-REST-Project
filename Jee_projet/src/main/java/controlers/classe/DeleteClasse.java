package controlers.classe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Etudiant;
import models.dao.ClasseDAO;
import models.dao.EtudiantDAO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class DeleteClasse
 */
public class DeleteClasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClasse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClasseDAO classeDao = new ClasseDAO();
		int id_classe = Integer.parseInt(request.getParameter("id_classe"));
		int nb_etud = Integer.parseInt(request.getParameter("nb_etud"));
		
		if (nb_etud > 0) {
			
//			request.setAttribute("delete_classe_error", "Bulk Delete of Etudiants Not Allowed!");
//			RequestDispatcher dis = request.getRequestDispatcher("admin_classes.jsp");
//			dis.forward(request, response);
			
			response.sendRedirect("BulkDeleteEtudiants?id_classe="+id_classe+"");
			
		}
		else {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
