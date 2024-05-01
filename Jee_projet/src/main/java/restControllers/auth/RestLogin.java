package restControllers.auth;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Classe;
import models.Etudiant;
import models.Matiere;
import models.Prof;
import models.Section;
import models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Servlet implementation class RestLogin
 */
@WebServlet("/RestLogin")
public class RestLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    /* To execute :
  
   	* Prof: 	RestLogin?email=pariwo@mailinator.com&password=Pa$$w0rd!
   	* Admin: 	RestLogin?email=abdo@gmail.com&password=abdo
   	* Etudiant: RestLogin?email=tiriximi@mailinator.com&password=Pa$$w0rd!
	
	*/
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Construct API URL
        String apiUrl = "http://localhost:8083/SOA_Project/Auth/Login?email=" + email + "&password=" + password;

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

                // Parse JSON response
                String jsonResponse = responseBuffer.toString();
                JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
                JsonObject jsonObject = jsonReader.readObject();
                jsonReader.close();

                // Retrieve the role attribute
                String role = null;
                if (jsonObject.containsKey("role")) {
                    // Role directly available in the JSON object
                    role = jsonObject.getString("role");
                } else if (jsonObject.containsKey("user")) {
                    // Role is nested inside the "user" object
                    JsonObject userObject = jsonObject.getJsonObject("user");
                    role = userObject.getString("role");
                }

                if (role != null) {
                    switch (role.toLowerCase()) {
                        case "admin":
                            // For admin, directly create a User object
                            User user = convertJsonToUser(jsonObject);
                            request.getSession().setAttribute("user", user);
                            RequestDispatcher adminDispatcher = request.getRequestDispatcher("admin_espace.jsp");
                            adminDispatcher.forward(request, response);
                            break;
                        case "prof":
                            Prof prof = convertJsonToProf(jsonObject);
                            if (prof != null) {
                                request.getSession().setAttribute("prof", prof);
                                RequestDispatcher profDispatcher = request.getRequestDispatcher("prof_espace.jsp");
                                profDispatcher.forward(request, response);
                            } else {
                                request.setAttribute("prof_not_found", "Prof not found");
                                RequestDispatcher profNotFoundDispatcher = request.getRequestDispatcher("login.jsp");
                                profNotFoundDispatcher.forward(request, response);
                            }
                            break;
                        case "etudiant":
                            Etudiant etudiant = convertJsonToEtudiant(jsonObject);
                            if (etudiant != null) {
                                request.getSession().setAttribute("etudiant", etudiant);
                                RequestDispatcher etudiantDispatcher = request.getRequestDispatcher("etudiant_espace.jsp");
                                etudiantDispatcher.forward(request, response);
                            } else {
                                request.setAttribute("etudiant_not_found", "Etudiant not found");
                                RequestDispatcher etudiantNotFoundDispatcher = request.getRequestDispatcher("login.jsp");
                                etudiantNotFoundDispatcher.forward(request, response);
                            }
                            break;
                        default:
                            // Handle unknown role
                            break;
                    }
                } else {
                    // Handle missing role
                }
            } else {
                // Handle error response
                response.getWriter().println("<h1>Error response code: " + responseCode + "</h1>");
                response.getWriter().println("<h3>Failed to retrieve classes!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            response.getWriter().println("<h3> Failed to connect to the API! </h3>");
        }
    }

    

 // Convert JSON object to Prof
    private Prof convertJsonToProf(JsonObject jsonObject) {
        Prof prof = new Prof();
        prof.setId_prof(jsonObject.getInt("id_prof"));

        // Set user
        User user = convertJsonToUser(jsonObject.getJsonObject("user"));
        prof.setUser(user);

        // Set matieres
        JsonArray matieresArray = jsonObject.getJsonArray("matieres");
        ArrayList<Matiere> matieres = new ArrayList<>();
        for (JsonValue matiereValue : matieresArray) {
            JsonObject matiereObject = matiereValue.asJsonObject();
            Matiere matiere = new Matiere();
            matiere.setId_matiere(matiereObject.getInt("id_matiere"));
            matiere.setLable(matiereObject.getString("lable"));
            matiere.setPrix((float) matiereObject.getJsonNumber("prix").doubleValue());

            // Set section
            JsonObject sectionObject = matiereObject.getJsonObject("section");
            Section section = new Section();
            section.setFiliere(sectionObject.getString("filiere"));
            section.setId_section(sectionObject.getInt("id_section"));
            section.setLabel(sectionObject.getString("label"));
            section.setNiveau(sectionObject.getString("niveau"));

            matiere.setSection(section);

            matieres.add(matiere);
        }
        prof.setMatieres(matieres);

        // Set other properties
        prof.setSalaire((float) jsonObject.getJsonNumber("salaire").doubleValue());
        prof.setPayement(jsonObject.getInt("payement"));

        return prof;
    }

    // Convert JSON object to Etudiant
    private Etudiant convertJsonToEtudiant(JsonObject jsonObject) {
        Etudiant etudiant = new Etudiant();
        etudiant.setId_etud(jsonObject.getInt("id_etud"));
        etudiant.setCne(jsonObject.getString("cne"));
        etudiant.setPrix((float) jsonObject.getJsonNumber("prix").doubleValue());
        etudiant.setPayement(jsonObject.getInt("payement"));

        // Set user
        User user = convertJsonToUser(jsonObject.getJsonObject("user"));
        etudiant.setUser(user);

        // Set classe
        JsonObject classeObject = jsonObject.getJsonObject("classe");
        Classe classe = new Classe();
        classe.setId_classe(classeObject.getInt("id_classe"));
        classe.setLable(classeObject.getString("lable"));
        etudiant.setClasse(classe);

        // Set section
        JsonObject sectionObject = jsonObject.getJsonObject("section");
        Section section = new Section();
        section.setFiliere(sectionObject.getString("filiere"));
        section.setId_section(sectionObject.getInt("id_section"));
        section.setLabel(sectionObject.getString("label"));
        section.setNiveau(sectionObject.getString("niveau"));
        etudiant.setSection(section);

        return etudiant;
    }

    // Convert JSON object to User
    private User convertJsonToUser(JsonObject jsonObject) {
        User user = new User();
        user.setEmail(jsonObject.getString("email"));
        user.setEtud_id(jsonObject.getInt("etud_id"));
        user.setId_user(jsonObject.getInt("id_user"));
        user.setNom(jsonObject.getString("nom"));
        user.setPassword(jsonObject.getString("password"));
        user.setPrenom(jsonObject.getString("prenom"));
        user.setProf_id(jsonObject.getInt("prof_id"));
        user.setRole(jsonObject.getString("role"));
        user.setTel(jsonObject.getString("tel"));

        return user;
    }


    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//      
//        /* To execute :
//        
//	     	* Prof: 	RestLogin?email=pariwo@mailinator.com&password=Pa$$w0rd!
//	     	* Admin: 	RestLogin?email=abdo@gmail.com&password=abdo
//	     	* Etudiant: RestLogin?email=tiriximi@mailinator.com&password=Pa$$w0rd!
//     	
//     	*/
//    	
//    	String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        
//        // URL of the REST API endpoint
//        String apiUrl = "http://localhost:8083/SOA_Project/Auth/Login?email=" + email + "&password=" + password;
//
//        try {
//            // Create URL object
//            URL url = new URL(apiUrl);
//
//            // Open connection
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            // Set request method
//            conn.setRequestMethod("GET");
//
//            // Get response code
//            int responseCode = conn.getResponseCode();
//
//            // Check if request was successful
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                // Read response
//                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String inputLine;
//                StringBuffer responseBuffer = new StringBuffer();
//                while ((inputLine = in.readLine()) != null) {
//                    responseBuffer.append(inputLine);
//                }
//                in.close();
//
//                // Forward response or process it further
//                String jsonResponse = responseBuffer.toString();
//                // You can parse jsonResponse here and handle accordingly
//                // For now, let's forward it to a JSP page
//                request.setAttribute("jsonResponse", jsonResponse);
//                request.getRequestDispatcher("response.jsp").forward(request, response);
//            } else {
//                // Handle error response
//                // You can forward to an error page or do something else
//                response.getWriter().println("<h1>Error response code: " + responseCode + "</h1>");
//                response.getWriter().println("<h3>Failed to retrieve classes!</h3>");
//
//            }
//        } catch (IOException e) {
//            // Handle exception
//            e.printStackTrace();
//            response.getWriter().println("<h3> Failed to connect to the API! </h3>");
//        }
//    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
