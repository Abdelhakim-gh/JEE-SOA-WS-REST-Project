package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import models.Classe;
import models.Etudiant;
import models.Prof;
import models.Section;
import models.User;
import tools.DBInteraction;

/*
 *  this class get the basic information for Admin, etud, prof
 *  only used for Authentification
 */

public class AuthDAO {
	
	// get the user from database if found
	public User getUser(String email, String password) {
		
        DBInteraction.connect();
        String sql = "SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "'";
        ResultSet rs = DBInteraction.SelectQuery(sql);
        try {
			if (rs.next()) {
				
				int id = rs.getInt("id_user");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String tel = rs.getString("tel");
				String role = rs.getString("role");
				Timestamp created_at = rs.getTimestamp("created_at");
				Timestamp updated_at = rs.getTimestamp("updated_at");
				
				User user = new User(id, nom, prenom, email, tel, password, role, created_at, updated_at);
				
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
	
	public Etudiant getEtudiant(User user) {
        DBInteraction.connect();
        String sql = "SELECT * FROM etudiants WHERE user_id="+user.getId_user()+";";
        ResultSet rs = DBInteraction.SelectQuery(sql);
        try {
			if (rs.next()) {
				
				int id_etud = rs.getInt("id_etud");
				String cne = rs.getString("cne");
				int prix = rs.getInt("prix");
				int payement = rs.getInt("payement");
				
				SectionDAO sectionDAO = new SectionDAO();
				Section section = sectionDAO.getSection(rs.getInt("section_id"));
						
				ClasseDAO classeDAO = new ClasseDAO();
				Classe classe = classeDAO.getClasse(rs.getInt("classe_id")); 
				
				Etudiant etudiant = new Etudiant(user, id_etud, cne, prix, payement, classe, section);
				return etudiant;
				
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
	
	public Prof getProf(User user) {
        DBInteraction.connect();
        String sql = "SELECT * FROM profs WHERE user_id="+user.getId_user()+";";
        ResultSet rs = DBInteraction.SelectQuery(sql);
        try {
			if (rs.next()) {
				
				int id_prof = rs.getInt("id_prof");
				int salaire = rs.getInt("salaire");
				int payement = rs.getInt("payement");
				
				ProfDAO profDAO = new ProfDAO();
				Prof prof = new Prof(user, id_prof, salaire, payement);
				prof.setMatieres(profDAO.matieres_enseigner(prof.getId_prof()));
				
				return prof;
				
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
