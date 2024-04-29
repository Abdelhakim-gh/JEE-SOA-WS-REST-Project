package authentification;

import etudiant.Etudiant;
import prof.Prof;
import user.User;

public class AuthService {

    private AuthDAO authDAO = new AuthDAO();

    public User userlogin(String email, String password) {
        return authDAO.getUser(email, password);
    }
    
    public Etudiant etudiantlogin(User user) {
    	return authDAO.getEtudiant(user);
    }
    
    public Prof proflogin(User user) {
    	return authDAO.getProf(user);
    }
	
	// login function
	
	// logout function
}
