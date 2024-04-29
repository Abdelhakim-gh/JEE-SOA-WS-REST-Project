package etudiant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;

import etudiant.Etudiant;
import matiere.*;
import section.*;
import user.*;

public class EtudiantService {

	EtudiantDAO etudiantDAO = new EtudiantDAO();
	UserService userService = new UserService();
	UserDAO userDAO = new UserDAO();
	
	public Etudiant getEtudiant(int id_etud) {
		return etudiantDAO.getEtudiant(id_etud);
	}
	
	public ArrayList<Etudiant> getEtudiants() {
		return etudiantDAO.readEtudiants();
	}
	
	public Etudiant addEtudiant(User user, int id_etud, String cne, int section_id) {
		
	    Map<String, Object> created_user = new HashMap<>();
		created_user = userService.addUser(user);
		if (created_user != null) {		
			int key = (int) created_user.get("key");
			if (key > 0) {
				int etud_id = etudiantDAO.addEtudiant(id_etud, cne, section_id, key);
				if (etud_id > 0) {
					User etud_user = userDAO.getUser(key);
		    		SectionDAO sectionDAO = new SectionDAO();
		    		Section section = sectionDAO.getSection(section_id);
		    		
		    		// default variables
		        	etudiantDAO.setClasse(etud_id, 1);
		        	etudiantDAO.matieres_inscrit(new ArrayList<>(Arrays.asList(1)), etud_id);
		        	etudiantDAO.setPrix(etud_id, (float) 0.0);
		  
		        	return new Etudiant(etud_user, etud_id, cne, section);  
		    		
				} else {
		    		userDAO.deleteUser(key);
				}
			}
		}
		
		return null;
	}
	
	public void updateEtudiant(int id_etud, int payement, int classe_id) {
	    	    
	}
	
	public int deleteEtudiant(int id_etud) {
	    etudiantDAO.delete_matieres_inscrit(id_etud);
		int nb;
		// etud user info
		Etudiant etudiant = etudiantDAO.getEtudiant(id_etud);
		int user_id = etudiant.getUser().getId_user();
		// delete etud 
		nb = etudiantDAO.deleteEtudiant(id_etud);
		// delete user
		userDAO.deleteUser(user_id);
		return nb;
	}
	
}
