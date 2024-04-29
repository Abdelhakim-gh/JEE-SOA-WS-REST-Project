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
 * Servlet implementation class DeleteSection
 */
public class DeleteSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SectionDAO sectionDao = new SectionDAO();
		int id_section = Integer.parseInt(request.getParameter("id_section"));
		int nb_matieres = Integer.parseInt(request.getParameter("nb_matieres"));
		int nb_etuds = Integer.parseInt(request.getParameter("nb_etuds"));
		int nb_classes = Integer.parseInt(request.getParameter("nb_classes"));
		
		if (nb_matieres > 0 || nb_etuds > 0 || nb_classes > 0) {
			request.setAttribute("delete_section_error", "Bulk Delete Section Not Allowed!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_sections.jsp");
			dis.forward(request, response);
		}
		else {
			int nb = sectionDao.deleteSection(id_section);
			if (nb == -1) {
				request.setAttribute("delete_section_error", "section failed to delete!");
				RequestDispatcher dis = request.getRequestDispatcher("admin_sections.jsp");
				dis.forward(request, response);
			}
			else {
				response.sendRedirect("admin_sections.jsp");
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
