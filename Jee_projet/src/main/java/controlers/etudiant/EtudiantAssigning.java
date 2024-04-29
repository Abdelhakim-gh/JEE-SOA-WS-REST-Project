package controlers.etudiant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Etudiant;
import models.dao.EtudiantDAO;
import models.dao.MatiereDAO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class EtudiantAssigning
 */
public class EtudiantAssigning extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtudiantAssigning() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int etud_id  = Integer.parseInt(request.getParameter("etud_id"));
		// dafault n/a
		int classe_id = 1; 

		if (request.getParameter("classe_id") != null) {
			classe_id = Integer.parseInt(request.getParameter("classe_id"));
		}

		
		String[] matiereIds = request.getParameterValues("matieres");
		
		MatiereDAO matiereDAO = new MatiereDAO();
		float prix = 0;		
		ArrayList<Integer> matieres= new ArrayList<Integer>();
	    if (matiereIds != null) {
	        for (String matiereId : matiereIds) {
	            int id = Integer.parseInt(matiereId);
	            matieres.add(id);
	            prix += matiereDAO.getMatierePrix(id);
	        }
	    }
	    
	    System.out.println("\nEtud_id: "+etud_id);
	    System.out.println("Classe_id: "+classe_id);

	    System.out.print("Matieres:[");
	    for (int item: matieres) {
	    	System.out.print(item+",");
	    }
	    System.out.print("]");
	    System.out.println("\nPrix: "+prix);
	    
	    EtudiantDAO etudiantDAO = new EtudiantDAO();
    	int nb = etudiantDAO.setClasse(etud_id, classe_id);
    	if (nb > 0) {
        	etudiantDAO.matieres_inscrit(matieres, etud_id);
        	nb = etudiantDAO.setPrix(etud_id, prix);
        	if (nb == -1) {
    			request.setAttribute("effuctue_etudiant_error", "failed to Assign Price!");
    			RequestDispatcher dis = request.getRequestDispatcher("admin_etudiants.jsp");
    			dis.forward(request, response);	 
        	}
    	}
    	else {
			request.setAttribute("effuctue_etudiant_error", "failed to Assign!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_etudiants.jsp");
			dis.forward(request, response);	 
    	}
    	
    	response.sendRedirect("admin_etudiants.jsp");	
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
