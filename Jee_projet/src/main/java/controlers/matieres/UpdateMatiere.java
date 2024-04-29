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
 * Servlet implementation class UpdateMatiere
 */
public class UpdateMatiere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMatiere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id_matiere = Integer.parseInt(request.getParameter("id_matiere"));
		String label = request.getParameter("label");
		float prix = Float.parseFloat(request.getParameter("prix"));
			
		MatiereDAO matiereDAO = new MatiereDAO();
		int nb = matiereDAO.updateMatieres(id_matiere, label, prix);
		if (nb == -1) {
			request.setAttribute("update_matiere_error", "matiere failed to update!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_matiere_edit.jsp");
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
