package controlers.classe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.dao.ClasseDAO;
import models.dao.MatiereDAO;
import models.dao.SectionDAO;

import java.io.IOException;

/**
 * Servlet implementation class AddClasse
 */
public class AddClasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClasse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClasseDAO classeDao = new ClasseDAO();
		String label = request.getParameter("label");
		int id_section = Integer.parseInt(request.getParameter("id_section"));
		
		int nb = classeDao.addClasse(label, id_section);
		if (nb == -1) {
			request.setAttribute("add_classe_error", "classe failed to insert!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_classe_add.jsp");
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
