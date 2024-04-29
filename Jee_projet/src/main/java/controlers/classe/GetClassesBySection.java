package controlers.classe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Classe;
import models.dao.ClasseDAO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class GetClassesBySection
 */
public class GetClassesBySection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClassesBySection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	int sectionId = Integer.parseInt(request.getParameter("sectionId"));
	
	        // Assuming you have a method in your ClasseDAO to get classes based on sectionId
	        ClasseDAO classDAO = new ClasseDAO();
	        ArrayList<Classe> classes = classDAO.getClassesBySectionId(sectionId);
	        System.out.println("Received request for sectionId: " + sectionId);
	        // Generate HTML for classes dropdown
	        StringBuilder htmlResponse = new StringBuilder();
	        for (Classe classe : classes) {
	            htmlResponse.append("<option value=\"").append(classe.getId_classe()).append("\">")
	                    .append(classe.getLable()).append("</option>");
	        }
	
	        // Send the HTML response
	        response.getWriter().write(htmlResponse.toString());
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
