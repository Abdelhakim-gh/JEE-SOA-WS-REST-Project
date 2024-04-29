package controlers.prof;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import models.dao.ProfDAO;
import models.dao.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class AddProf
 */
public class AddProf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		Float salaire = Float.parseFloat(request.getParameter("salaire")); 
		String[] matiereIds = request.getParameterValues("matieres");
		ArrayList<Integer> matieres= new ArrayList<Integer>();
	    if (matiereIds != null) {
	        for (String matiereId : matiereIds) {
	            int id = Integer.parseInt(matiereId);
	            matieres.add(id);
	        }
	    }
		
	    System.out.println("\nNom:"+nom);
	    System.out.println("Prenom:"+prenom);
	    System.out.println("email:"+email);
	    System.out.println("Password:"+password);
	    System.out.println("Tel:"+tel);
	    System.out.println("Salaire:"+salaire);
	    System.out.print("Matieres:[");
	    for (int item: matieres) {
	    	System.out.print(item+",");
	    }
	    System.out.print("]");
	    
	    User user = new User(nom, prenom, email, tel, password, "prof");
	    System.out.println("\n"+user.toString());
	    
	    response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    
	    UserDAO userDAO = new UserDAO();
	    int user_id = userDAO.addUser(user);
	    
	    out.println("User_id: "+user_id);
	    
	    if (user_id == -1) {
			request.setAttribute("add_user_error", "Email Reserved!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_prof_add.jsp");
			dis.forward(request, response);	    	
	    }
	    else if (user_id > 0) {
	    	ProfDAO profDAO = new ProfDAO();
	    	int prof_id = profDAO.addProf(salaire, user_id);
	    	
		    out.println("Prof_id: "+prof_id);
    		System.out.println(profDAO.getProf(prof_id).toString());

	    	if (prof_id > 0) {
	    		profDAO.enseigner_matieres(matieres, prof_id);
	    	}
	    	else {
	    		userDAO.deleteUser(user_id);
				request.setAttribute("add_prof_error", "Prof failed to insert!");
				RequestDispatcher dis = request.getRequestDispatcher("admin_prof_add.jsp");
				dis.forward(request, response);	   	    		
	    	}
	    }
	    response.sendRedirect("admin_profs.jsp");	    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
