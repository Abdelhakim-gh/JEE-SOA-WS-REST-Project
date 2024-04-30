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
 * Servlet implementation class RestDeleteSection
 */
@WebServlet("/RestDeleteSection")
public class RestDeleteSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestDeleteSection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // to execute : RestDeleteSection?id_section=5
        
        // Retrieve parameters for removing a section
        int idSection = Integer.parseInt(request.getParameter("id_section"));
        int nbMatieres = 0; // Default value
        int nbEtuds = 0; // Default value
        int nbClasses = 0; // Default value

        // Check if the parameters are present in the request and not null
        String nbMatieresParam = request.getParameter("nb_matieres");
        String nbEtudsParam = request.getParameter("nb_etuds");
        String nbClassesParam = request.getParameter("nb_classes");

        // Parse parameters if they are not null
        if (nbMatieresParam != null) {
            nbMatieres = Integer.parseInt(nbMatieresParam);
        }
        if (nbEtudsParam != null) {
            nbEtuds = Integer.parseInt(nbEtudsParam);
        }
        if (nbClassesParam != null) {
            nbClasses = Integer.parseInt(nbClassesParam);
        }

        // Construct the API endpoint URL
        String apiUrl = "http://localhost:8083/SOA_Project/Sections/remove/" + idSection + "?nb_matieres=" + nbMatieres + "&nb_etuds=" + nbEtuds + "&nb_classes=" + nbClasses;

        try {
            // Create URL object
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set request method
            conn.setRequestMethod("DELETE");

            // Get response code
            int responseCode = conn.getResponseCode();

            // Check if request was successful
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Redirect to the admin_sections.jsp page
                response.sendRedirect("admin_sections.jsp");
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to remove section!</h3>");
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
