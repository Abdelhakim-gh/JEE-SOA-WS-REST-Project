package models.dao.interfaces;

import java.util.List;
import models.Etudiant;
import models.Datatypes.Payement;
import models.Datatypes.Role;

public interface IEtudiant {
	/* Operations on Etudiant
	 * selection return object
	 * updates return number
	 */
	
	public int addEtudiant(Etudiant etudiant);
	
	public Etudiant getEtudiant(int id);
	
	public int editEtudiant(int id);
	
	public int deleteEtudiant(int id);
	
	public List<Etudiant> getAllEtudiants();
	
	public List<Etudiant> filterByClass(String lable);
	
	public List<Etudiant> filterByMatiere(String lable);
	
	public List<Etudiant> filterByFiliere(String lable);
		
	public List<Etudiant> filterByNiveau(String lable);

	public List<Etudiant> filterByStatus(Payement status);
	
	public int login(String email, String password, Role role);
	
}
