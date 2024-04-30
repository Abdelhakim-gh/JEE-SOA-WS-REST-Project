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
 * Servlet implementation class RestAddEtudiant
 */
@WebServlet("/RestAddEtudiant")
public class RestAddEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestAddEtudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve query parameters from the request
        String id_userParam = request.getParameter("id_user");
        String id_etudParam = request.getParameter("id_etud");

        int id_user = -1; // Default value for id_user
        if (id_userParam != null && !id_userParam.isEmpty()) {
            id_user = Integer.parseInt(id_userParam);
        }

        int id_etud = -1; // Default value for id_etud
        if (id_etudParam != null && !id_etudParam.isEmpty()) {
            id_etud = Integer.parseInt(id_etudParam);
        }

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String password = request.getParameter("password");
        String cne = request.getParameter("cne");
        int section_id = Integer.parseInt(request.getParameter("section_id"));
        
        // Call the method to add the student
        addUser(id_user, nom, prenom, email, tel, password, id_etud, cne, section_id, request, response);
    }

    private void addUser(int id_user, String nom, String prenom, String email, String tel, String password, int id_etud, String cne, int section_id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // to execute : RestAddEtudiant?id_user=8&nom=John&prenom=Doe&email=john.doe.etudiant@example.com&tel=123456789&password=password123&role=etudiant&id_etud=8&cne=CNE1234&section_id=1
        
        // API endpoint URL for adding a student
        String apiUrl = "http://localhost:8083/SOA_Project/Etudiants/add?" +
                "nom=" + nom +
                "&prenom=" + prenom +
                "&email=" + email +
                "&tel=" + tel +
                "&password=" + password +
                "&cne=" + cne +
                "&section_id=" + section_id;

        // Append id_user if provided
        if (id_user != -1) {
            apiUrl += "&id_user=" + id_user;
        }

        // Append id_etud if provided
        if (id_etud != -1) {
            apiUrl += "&id_etud=" + id_etud;
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
                request.getRequestDispatcher("admin_etudiants.jsp").forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to add student!</h3>");
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
