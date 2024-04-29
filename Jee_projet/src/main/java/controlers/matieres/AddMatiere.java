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
 * Servlet implementation class AddMatiere
 */
public class AddMatiere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMatiere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String label = request.getParameter("label");
		int id_section = Integer.parseInt(request.getParameter("id_section"));
		float prix = Float.parseFloat(request.getParameter("prix"));
		
		MatiereDAO matiereDAO = new MatiereDAO();
		int nb = matiereDAO.addMatiere(label, id_section, prix);
		if (nb == -1) {
			request.setAttribute("add_matiere_error", "matiere failed to insert!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_matiere_add.jsp");
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
