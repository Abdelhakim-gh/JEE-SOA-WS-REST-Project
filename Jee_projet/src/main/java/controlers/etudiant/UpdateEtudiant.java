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
import tools.DBInteraction;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class UpdateEtudiant
 */
public class UpdateEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEtudiant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id_etud = Integer.parseInt(request.getParameter("id_etud"));
		
	    EtudiantDAO etudiantDAO = new EtudiantDAO();
	    Etudiant etudiant = etudiantDAO.getEtudiant(id_etud);
		
		String cid = request.getParameter("classe_id");
		int classe_id;
		if (cid != null) {
			classe_id = Integer.parseInt(cid); 
		}
		else {
			classe_id = etudiant.getClasse().getId_classe();
		}
		
		String pid = request.getParameter("payement");
		int payement;
		if (pid != null) {
			payement = Integer.parseInt(pid);
		}
		else {
			payement = etudiant.getPayement();
		}
		
		float prix = 0;
		String[] matiereIds = request.getParameterValues("matieres");
		ArrayList<Integer> matieres= new ArrayList<Integer>();
	    if (matiereIds != null) {
	    	MatiereDAO matiereDAO = new MatiereDAO();
	        for (String matiereId : matiereIds) {
	            int id = Integer.parseInt(matiereId);
	            matieres.add(id);
	            prix += matiereDAO.getMatierePrix(id);
	        }
	    }
	    
	    System.out.println("\nPrix: "+prix);

	    
	    int nb = etudiantDAO.updateEtudiant(id_etud, payement, classe_id);
		if (nb == -1) {
			request.setAttribute("update_etudiant_error", "Etudiant failed to Upadate!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_etudiants.jsp");
			dis.forward(request, response);	 
		}
		else {
			if (matiereIds != null) {
				etudiantDAO.delete_matieres_inscrit(id_etud);
				etudiantDAO.matieres_inscrit(matieres, id_etud);
				
				// set new price 
	        	nb = etudiantDAO.setPrix(id_etud, prix);
	        	if (nb == -1) {
	    			request.setAttribute("effuctue_etudiant_error", "failed to Assign Price!");
	    			RequestDispatcher dis = request.getRequestDispatcher("admin_etudiants.jsp");
	    			dis.forward(request, response);	 
	        	}
			}
			response.sendRedirect("admin_etudiants.jsp");
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
