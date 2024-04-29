package matiere;

import java.util.ArrayList;

public class MatiereService {

	MatiereDAO matiereDAO = new MatiereDAO();
	
	public ArrayList<Matiere> getMatieres() {
		return matiereDAO.readMatieres();
	}
	
	public Matiere getMatiere(int id) {
		return matiereDAO.getMatiere(id);
	}
	
	public Matiere addMatiere(int id_matiere, String label, float prix, int section_id) {
		int nb = matiereDAO.addMatiere(id_matiere, label, section_id, prix);
		if (nb > 0) {
			return matiereDAO.getMatiere(id_matiere);
		}
		return null;
	}
	
	public Matiere editMatiere(int id_matiere, String label, float prix) {
		int nb = matiereDAO.updateMatieres(id_matiere, label, prix);
		if (nb > 0) {
			return matiereDAO.getMatiere(id_matiere);
		}
		return null;
	}
	
	public int deleteMatiere(int id_matiere) {
		matiereDAO.delete_enseigneres_matiere(id_matiere);
		matiereDAO.delete_inscriptions_matiere(id_matiere);
		return matiereDAO.deleteMatieres(id_matiere);	
	}
}
