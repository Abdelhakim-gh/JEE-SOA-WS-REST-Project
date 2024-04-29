package models.dao.interfaces;

import java.util.List;
import models.Prof;
import models.Datatypes.Payement;
import models.Datatypes.Role;

public interface IProf {

	/* Operations on Prof
	 * selection return object
	 * updates return number
	 */
	
	public int addProf(Prof prof);
	
	public Prof getProf(int id);
	
	public int editProf(int id);
	
	public int deleteProf(int id);
	
	public List<Prof> getAllProfs();
		
	public List<Prof> filterByMatiere(String lable);
	
	public List<Prof> filterByFiliere(String lable);
	
	public List<Prof> filterByNiveau(String lable);

	public List<Prof> filterByStatus(Payement status);
	
	public int login(String email, String password, Role role);
}
