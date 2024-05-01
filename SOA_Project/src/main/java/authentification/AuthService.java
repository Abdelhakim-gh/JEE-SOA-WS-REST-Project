package authentification;

import etudiant.Etudiant;
import prof.Prof;
import user.User;

public class AuthService {

    private AuthDAO authDAO = new AuthDAO();
    
    public Object userlogin(String email, String password) {
        User user = authDAO.getUser(email, password);
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {
                return user;
            } else if (user.getRole().equalsIgnoreCase("prof")) {
                Prof prof = authDAO.getProf(user);
                if (prof != null) {
                	return prof;
                } else {
                	return null;
                }
            } else if (user.getRole().equalsIgnoreCase("etudiant")) {
                Etudiant etudiant = authDAO.getEtudiant(user);
                if (etudiant != null) {
                	return etudiant;
                } else {
                	return null;
                }            
            } 
        }
        return null;    
    }

//  public String authenticateUser(String email, String password) {
//  User user = authDAO.getUser(email, password);
//
//  if (user != null) {
//      return user.getRole();
//  } else {
//      return null;
//  }
//}
    
//    public Etudiant etudiantlogin(User user) {
//    	return authDAO.getEtudiant(user);
//    }
//    
//    public Prof proflogin(User user) {
//    	return authDAO.getProf(user);
//    }
	
	// login function
	
	// logout function
}
