package restControllers.prof;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Servlet implementation class RestAddProf
 */
@WebServlet("/RestAddProf")
public class RestAddProf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestAddProf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve query parameters from the request
        String id_userParam = request.getParameter("id_user");
        String id_profParam = request.getParameter("id_prof");

        int id_user = -1; // Default value for id_user
        if (id_userParam != null && !id_userParam.isEmpty()) {
            id_user = Integer.parseInt(id_userParam);
        }

        int id_prof = -1; // Default value for id_prof
        if (id_profParam != null && !id_profParam.isEmpty()) {
            id_prof = Integer.parseInt(id_profParam);
        }

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String password = request.getParameter("password");
        
        // Call the method to add the professor
        addProf(id_user, id_prof, nom, prenom, email, tel, password, request, response);
    }

    private void addProf(int id_user, int id_prof, String nom, String prenom, String email, String tel, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // to execute : RestAddProf?id_user=5&id_prof=5&nom=John&prenom=Doe&email=john.doe.prof@example.com&tel=123456789&password=password123
        
        // API endpoint URL for adding a professor
        String apiUrl = "http://localhost:8083/SOA_Project/Profs/add?" +
                "nom=" + nom +
                "&prenom=" + prenom +
                "&email=" + email +
                "&tel=" + tel +
                "&password=" + password;

        // Append id_user if provided
        if (id_user != -1) {
            apiUrl += "&id_user=" + id_user;
        }

        // Append id_prof if provided
        if (id_prof != -1) {
            apiUrl += "&id_prof=" + id_prof;
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
                request.getRequestDispatcher("admin_profs.jsp").forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to add professor!</h3>");
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
