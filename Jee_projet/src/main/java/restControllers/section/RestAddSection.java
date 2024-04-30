package restControllers.section;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Servlet implementation class RestAddSection
 */
@WebServlet("/RestAddSection")
public class RestAddSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestAddSection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // to execute : RestAddSection?id_section=5&niveau=n1&filiere=f1
        
        // Retrieve parameters for adding a section
        String niveau = request.getParameter("niveau");
        String filiere = request.getParameter("filiere");
        String idSectionParam = request.getParameter("id_section");

        // Construct the API endpoint URL
        String apiUrl;
        if (idSectionParam != null) {
            // Execute with id_section parameter included
            int idSection = Integer.parseInt(idSectionParam);
            apiUrl = "http://localhost:8083/SOA_Project/Sections/add?id_section=" + idSection + "&niveau=" + niveau + "&filiere=" + filiere;
        } else {
            // Execute without id_section parameter
            apiUrl = "http://localhost:8083/SOA_Project/Sections/add?niveau=" + niveau + "&filiere=" + filiere;
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
                // Redirect to the admin_sections.jsp page
                response.sendRedirect("admin_sections.jsp");
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to add section!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
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
