package restControllers.prof;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Matiere;
import models.Prof;
import models.Section;
import models.User;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.bind.*;
/**
 * Servlet implementation class RestReadProf
 */
@WebServlet("/RestReadProf")
public class RestReadProf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestReadProf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // to execute : RestReadProf?id_prof=4
    // to execute : RestReadProf
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id_prof");
        if (idParam != null && !idParam.isEmpty()) {
            int profId = Integer.parseInt(idParam);
            retrieveSingleProfById(profId, request, response);
        } else {
            retrieveAllProfs(request, response);
        }
    }

    private void retrieveAllProfs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String apiUrl = "http://localhost:8083/SOA_Project/Profs/";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder responseBuffer = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
                in.close();

                ArrayList<Prof> profs = parseProfs(responseBuffer.toString());

                // Prompt list
                System.out.println(profs.toString());
                
                request.setAttribute("profs", profs);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin_profs.jsp");
                dispatcher.forward(request, response);
            } else {
                out.println("{ \"error\": \"Failed to retrieve professors!\" }");
            }
        } catch (IOException e) {
            e.printStackTrace();
            out.println("{ \"error\": \"Failed to connect to the API!\" }");
        }
    }

    private void retrieveSingleProfById(int profId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String apiUrl = "http://localhost:8083/SOA_Project/Profs/" + profId;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder responseBuffer = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
                in.close();

                request.setAttribute("prof", responseBuffer.toString());
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin_prof_info.jsp?id_prof=" + profId);
                dispatcher.forward(request, response);
            } else {
                out.println("{ \"error\": \"Failed to retrieve professor!\" }");
            }
        } catch (IOException e) {
            e.printStackTrace();
            out.println("{ \"error\": \"Failed to connect to the API!\" }");
        }
    }

    private ArrayList<Prof> parseProfs(String json) {
        ArrayList<Prof> profs = new ArrayList<>();
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonArray jsonArray = jsonReader.readArray();
        jsonReader.close();

        for (JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            Prof prof = new Prof();

            // Fill Prof properties
            prof.setId_prof(jsonObject.getInt("id_prof"));
            prof.setPayement(jsonObject.getInt("payement"));
            prof.setSalaire((float) jsonObject.getJsonNumber("salaire").doubleValue());
            
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
            prof.setUser(user);

            // Parse and set Matieres list
            JsonArray matieresArray = jsonObject.getJsonArray("matieres");
            ArrayList<Matiere> matieres = new ArrayList<>();
            for (JsonValue matiereValue : matieresArray) {
                JsonObject matiereObject = matiereValue.asJsonObject();
                Matiere matiere = new Matiere();
                matiere.setId_matiere(matiereObject.getInt("id_matiere"));
                matiere.setLable(matiereObject.getString("lable"));
                matiere.setPrix((float) matiereObject.getJsonNumber("prix").doubleValue());

                // Parse and set Section object for Matiere
                JsonObject sectionObject = matiereObject.getJsonObject("section");
                Section section = new Section();
                section.setFiliere(sectionObject.getString("filiere"));
                section.setId_section(sectionObject.getInt("id_section"));
                section.setLabel(sectionObject.getString("label"));
                section.setNiveau(sectionObject.getString("niveau"));
                matiere.setSection(section);

                // Add Matiere to the list
                matieres.add(matiere);
            }
            prof.setMatieres(matieres);

            // Add the filled Prof object to the list
            profs.add(prof);
        }
        return profs;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
