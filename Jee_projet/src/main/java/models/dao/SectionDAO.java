package models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Section;
import tools.DBInteraction;

public class SectionDAO {
	
	public int addSection(String niveau, String filiere) {
		DBInteraction.connect();
		// check if there is row with the same data
		String check = "select * from sections where lower(niveau) = lower('"+niveau+"') and lower(filiere) = lower('"+filiere+"');";
		ResultSet rs = DBInteraction.SelectQuery(check);
		
		// this mean if rs contain a row with the same name we shoudn't allow it
		try {
			if 	(rs.next()){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DBInteraction.disconnect();
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "insert into sections(niveau, filiere, label) values('"+niveau+"', '"+filiere+"', DEFAULT);";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
	
	public ArrayList<Section> readSections() {
		
		DBInteraction.connect();
		String sql = "select * from sections";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		ArrayList<Section> sections = new ArrayList<Section>();
		
		
		try {
			while (rs.next()) {
				Section section = new Section(
						rs.getInt("id_section"), 
						rs.getString("niveau"), 
						rs.getString("filiere"),
						rs.getString("label")
						);
				sections.add(section);
			}
			return sections;
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
	
	public int updateSection(int id, String niveau, String filiere) {
		
		DBInteraction.connect();
		String sql = "UPDATE sections SET niveau = '"+niveau+"', filiere = '"+filiere+"' WHERE id_section = "+id+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
		
	}
	
	public int deleteSection(int id) {
		
		DBInteraction.connect();
		String sql = "delete from sections WHERE id_section = "+id+";";
		int nb = DBInteraction.UpdateQuery(sql);
		DBInteraction.disconnect();
		return nb;
	}
	
	public ArrayList<Section> searchSection(String search) {
		
		return null;
	}
	
	public Section getSection(int id) {
		
		DBInteraction.connect();
		String sql = "select * from sections where id_section = "+id+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		try {
			if (rs.next()) {
				Section section = new Section(
						rs.getInt("id_section"), 
						rs.getString("niveau"), 
						rs.getString("filiere"),
						rs.getString("label")
						);
				return section;		
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
	
	public ArrayList<Section> filterSections(String rech) {
		
		DBInteraction.connect();
		String sql = "select * from sections where lower(label) like lower('%"+rech+"%')";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		ArrayList<Section> sections = new ArrayList<Section>();		
		
		try {
			while (rs.next()) {
				Section section = new Section(
						rs.getInt("id_section"), 
						rs.getString("niveau"), 
						rs.getString("filiere"),
						rs.getString("label")
						);
				sections.add(section);
			}
			return sections;
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
	
	// gets the label of Section base on id
	public String getSectionLabel(int id) {
		
		DBInteraction.connect();
		String sql = "select label from sections where id_section = "+id+";";
		ResultSet rs = DBInteraction.SelectQuery(sql);
		try {
			if (rs.next()) {
				String label = rs.getString("label"); 
				return label;		
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
	
	public int nombreSections() {
		DBInteraction.connect();
		String sql = "select count(*) from sections";
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
