package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Matiere;
import models.Prof;
import models.Section;
import tools.DBInteraction;


public class MatiereDAO {

	public int addMatiere(String label, int id_section, float prix) {
		DBInteraction.connect();
		// check if there is row with the same data
//		String check = "select * from matieres where label = '"+label+" and section_id = "+id_section+";";
//		ResultSet rs = DBInteraction.SelectQuery(check);
//			
//		// this mean if rs contain a row with the same name we shoudn't allow it
//		if 	(rs != null){
//			try {
//				rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return -1;
//		}
		String sql = "insert into matieres(label, section_id, prix) values('"+label+"', "+id_section+", "+prix+");";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
	
	public ArrayList<Matiere> readMatieres() {
		
		DBInteraction.connect();
		String sql = "select * from matieres";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		
		try {
			while (rs.next()) {
				Matiere matiere = new Matiere(
						rs.getInt("id_matiere"),
						rs.getString("label"),
						rs.getFloat("prix")
						);
				
				SectionDAO sectionDAO = new SectionDAO();
				Section section= sectionDAO.getSection(rs.getInt("section_id"));
				matiere.setSection(section);
				
				matieres.add(matiere);
			}
			return matieres;
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
	
	public int updateMatieres(int id, String label, float prix) {
		
		DBInteraction.connect();
		String sql = "UPDATE matieres SET label = '"+label+"', prix = "+prix+" WHERE id_matiere = "+id+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
		
	}
	
	public int deleteMatieres(int id) {
		
		DBInteraction.connect();
		String sql = "delete from matieres WHERE id_matiere = "+id+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
	
	public ArrayList<Section> searchSection(String search) {
		
		return null;
	}
	
	public Matiere getMatiere(int id) {
		
		DBInteraction.connect();
		String sql = "select * from matieres where id_matiere = "+id+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		try {
			if (rs.next()) {
				Matiere matiere = new Matiere(
						rs.getInt("id_matiere"),
						rs.getString("label"),
						rs.getFloat("prix")
						);
				
				SectionDAO sectionDAO = new SectionDAO();
				Section section= sectionDAO.getSection(rs.getInt("section_id"));
				matiere.setSection(section);
				return matiere;
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

	public ArrayList<Matiere> readSortedMatieres() {
		
		DBInteraction.connect();
		String sql = "select * from matieres order by label";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		
		try {
			while (rs.next()) {
				Matiere matiere = new Matiere(
						rs.getInt("id_matiere"),
						rs.getString("label"),
						rs.getFloat("prix")
						);
				
				SectionDAO sectionDAO = new SectionDAO();
				Section section= sectionDAO.getSection(rs.getInt("section_id"));
				matiere.setSection(section);
				
				matieres.add(matiere);
			}
			return matieres;
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
	
	public ArrayList<Section> filterMatieres(String rech) {
		
		
		return null;
	}	
	
	public ArrayList<Prof> getEnseigneres(int matiere_id) {
		
		DBInteraction.connect();
		String sql = "select prof_id from enseigner_matiere where matiere_id = "+matiere_id+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		
		ArrayList<Prof> profs = new ArrayList<Prof>();
		ProfDAO profDAO = new ProfDAO();
		
		try {
			while (rs.next()) {
				Prof prof = profDAO.getProf(rs.getInt("prof_id"));
				profs.add(prof);
			}
			return profs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	public ArrayList<Matiere> getMatieresbySection(int section_id) {
		
		DBInteraction.connect();
		String sql = "select * from matieres where section_id = "+section_id+" order by label";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		ArrayList<Matiere> matieres = new ArrayList<Matiere>();
		
		try {
			while (rs.next()) {
				Matiere matiere = new Matiere(
						rs.getInt("id_matiere"),
						rs.getString("label"),
						rs.getFloat("prix")
						);
								
				matieres.add(matiere);
			}
			return matieres;
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

	public float getMatierePrix(int id_matiere) {
		DBInteraction.connect();
		String sql = "select prix from matieres where id_matiere = "+id_matiere+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		try {
			if (rs.next()) {
				float prix = rs.getFloat("prix");
				return prix;
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
		
		return 0;
	}

	public int nombreInscriptions(int matiere_id) {
		DBInteraction.connect();
		String sql = "select count(*) from matiere_inscrit where matiere_id = "+matiere_id+"";
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

	public int nombreEnseigneres(int matiere_id) {
		DBInteraction.connect();
		String sql = "select count(*) from matiere_inscrit where matiere_id = "+matiere_id+"";
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
	
	public int nombreMatieresSection(int section_id) {
		DBInteraction.connect();
		String sql = "select count(*) from matieres where section_id = "+section_id+"";
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

	public void delete_enseigneres_matiere(int matiere_id) {
		DBInteraction.connect();
		String sql = "delete from enseigner_matiere WHERE matiere_id = "+matiere_id+";";
		DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
	}
	
	public void delete_inscriptions_matiere(int matiere_id) {
		DBInteraction.connect();
		String sql = "delete from matiere_inscrit WHERE matiere_id = "+matiere_id+";";
		DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
	}

	public int nombreMatieres() {
		DBInteraction.connect();
		String sql = "select count(*) from matieres";
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
