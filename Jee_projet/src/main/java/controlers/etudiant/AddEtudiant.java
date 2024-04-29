package controlers.etudiant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Etudiant;
import models.Section;
import models.User;
import models.dao.EtudiantDAO;
import models.dao.ProfDAO;
import models.dao.SectionDAO;
import models.dao.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class AddEtudiant
 */
public class AddEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEtudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String cne = request.getParameter("cne");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		int section_id = Integer.parseInt(request.getParameter("section_id"));
		
	    System.out.println("\nNom:"+nom);
	    System.out.println("Prenom:"+prenom);
	    System.out.println("CNE:"+cne);
	    System.out.println("email:"+email);
	    System.out.println("Password:"+password);
	    System.out.println("Tel:"+tel);
	    System.out.println("Section_id:"+section_id);
	    
	    User user = new User(nom, prenom, email, tel, password, "etudiant");
	    System.out.println("\n"+user.toString());
	    
	    response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    
	    UserDAO userDAO = new UserDAO();
	    int user_id = userDAO.addUser(user);
	    
	    out.println("User_id: "+user_id);
	    
	    if (user_id == -1) {
			request.setAttribute("add_user_error", "Email Reserved!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_etudiant_add.jsp");
			dis.forward(request, response);	    	
	    }
	    else if (user_id > 0) {
	    	EtudiantDAO etudiantDAO = new EtudiantDAO();
	    	int etud_id = etudiantDAO.addEtudiant(cne, section_id, user_id);
	    	
		    out.println("Etud_id: "+etud_id);
	    	
		    // redirect ot assigning classe & matieres page
	    	if (etud_id > 0) {
	    		User etud_user = userDAO.getUser(user_id);
	    		SectionDAO sectionDAO = new SectionDAO();
	    		Section section = sectionDAO.getSection(section_id);
	    		Etudiant etudiant = new Etudiant(etud_user, etud_id, cne, section);
	    		
	    		System.out.println(etudiant.toString());
	    		
				request.setAttribute("etud_info", etudiant);
				RequestDispatcher dis = request.getRequestDispatcher("admin_etudiant_assigning.jsp");
				dis.forward(request, response);	
	    	}
	    	else {
	    		userDAO.deleteUser(user_id);
				request.setAttribute("add_etudiant_error", "Etudiant failed to insert!");
				RequestDispatcher dis = request.getRequestDispatcher("admin_etudiant_add.jsp");
				dis.forward(request, response);	   	    		
	    	}
	    }
	   // response.sendRedirect("admin_etudiants.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
