package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Matiere;
import models.Prof;
import models.User;
import models.Datatypes.Payement;
import models.Datatypes.Role;
import models.dao.interfaces.IProf;
import tools.DBInteraction;

public class ProfDAO {
	
    public int addProf(float salaire, int user_id) {
        String url = "jdbc:mysql://localhost:3306/jeegestionprojet";
        int generatedKey = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement stmt = conn.createStatement()) {

            String sql = "INSERT INTO profs (salaire, user_id) VALUES (" + salaire + ", " + user_id + ")";
            int rowsAffected = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                    	
                    	System.out.println("Prof_id: "+rs.getInt(1));
                    	
                        generatedKey = rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the error or handle it appropriately
        }

        return generatedKey;
    }

	public void enseigner_matieres(ArrayList<Integer> matieres, int prof_id) {
		DBInteraction.connect();
		for (Integer matiere: matieres) {
			
			System.out.println("\nMatiere_id: "+matiere);
			
			String sql = "insert into enseigner_matiere(matiere_id, prof_id) values("+matiere+","+prof_id+");";
			int nb = DBInteraction.UpdateQuery(sql);

			System.out.println("NB Result: "+nb);
			
			if (nb == -1) {
				break;
			}
		}
	}
	
	public ArrayList<Prof> readProfs() {
		
		DBInteraction.connect();
		String sql = "select * from profs";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		
		ArrayList<Prof> profs = new ArrayList<Prof>();
		
		try {
			UserDAO userDAO = new UserDAO();
			while (rs.next()) {
				// get profs data from profs
				Prof prof = new Prof(
						rs.getInt("id_prof"),
						rs.getFloat("salaire"),
						rs.getByte("payement")
						);
				// to get user information about prof from users
				User user = userDAO.getUser(rs.getInt("user_id"));
				prof.setUser(user);
				// to get prof matieres from matieres_enseigner
				prof.setMatieres(matieres_enseigner(prof.getId_prof()));
				profs.add(prof);		
			}
			return profs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		DBInteraction.disconnect();
		return null;
	}
	
	public Prof getProf(int id_prof) {
		DBInteraction.connect();
		String sql = "select * from profs where id_prof = "+id_prof+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
				
		try {
			UserDAO userDAO = new UserDAO();
			if (rs.next()) {
				// get profs data from profs
				Prof prof = new Prof(
						rs.getInt("id_prof"),
						rs.getFloat("salaire"),
						rs.getByte("payement")
						);
				// to get user information about prof from users
				User user = userDAO.getUser(rs.getInt("user_id"));
				prof.setUser(user);
				// to get prof matieres from matieres_enseigner
				prof.setMatieres(matieres_enseigner(prof.getId_prof()));

				return prof;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		DBInteraction.disconnect();
		return null;
	}
	
	public ArrayList<Matiere> matieres_enseigner(int prof_id) {
		
		DBInteraction.connect();
		String sql = "select * from enseigner_matiere where prof_id = "+prof_id+"";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		
		try {
			MatiereDAO matiereDAO = new MatiereDAO();
			while (rs.next()) {
				Matiere matiere = matiereDAO.getMatiere(rs.getInt("matiere_id"));
				matieres.add(matiere);
			}
			return matieres;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		DBInteraction.disconnect();
		return null;
		
	}
	
	public void delete_matieres_enseigner(int prof_id) {
		DBInteraction.connect();
		String sql = "delete from enseigner_matiere WHERE prof_id = "+prof_id+";";
		DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
	}

	public int deleteProf(int id_prof) {
		DBInteraction.connect();
		String sql = "delete from profs WHERE id_prof = "+id_prof+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}

	public int updateProf(int id_prof, float salaire, int payement) {
		DBInteraction.connect();
		String sql = "update profs set salaire = "+salaire+" , payement = "+payement+" WHERE id_prof = "+id_prof+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}

	public int nombreProfsPay() {
		DBInteraction.connect();
		String sql = "select count(*) from profs where payement = 1";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		
		try {
			if (rs.next()) {
				int nb = rs.getInt(1);
				return nb;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int nombreProfsNonPay() {
		DBInteraction.connect();
		String sql = "select count(*) from profs where payement = 0";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		
		try {
			if (rs.next()) {
				int nb = rs.getInt(1);
				return nb;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int nombreProfs() {
		DBInteraction.connect();
		String sql = "select count(*) from profs";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		
		try {
			if (rs.next()) {
				int nb = rs.getInt(1);
				return nb;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
