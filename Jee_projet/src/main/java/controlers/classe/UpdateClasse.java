package controlers.classe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.dao.ClasseDAO;

import java.io.IOException;

/**
 * Servlet implementation class UpdateClasse
 */
public class UpdateClasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClasse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClasseDAO classeDao = new ClasseDAO();
		int id_classe = Integer.parseInt(request.getParameter("id_classe"));
		String label = request.getParameter("label");

		System.out.println("id_classe: "+id_classe);
		System.out.println("label: "+label);
		
		int nb = classeDao.updateClasse(id_classe, label);
		if (nb == -1) {
			request.setAttribute("Update_classe_error", "classe failed to Update!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_classe_edit.jsp");
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
