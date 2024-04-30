package restControllers.matiere;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Matiere;
import models.Section;

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

/**
 * Servlet implementation class RestReadMatiere
 */
@WebServlet("/RestReadMatiere")
public class RestReadMatiere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestReadMatiere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if an ID parameter is present in the request
        String idParam = request.getParameter("id_matiere");
        if (idParam != null && !idParam.isEmpty()) {
            // If ID parameter is present, retrieve a single matiere by ID
            int id = Integer.parseInt(idParam);
            retrieveSingleMatiereById(id, request, response);
        } else {
            // If no ID parameter, retrieve all matieres
            retrieveAllMatieres(request, response);
        }
    }

    private void retrieveAllMatieres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // to execute : RestReadMatiere
    	
    	// Construct the API endpoint URL
        String apiUrl = "http://localhost:8083/SOA_Project/Matieres";

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

                // Convert JSON response to ArrayList<Matiere>
                JsonReader jsonReader = Json.createReader(new StringReader(responseBuffer.toString()));
                JsonArray jsonArray = jsonReader.readArray();
                jsonReader.close();

                ArrayList<Matiere> matieres = new ArrayList<>();
                for (JsonValue jsonValue : jsonArray) {
                    JsonObject jsonObject = jsonValue.asJsonObject();
                    // Parse JsonObject to Matiere object
                    Matiere matiere = parseMatiere(jsonObject);

                    // Add Matiere object to ArrayList
                    matieres.add(matiere);

                    // Prompt the object
                    System.out.println(matiere);
                }

                // Forward matieres to JSP or process further
                request.setAttribute("matieres", matieres);
                request.getRequestDispatcher("admin_matieres.jsp").forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve matieres!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            out.println("<h3>Failed to connect to the API!</h3>");
        }
    }

    private void retrieveSingleMatiereById(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // to execute : RestReadMatiere?id_matiere=7
    	
    	// Construct the API endpoint URL for retrieving a single matiere by ID
        String apiUrl = "http://localhost:8083/SOA_Project/Matieres/" + id;

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

                // Create a local variable "matiere" and assign the response buffer to it
                String matiereJson = responseBuffer.toString();
                
                // Prompt matiere
                System.out.println(matiereJson.toString());
                
                // Forward response to JSP or process it further
                request.setAttribute("matiere", matiereJson);
                request.getRequestDispatcher("admin_matiere_info.jsp?id_matiere=" + id).forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve matiere!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            out.println("<h3>Failed to connect to the API!</h3>");
        }
    }

    private Matiere parseMatiere(JsonObject jsonObject) {
        Matiere matiere = new Matiere();
        matiere.setId_matiere(jsonObject.getInt("id_matiere"));
        matiere.setLable(jsonObject.getString("lable"));
        matiere.setPrix((float) jsonObject.getJsonNumber("prix").doubleValue());

        // Parse section JsonObject
        JsonObject sectionObject = jsonObject.getJsonObject("section");
        Section section = new Section();
        section.setId_section(sectionObject.getInt("id_section"));
        section.setFiliere(sectionObject.getString("filiere"));
        section.setLabel(sectionObject.getString("label"));
        section.setNiveau(sectionObject.getString("niveau"));

        // Set section object in Matiere
        matiere.setSection(section);

        return matiere;
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
