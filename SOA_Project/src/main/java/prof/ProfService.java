package prof;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import section.*;
import user.*;

public class ProfService {

	ProfDAO profDAO = new ProfDAO();
	UserService userService = new UserService();
	UserDAO userDAO = new UserDAO();
	
	public ArrayList<Prof> getProfs() {
		return profDAO.readProfs();
	}

	public Prof getProf(int id_prof) {
		return profDAO.getProf(id_prof);
	}
	
	public Prof addProf(User user, int id_prof) {
		
	    Map<String, Object> created_user = new HashMap<>();
		created_user = userService.addUser(user);
		if (created_user != null) {		
			int key = (int) created_user.get("key");
			if (key > 0) {
				// add default price
				int prof_id = profDAO.addProf(id_prof, (float) 0.0, key);
				if (prof_id > 0) {
					// add default matiere
		    		profDAO.enseigner_matieres(new ArrayList<>(Arrays.asList(1)), id_prof);
		    		
		    		return profDAO.getProf(id_prof);
		    		
				} else {
		    		userDAO.deleteUser(key);
				}
			}
		}
		
		return null;
	}
	
	public void updateProf(int id_prof, float salaire, int payement, String matieres) {
	    	    
	}
	
	public int deleteProf(int id_prof) {
		
		// delete all matieres enseigner 
		profDAO.delete_matieres_enseigner(id_prof);
		// prof user info
		Prof prof = profDAO.getProf(id_prof);
		int user_id = prof.getUser().getId_user();		
		// delete prof 
		int nb = profDAO.deleteProf(id_prof);
		// delete user
		userDAO.deleteUser(user_id);
		return nb;
	}
	
}

