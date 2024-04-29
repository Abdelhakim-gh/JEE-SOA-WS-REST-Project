package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import models.Classe;
import models.Etudiant;
import models.Matiere;
import models.Prof;
import models.Section;
import models.User;
import models.Datatypes.Payement;
import models.Datatypes.Role;
import models.dao.interfaces.IEtudiant;
import tools.DBInteraction;

public class EtudiantDAO {

    public int addEtudiant(String cne, int section_id, int user_id) {
        String url = "jdbc:mysql://localhost:3306/jeegestionprojet";
        int generatedKey = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement stmt = conn.createStatement()) {

            String sql = "INSERT INTO etudiants (cne, section_id, user_id) VALUES ('" + cne + "',"+section_id+"," + user_id + ")";
            int rowsAffected = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                    	
                    	System.out.println("Etudiant_id: "+rs.getInt(1));
                    	
                        generatedKey = rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the error or handle it appropriately
        }

        return generatedKey;
    }
	
    public ArrayList<Etudiant> readEtudiants() {
		
		DBInteraction.connect();
		String sql = "select * from etudiants";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		
		try {
			UserDAO userDAO = new UserDAO();
			SectionDAO sectionDAO = new SectionDAO();
			ClasseDAO classDAO = new ClasseDAO();
			while (rs.next()) {
				// get etudiant data from etudiant
				Etudiant etudiant = new Etudiant(
						rs.getInt("id_etud"),
						rs.getString("cne"),
						rs.getFloat("prix"),
						rs.getByte("payement")
						);
				// to get user information about etudiant from users
				User user = userDAO.getUser(rs.getInt("user_id"));
				etudiant.setUser(user);
				
				// to get etudiant section from sections
				Section section = sectionDAO.getSection(rs.getInt("section_id"));
				etudiant.setSection(section);
				
				// to get etudiant classe from classes
				Classe classe = classDAO.getClasse(rs.getInt("classe_id"));
				etudiant.setClasse(classe);
				
				// to get etudiant matieres from matieres_incrit
				etudiant.setMatieres(matieres_inscrite(etudiant.getId_etud()));
				etudiants.add(etudiant);		
			}
			return etudiants;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		DBInteraction.disconnect();
		return null;
	}
    
	public void matieres_inscrit(ArrayList<Integer> matieres, int etud_id) {
		DBInteraction.connect();
		for (Integer matiere: matieres) {
			
			System.out.println("\nMatiere_id: "+matiere);
			
			String sql = "insert into matiere_inscrit(matiere_id, etud_id) values("+matiere+","+etud_id+");";
			int nb = DBInteraction.UpdateQuery(sql);

			System.out.println("NB Result: "+nb);
			
			if (nb == -1) {
				break;
			}
		}
	}
	
	public int setClasse(int id_etud, int classe_id) {
		DBInteraction.connect();
		String sql = "update etudiants set classe_id = "+classe_id+"  WHERE id_etud = "+id_etud+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
	
	public int setPrix(int id_etud, float prix) {
		DBInteraction.connect();
		String sql = "update etudiants set prix = "+prix+"  WHERE id_etud = "+id_etud+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}

	public ArrayList<Matiere> matieres_inscrite(int etud_id) {
		
		DBInteraction.connect();
		String sql = "select * from matiere_inscrit where etud_id = "+etud_id+"";
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

    public Etudiant getEtudiant(int id_etud) {
		
		DBInteraction.connect();
		String sql = "select * from etudiants where id_etud = "+id_etud+"";
		ResultSet rs = DBInteraction.SelectQuery(sql);
				
		try {
			UserDAO userDAO = new UserDAO();
			SectionDAO sectionDAO = new SectionDAO();
			ClasseDAO classDAO = new ClasseDAO();
			if (rs.next()) {
				// get etudiant data from etudiant
				Etudiant etudiant = new Etudiant(
						rs.getInt("id_etud"),
						rs.getString("cne"),
						rs.getFloat("prix"),
						rs.getByte("payement")
						);
				// to get user information about etudiant from users
				User user = userDAO.getUser(rs.getInt("user_id"));
				etudiant.setUser(user);
				
				// to get etudiant section from sections
				Section section = sectionDAO.getSection(rs.getInt("section_id"));
				etudiant.setSection(section);
				
				// to get etudiant classe from classes
				Classe classe = classDAO.getClasse(rs.getInt("classe_id"));
				etudiant.setClasse(classe);
				
				// to get etudiant matieres from matieres_incrit
				etudiant.setMatieres(matieres_inscrite(etudiant.getId_etud()));
				
				return etudiant;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		DBInteraction.disconnect();
		return null;
	}
	
	public int updateEtudiant(int id_etud, int payement, int classe_id) {
		DBInteraction.connect();
		String sql = "update etudiants set payement = "+payement+" , classe_id = "+classe_id+" WHERE id_etud = "+id_etud+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
    
	public int deleteEtudiant(int id_etud) {
		DBInteraction.connect();
		String sql = "delete from etudiants WHERE id_etud = "+id_etud+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
	
	public void delete_matieres_inscrit(int id_etud) {
		DBInteraction.connect();
		String sql = "delete from matiere_inscrit WHERE etud_id = "+id_etud+";";
		DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
	}
	
	public int nombreEtudiantPay() {
		DBInteraction.connect();
		String sql = "select count(*) from etudiants where payement = 1";
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
	
	public int nombreEtudiantNonPay() {
		DBInteraction.connect();
		String sql = "select count(*) from etudiants where payement = 0";
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
	
	public int nombreEtudiants() {
		DBInteraction.connect();
		String sql = "select count(*) from etudiants";
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

	public ArrayList<Etudiant> getEtudiantByClass(int classe_id) {
		DBInteraction.connect();
		String sql = "select * from etudiants where classe_id ="+classe_id+" order by prix";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		
		try {
			UserDAO userDAO = new UserDAO();
			while (rs.next()) {
				// get etudiant data from etudiant
				Etudiant etudiant = new Etudiant(
						rs.getInt("id_etud"),
						rs.getString("cne"),
						rs.getFloat("prix"),
						rs.getByte("payement")
						);
				// to get user information about etudiant from users
				User user = userDAO.getUser(rs.getInt("user_id"));
				etudiant.setUser(user);			
			
				etudiants.add(etudiant);
			}
			return etudiants;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		DBInteraction.disconnect();
		return null;
	}
	
	public int nombreEtudiantsSection(int section_id) {
		DBInteraction.connect();
		String sql = "select count(*) from etudiants where section_id = "+section_id+"";
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

	public int nombreEtudiantsClasse(int classe_id) {
		DBInteraction.connect();
		String sql = "select count(*) from etudiants where classe_id = "+classe_id+"";
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
	
	public void cancelClasse(int classe_id) {
		DBInteraction.connect();
		String sql = "delete from etudiants WHERE classe_id = "+classe_id+";";
		DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
	}
}
