package restControllers.section;

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
import models.Section;

/**
 * Servlet implementation class RestReadSection
 */
@WebServlet("/RestReadSection")
public class RestReadSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestReadSection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if an ID parameter is present in the request
        String idParam = request.getParameter("id_section");
        if (idParam != null && !idParam.isEmpty()) {
            // If ID parameter is present, retrieve a single section by ID
            int sectionId = Integer.parseInt(idParam);
            retrieveSingleSectionById(sectionId, request, response);
        } else {
            // If no ID parameter, retrieve all sections
            retrieveAllSections(request, response);
        }
    }

    private ArrayList<Section> parseSections(String json) {
        ArrayList<Section> sections = new ArrayList<>();
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonArray jsonArray = jsonReader.readArray();
        jsonReader.close();

        for (JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            Section section = new Section();
            section.setId_section(jsonObject.getInt("id_section"));
            section.setFiliere(jsonObject.getString("filiere"));
            section.setLabel(jsonObject.getString("label"));
            section.setNiveau(jsonObject.getString("niveau"));
            sections.add(section);
        }
        return sections;
    }

    private void retrieveAllSections(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // API endpoint URL for retrieving all sections
        String apiUrl = "http://localhost:8083/SOA_Project/Sections/";

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

                // Parse JSON response to ArrayList<Section>
                ArrayList<Section> sections = parseSections(responseBuffer.toString());

                // Forward sections to JSP
                request.setAttribute("sections", sections);
                request.getRequestDispatcher("admin_sections.jsp").forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve sections!</h3>");
            }
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            out.println("<h3>Failed to connect to the API!</h3>");
        }
    }

    private void retrieveSingleSectionById(int sectionId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // API endpoint URL for retrieving a single section by ID
        String apiUrl = "http://localhost:8083/SOA_Project/Sections/" + sectionId;

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
                request.setAttribute("section", responseBuffer.toString());
                request.getRequestDispatcher("admin_section_info.jsp?id_section=" + sectionId).forward(request, response);
            } else {
                // Handle error response
                out.println("<h1>Error response code: " + responseCode + "</h1>");
                out.println("<h3>Failed to retrieve section!</h3>");
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
