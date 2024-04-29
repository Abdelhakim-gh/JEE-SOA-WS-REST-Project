package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;

public class UserService {
	
	UserDAO userDOA = new UserDAO();
	
	public User getUser(int id_user) {
		return userDOA.getUser(id_user);
	}
	
	public ArrayList<User> getUsers() {
		return userDOA.getUsers();
	}
	
//	public User addUser(User user) {
//		int nb = userDOA.addUser(user);
//		if (nb != 0 & nb != -1) {
//			User created_user = userDOA.getUser(nb);
//			return created_user;
//		} 
//		return null;
//	}

	public Map<String, Object> addUser(User user) {
	    Map<String, Object> result = new HashMap<>();
	    int nb = userDOA.addUser(user);
	    if (nb != 0 && nb != -1) {
	        User created_user = userDOA.getUser(nb);
	        result.put("user", created_user);
	        result.put("key", nb);
	    } else {
	        result.put("user", null);
	        result.put("key", -1); // Or any other value to indicate failure
	    }
	    return result;
	}
	
	public User updateUser(int id_user, String role, String email, String password, String tel, String nom, String prenom) {
		int nb = userDOA.updateUser(id_user, role, email, password, tel, nom, prenom);
		if (nb != -1) {
			return getUser(id_user);
		} 
		return null;
	}

//	public User updateUser(User user) {
//		int nb = userDOA.updateUser(user);
//		if (nb != -1) {
//			return getUser(user.getId_user());
//		} 
//		return null;
//	}
	
	public int deleteUser(int id_user) {
		int nb = userDOA.deleteUser(id_user);
		return nb;
	}


}
