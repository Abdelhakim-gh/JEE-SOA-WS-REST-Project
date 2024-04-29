package controlers.section;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.dao.SectionDAO;

import java.io.IOException;

/**
 * Servlet implementation class AddSection
 */
public class AddSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SectionDAO sectionDao = new SectionDAO();
		String niveau = request.getParameter("niveau");
		String filiere = request.getParameter("filiere");
		
		int nb = sectionDao.addSection(niveau, filiere);
		if (nb == -1) {
			request.setAttribute("add_section_error", "section failed to insert!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_section_add.jsp");
			dis.forward(request, response);
		}
		else {
			response.sendRedirect("admin_sections.jsp");
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
