package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Classe;
import models.Section;
import models.User;
import models.Datatypes.Role;
import tools.DBInteraction;

public class UserDAO {

	public int addUser(User user) {
	    
		if (emailExist(user.getEmail())) {
	        // Email is reserved
	        return -1;
	    } else {

	    	try {	
	    		try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
		        String url = "jdbc:mysql://localhost:3306/jeegestionprojet";
	            Connection conn = DriverManager.getConnection(url, "root", "");
	            Statement stmt = conn.createStatement();
	            String sql = "INSERT INTO users (nom, prenom, email, tel, password, role) VALUES ('" + user.getNom() + "', '" + user.getPrenom() + "', '" + user.getEmail() + "', '" + user.getTel() + "', '" + user.getPassword() + "', '" + user.getRole() + "')";
	            int rowsAffected = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
	            
	            if (rowsAffected > 0) {
	                try (ResultSet rs = stmt.getGeneratedKeys()) {
	                    if (rs.next()) {
	                    	
	                    	System.out.println("\nGenerated Key:"+rs.getInt(1));
	                    	
	                        return rs.getInt(1);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Log the error or handle it appropriately
	        }
	    }
	    return 0;
	}

	public Boolean emailExist(String email) {
	    DBInteraction.connect();
	    String check = "SELECT * FROM users WHERE LOWER(email) = LOWER('" + email + "'); ";
	    ResultSet rs = DBInteraction.SelectQuery(check);

	    try {
	        // Check if rs contains a row with the same email
	        if (rs.next()) {
	            // Email is reserved
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Log the error or handle it appropriately
	    } finally {
	        // Close resources in a finally block
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Log the error or handle it appropriately
	        }
	        DBInteraction.disconnect();
	    }

	    return false;
	}
	
	public void deleteUser(int id_user) {
		DBInteraction.connect();
		String sql = "delete from users WHERE id_user = "+id_user+";";
		DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
	}

	public int updateUser(int id_user, String role, String email, String password, String tel, String nom, String prenom) {
		int nb = -1;
		
		System.out.println("\nid_user: "+id_user);
		System.out.println("role: "+role);
		System.out.println("nom: "+nom);
		System.out.println("prenom: "+prenom);
		System.out.println("email: "+email);
		System.out.println("password: "+password);
		System.out.println("tel: "+tel);
		
		if (role.compareToIgnoreCase("admin")==0) {
			DBInteraction.connect();
			String sql = "update users set nom = '"+nom+"', prenom = '"+prenom+"', email = '"+email+"', password = '"+password+"', tel = '"+tel+"' WHERE id_user = "+id_user+";";
			nb = DBInteraction.UpdateQuery(sql);
			DBInteraction.disconnect();
		}
		else if (role.compareToIgnoreCase("prof")==0) {
			DBInteraction.connect();
			String sql = "update users set email = '"+email+"', password = '"+password+"', tel = '"+tel+"' WHERE id_user = "+id_user+";";
			nb = DBInteraction.UpdateQuery(sql);
			DBInteraction.disconnect();
		}
		else {
			DBInteraction.connect();
			String sql = "update users set email = '"+email+"', password = '"+password+"', tel = '"+tel+"' WHERE id_user = "+id_user+";";
			nb = DBInteraction.UpdateQuery(sql);
			DBInteraction.disconnect();	
		}
		
		System.out.println("NB: "+nb);
		return nb;
	}
	
	public int getUserId(String email) {
		
		DBInteraction.connect();
		String sql = "select id from users where lower(email) = lower("+email+");";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		try {
			if (rs.next()) {
				int user_id = rs.getInt("id");
				
				return user_id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        // Close the ResultSet
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	        }
	        // Close the database connection
	        DBInteraction.disconnect();
	    }

		return -1;
		
	}
	
	public User getUser(int id_user) {
		DBInteraction.connect();
		String sql = "select * from users where id_user = "+id_user+" ;";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		try {
			if (rs.next()) {
				User user = new User(
						rs.getInt("id_user"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("tel"),
						rs.getString("password"),
						rs.getString("role")
						);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        // Close the ResultSet
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	        }
	        // Close the database connection
	        DBInteraction.disconnect();
	    }
		return null;
	}
 
	
}
