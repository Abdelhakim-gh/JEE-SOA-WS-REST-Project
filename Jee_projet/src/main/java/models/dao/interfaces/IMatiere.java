package models.dao.interfaces;

import java.util.List;

import models.Matiere;

public interface IMatiere {

	/* Operations on Matiere
	 * selection return object
	 * updates return number
	 */
	
	public int addMatiere(Matiere matiere);
	
	public Matiere getMatiere(int id);
	
	public int editMatiere(int id);
	
	public int deleteMatiere(int id);
	
	public List<Matiere> getAllmatieres();
	
	// range p1 < x < p2
	public List<Matiere> filterByPrice(int p1, int p2);
	
	public List<Matiere> filterByFiliere(String lable);
	
	public List<Matiere> filterByNiveau(String lable);
	
}
