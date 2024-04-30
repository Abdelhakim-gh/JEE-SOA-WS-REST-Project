package restControllers.matiere;

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
 * Servlet implementation class RestUpdateMatiere
 */
@WebServlet("/RestUpdateMatiere")
public class RestUpdateMatiere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestUpdateMatiere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // to execute : RestUpdateMatiere?id_matiere=7&label=lable2&prix=100
        
        // Retrieve parameters from the request
        int idMatiere = Integer.parseInt(request.getParameter("id_matiere"));
        String label = request.getParameter("label");
        float prix = Float.parseFloat(request.getParameter("prix"));

        // Construct the API endpoint URL
        String apiUrl = "http://localhost:8083/SOA_Project/Matieres/edit?id_matiere=" + idMatiere 
                        + "&label=" + label + "&prix=" + prix;
        
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
                // Redirect to the admin_matieres.jsp page
                response.sendRedirect("admin_matieres.jsp");
            } else {
                // Handle error response
                PrintWriter out = response.getWriter();
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to edit matiere!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            PrintWriter out = response.getWriter();
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
