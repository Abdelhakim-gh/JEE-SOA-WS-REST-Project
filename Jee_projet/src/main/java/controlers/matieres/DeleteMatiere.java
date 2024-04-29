package controlers.matieres;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.dao.MatiereDAO;

import java.io.IOException;

/**
 * Servlet implementation class DeleteMatiere
 */
public class DeleteMatiere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMatiere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id_matiere = Integer.parseInt(request.getParameter("id_matiere"));
		
		MatiereDAO matiereDAO = new MatiereDAO();
		
		matiereDAO.delete_enseigneres_matiere(id_matiere);
		matiereDAO.delete_inscriptions_matiere(id_matiere);
		
		int nb = matiereDAO.deleteMatieres(id_matiere);
		if (nb == -1) {
			request.setAttribute("delete_matiere_error", "matiere failed to delete!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_matieres.jsp");
			dis.forward(request, response);
		}
		else {
			response.sendRedirect("admin_matieres.jsp");
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
