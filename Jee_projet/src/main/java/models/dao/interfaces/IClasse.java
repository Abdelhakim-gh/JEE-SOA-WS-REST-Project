package models.dao.interfaces;

import java.util.List;

import models.Classe;

public interface IClasse {

	/* Operations on Classe
	 * selection return object
	 * updates return number
	 */
	
	public int addClasse(Classe classe);
	
	public Classe getClasse(int id);
	
	public int editClasse(int id);
	
	public int deleteClasse(int id);
	
	public List<Classe> getAllClasses();
	
	public List<Classe> filterByMatiere(String lable);
	
	public List<Classe> filterByFiliere(String lable);
	
	public List<Classe> filterByNiveau(String lable);
	
}
