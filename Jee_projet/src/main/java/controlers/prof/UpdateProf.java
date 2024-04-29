package controlers.prof;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Prof;
import models.dao.ProfDAO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class UpdateProf
 */
public class UpdateProf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id_prof = Integer.parseInt(request.getParameter("id_prof"));
		
		ProfDAO profDAO = new ProfDAO();
		Prof prof = profDAO.getProf(id_prof);
		
		String s = request.getParameter("salaire");
		float salaire;
	    if (s != null) {
	        salaire = Float.parseFloat(s);
	    } 
	    else {
	        salaire = prof.getSalaire();
	    }

		String p = request.getParameter("payement");
		int payement;
		if (p != null) {
			payement = Integer.parseInt(p);
		}
		else {
			payement = prof.getPayement();
		}
		
		String[] matiereIds = request.getParameterValues("matieres");
		ArrayList<Integer> matieres= new ArrayList<Integer>();
	    if (matiereIds != null) {
	        for (String matiereId : matiereIds) {
	            int id = Integer.parseInt(matiereId);
	            matieres.add(id);
	        }
	    }
	    
		System.out.println("Salaire: "+salaire);
		System.out.println("Payement: "+payement);
		
		int nb = profDAO.updateProf(id_prof, salaire, payement);
		if (nb == -1) {
			request.setAttribute("update_prof_error", "Prof failed to Upadate!");
			RequestDispatcher dis = request.getRequestDispatcher("admin_prof_edit.jsp?id_prof="+id_prof+"");
			dis.forward(request, response);	 
		}
		else {
			if (matiereIds != null) {
				profDAO.delete_matieres_enseigner(id_prof);
	    		profDAO.enseigner_matieres(matieres, id_prof);
			}
			response.sendRedirect("admin_profs.jsp");
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
