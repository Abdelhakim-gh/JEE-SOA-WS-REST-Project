package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Classe;
import models.Section;
import models.dao.interfaces.IClasse;
import tools.DBInteraction;

public class ClasseDAO {

	public int addClasse(String label, int id_section) {
		DBInteraction.connect();
//		// check if there is row with the same data
//		String check = "select * from classes where lower(label) = lower('"+label+"') and section_id = "+id_section+";";
//		ResultSet rs = DBInteraction.SelectQuery(check);
//		
//		// this mean if rs contain a row with the same name we shoudn't allow it
//		try {
//	        if (rs.next()) {
//	            // Email is reserved
//	            return -1;
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace(); // Log the error or handle it appropriately
//	    } finally {
//	        // Close resources in a finally block
//	            if (rs != null) {
//	                try {
//						rs.close();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	            }
//	        
//	        DBInteraction.disconnect();
//	    }
//		
		String sql = "insert into classes(label, section_id) values('"+label+"', "+id_section+");";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
	
	public ArrayList<Classe> readClasses() {
		
		DBInteraction.connect();
		String sql = "select * from classes";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		ArrayList<Classe> classes = new ArrayList<Classe>();
		
		try {
			while (rs.next()) {
				Classe classe = new Classe(
							rs.getInt("id_classe"),
							rs.getString("label")
						);
				SectionDAO sectionDAO = new SectionDAO();
				Section section= sectionDAO.getSection(rs.getInt("section_id"));
				classe.setSection(section);
				
				classes.add(classe);
			}
			return classes;
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
	
	public int updateClasse(int id, String label) {
		
		DBInteraction.connect();
		String sql = "UPDATE classes SET label = '"+label+"' WHERE id_classe = "+id+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
		
	}
	
	public int deleteClasse(int id) {
		
		DBInteraction.connect();
		String sql = "delete from classes WHERE id_classe = "+id+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
	
	public ArrayList<Classe> searchClasse(String search) {
		
		return null;
	}
	
	public Classe getClasse(int id) {
		
		DBInteraction.connect();
		String sql = "select * from classes where id_classe = "+id+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		try {
			if (rs.next()) {
				Classe classe = new Classe(
						rs.getInt("id_classe"),
						rs.getString("label")
					);
				SectionDAO sectionDAO = new SectionDAO();
				Section section= sectionDAO.getSection(rs.getInt("section_id"));
				classe.setSection(section);
				
				return classe;
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
	
	public ArrayList<Classe> filterClasses(String rech) {
		
		return null;
	}	
	
	// gets the label of Section base on id
	public String getClasseLabel(int id) {
				
		return null;
	}
	
	public ArrayList<Classe> getClassesBySectionId(int section_id) {
		
		DBInteraction.connect();
		String sql = "select * from classes where section_id="+section_id+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		ArrayList<Classe> classes = new ArrayList<Classe>();
		
		try {
			while (rs.next()) {
				Classe classe = new Classe(
							rs.getInt("id_classe"),
							rs.getString("label")
						);
				SectionDAO sectionDAO = new SectionDAO();
				Section section= sectionDAO.getSection(rs.getInt("section_id"));
				classe.setSection(section);
				
				classes.add(classe);
			}
			return classes;
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
	
	public int nombreClassesSection(int section_id) {
		DBInteraction.connect();
		String sql = "select count(*) from classes where section_id = "+section_id+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int nombreClasses() {
		DBInteraction.connect();
		String sql = "select count(*) from classes";
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
