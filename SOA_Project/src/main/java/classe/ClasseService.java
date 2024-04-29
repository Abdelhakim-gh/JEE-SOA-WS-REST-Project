package classe;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import matiere.*;
import section.*;

public class ClasseService {

	ClasseDAO classeDAO = new ClasseDAO();
	
	public ArrayList<Classe> getClasses() {
		return classeDAO.readClasses();
	}
	
	public Classe getClasse(int id) {
		return classeDAO.getClasse(id);
	}
	
	public Classe addClasse(int id_classe, String label, int id_section) {
		int nb = classeDAO.addClasse(id_classe, label, id_section);
		if (nb > 0) {
			return classeDAO.getClasse(id_classe);
		}
		return null;
	}
	
	public Classe editClasse(int id_classe, String label) {
		int nb = classeDAO.updateClasse(id_classe, label);
		if (nb > 0) {
			return classeDAO.getClasse(id_classe);
		}
		return null;
	}
	
	public int deleteClasse(int id_classe) {
		return classeDAO.deleteClasse(id_classe);
	}
}
