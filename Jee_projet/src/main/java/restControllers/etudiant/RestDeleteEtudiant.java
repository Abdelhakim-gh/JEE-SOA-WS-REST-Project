package restControllers.etudiant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RestDeleteEtudiant
 */
@WebServlet("/RestDeleteEtudiant")
public class RestDeleteEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestDeleteEtudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_etud = Integer.parseInt(request.getParameter("id_etud"));
        removeEtudiant(id_etud, request, response);
    }

    private void removeEtudiant(int etudiantId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // to execute : RestDeleteEtudiant?id_etud=8
        
        // API endpoint URL for removing a student by ID
        String apiUrl = "http://localhost:8083/SOA_Project/Etudiants/remove/" + etudiantId;

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
                // Read response
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer responseBuffer = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
                in.close();

                // Forward response or process it further
                request.setAttribute("message", responseBuffer.toString());
                request.getRequestDispatcher("admin_etudiants.jsp").forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to remove student!</h3>");
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
