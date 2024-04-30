package restControllers.classe;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RestAddClasse
 */
@WebServlet("/RestAddClasse")
public class RestAddClasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestAddClasse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        // to execute: RestAddClasse?id_classe=2&label=label1&section_id=1
                
        // Retrieve parameters from the request
        String label = request.getParameter("label");
        int sectionId = Integer.parseInt(request.getParameter("section_id"));
        String idClasseParam = request.getParameter("id_classe");

        // Construct the API endpoint URL
        String apiUrl;
        if (idClasseParam != null) {
            // Execute with id_classe parameter included
            int idClasse = Integer.parseInt(idClasseParam);
            apiUrl = "http://localhost:8083/SOA_Project/Classes/add?id_classe=" + idClasse + "&label=" + label + "&section_id=" + sectionId;
        } else {
            // Execute without id_classe parameter
            apiUrl = "http://localhost:8083/SOA_Project/Classes/add?label=" + label + "&section_id=" + sectionId;
        }
        
        try {
            // Create URL object
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set request method
            conn.setRequestMethod("GET");

            // Get response code
            int responseCode = conn.getResponseCode();

            // Check if request was successful
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Redirect to the admin_classes.jsp page
                response.sendRedirect("admin_classes.jsp");
            } else {
                // Handle error response
            	PrintWriter out = response.getWriter();
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to add class!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
        	PrintWriter out = response.getWriter();
            e.printStackTrace();
            out.println("<h3>Failed to connect to the API!</h3>");
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
