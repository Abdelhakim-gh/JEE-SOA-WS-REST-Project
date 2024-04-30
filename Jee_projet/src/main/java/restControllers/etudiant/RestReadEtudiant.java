package restControllers.etudiant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Classe;
import models.Etudiant;
import models.Section;
import models.User;

/**
 * Servlet implementation class RestReadEtudiant
 */
@WebServlet("/RestReadEtudiant")
public class RestReadEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestReadEtudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Check if an ID parameter is present in the request
        String idParam = request.getParameter("id_etud");
        if (idParam != null && !idParam.isEmpty()) {
            // If ID parameter is present, retrieve a single student by ID
            int studentId = Integer.parseInt(idParam);
            retrieveSingleStudentById(studentId, request, response);
        } else {
            // If no ID parameter, retrieve all students
            retrieveAllStudents(request, response);
        }
    }

    private ArrayList<Etudiant> parseStudents(String json) {
        ArrayList<Etudiant> students = new ArrayList<>();
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonArray jsonArray = jsonReader.readArray();
        jsonReader.close();

        for (JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            Etudiant student = new Etudiant();

            // Fill Etudiant properties
            student.setId_etud(jsonObject.getInt("id_etud"));
            student.setCne(jsonObject.getString("cne"));
            student.setPayement(jsonObject.getInt("payement"));
            student.setPrix((float) jsonObject.getJsonNumber("prix").doubleValue());

            // Parse and set Classe object
            JsonObject classeObject = jsonObject.getJsonObject("classe");
            Classe classe = new Classe();
            classe.setId_classe(classeObject.getInt("id_classe"));
            // Parse and set Section object for Classe
            JsonObject sectionObject = classeObject.getJsonObject("section");
            Section section = new Section();
            section.setFiliere(sectionObject.getString("filiere"));
            section.setId_section(sectionObject.getInt("id_section"));
            section.setLabel(sectionObject.getString("label"));
            section.setNiveau(sectionObject.getString("niveau"));
            classe.setSection(section);
            student.setClasse(classe);

            // Parse and set User object
            JsonObject userObject = jsonObject.getJsonObject("user");
            User user = new User();
            user.setId_user(userObject.getInt("id_user"));
            user.setEmail(userObject.getString("email"));
            user.setNom(userObject.getString("nom"));
            user.setPassword(userObject.getString("password"));
            user.setPrenom(userObject.getString("prenom"));
            user.setProf_id(userObject.getInt("prof_id"));
            user.setRole(userObject.getString("role"));
            user.setTel(userObject.getString("tel"));
            student.setUser(user);

            // Add the filled Etudiant object to the list
            students.add(student);
        }
        return students;
    }
    private void retrieveAllStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // to execute: RestReadEtudiant
        
        // API endpoint URL for retrieving all students
        String apiUrl = "http://localhost:8083/SOA_Project/Etudiants/";

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

                // Parse JSON response to ArrayList<Etudiant>
                ArrayList<Etudiant> etudiants = parseStudents(responseBuffer.toString());

                // Prompt list
                System.out.println(etudiants.toString());
                
                // Forward students to JSP
                request.setAttribute("etudiants", etudiants);
                request.getRequestDispatcher("admin_etudiants.jsp").forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve students!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            out.println("<h3>Failed to connect to the API!</h3>");
        }
    }

    private void retrieveSingleStudentById(int studentId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // RestReadEtudiant?id_etud=27
        
        // API endpoint URL for retrieving a single student by ID
        String apiUrl = "http://localhost:8083/SOA_Project/Etudiants/" + studentId;

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

                // Forward response to JSP or process it further
                request.setAttribute("etudiant", responseBuffer.toString());
                request.getRequestDispatcher("admin_etudiant_info.jsp?id_etud=" + studentId).forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve student!</h3>");
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
