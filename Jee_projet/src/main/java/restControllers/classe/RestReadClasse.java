package restControllers.classe;

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
import javax.json.Json;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Classe;
import models.Section;

/**
 * Servlet implementation class RestReadClasse
 */
@WebServlet("/RestReadClasse")
public class RestReadClasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestReadClasse() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check if an ID parameter is present in the request
        String idParam = request.getParameter("id_classe");
        if (idParam != null && !idParam.isEmpty()) {
            // If ID parameter is present, retrieve a single class by ID
            int classId = Integer.parseInt(idParam);
            retrieveSingleClassById(classId, request, response);
        } else {
            // If no ID parameter, retrieve all classes
            retrieveAllClasses(request, response);
        }
    }

    private void retrieveAllClasses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
    	// to execute : RestReadClasse
    	
    	// API endpoint URL for retrieving all classes
    	String apiUrl = "http://localhost:8083/SOA_Project/Classes";

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

                // Convert JSON response to ArrayList<Classe>
                JsonReader jsonReader = Json.createReader(new StringReader(responseBuffer.toString()));
                JsonArray jsonArray = jsonReader.readArray();
                jsonReader.close();

                ArrayList<Classe> classes = new ArrayList<>();
                for (JsonValue jsonValue : jsonArray) {
                    JsonObject jsonObject = jsonValue.asJsonObject();
                    // Parse JsonObject to Classe object
                    Classe classe = new Classe();
                    classe.setId_classe(jsonObject.getInt("id_classe"));
                    classe.setLable(jsonObject.getString("lable"));

                    // Parse section JsonObject
                    JsonObject sectionObject = jsonObject.getJsonObject("section");
                    Section section = new Section();
                    section.setId_section(sectionObject.getInt("id_section"));
                    section.setFiliere(sectionObject.getString("filiere"));
                    section.setLabel(sectionObject.getString("label"));
                    section.setNiveau(sectionObject.getString("niveau"));

                    // Set section object in Classe
                    classe.setSection(section);

                    // Add Classe object to ArrayList
                    classes.add(classe);
                    
                   // Prompt the object 
                   System.out.println(classe);
                }

                // Forward classes to JSP
                request.setAttribute("classes", classes);
                request.getRequestDispatcher("admin_classes.jsp").forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve classes!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            out.println("<h3>Failed to connect to the API!</h3>");
        }
    }

    private void retrieveSingleClassById(int classId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
    	// to execute : RestReadClasse?id_classe=2
    	
    	// API endpoint URL for retrieving a single class by ID
        String apiUrl = "http://localhost:8083/SOA_Project/Classes/" + classId;

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

                // Create a local variable "classe" and assign the response buffer to it
                String classe = responseBuffer.toString();
                
                // Prompt the object
//                System.out.println(classe.toString());
                
                // Forward response to JSP or process it further
                request.setAttribute("classe", classe);
                request.getRequestDispatcher("admin_classe_info.jsp?id_classe="+classId).forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve classe!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            out.println("<h3>Failed to connect to the API!</h3>");
        }
    }

    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        // API endpoint URL
//        String apiUrl = "http://localhost:8083/SOA_Project/Classes";
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
//             // Convert JSON response to ArrayList<Classe>
//                JsonReader jsonReader = Json.createReader(new StringReader(responseBuffer.toString()));
//                JsonArray jsonArray = jsonReader.readArray();
//                jsonReader.close();
//
//                ArrayList<Classe> classes = new ArrayList<>();
//                for (JsonValue jsonValue : jsonArray) {
//                    JsonObject jsonObject = jsonValue.asJsonObject();
//                    // Parse JsonObject to Classe object
//                    Classe classe = new Classe();
//                    classe.setId_classe(jsonObject.getInt("id_classe"));
//                    classe.setLable(jsonObject.getString("lable"));
//
//                    // Parse section JsonObject
//                    JsonObject sectionObject = jsonObject.getJsonObject("section");
//                    Section section = new Section();
//                    section.setId_section(sectionObject.getInt("id_section"));
//                    section.setFiliere(sectionObject.getString("filiere"));
//                    section.setLabel(sectionObject.getString("label"));
//                    section.setNiveau(sectionObject.getString("niveau"));
//
//                    // Set section object in Classe
//                    classe.setSection(section);
//                    
//                    // Add Classe object to ArrayList
//                    classes.add(classe);
//                    
//                    // Prompt classe 
//                    // System.out.println(classe);
//                }
//                
//                
//                // Forward classes to JSP
//                request.setAttribute("classes", classes);
//                request.getRequestDispatcher("admin_classes.jsp").forward(request, response);
//            } else {
//                // Handle error response
//                out.println("<h1>Error response code: " + responseCode + "</h1>");
//                out.println("<h3>Failed to retrieve classes!</h3>");
//            }
//        } catch (IOException e) {
//            // Handle exception
//            e.printStackTrace();
//            out.println("<h3>Failed to connect to the API!</h3>");
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
