package section;

import java.util.ArrayList;

import matiere.Matiere;

public class SectionService {

	SectionDAO sectionDAO = new SectionDAO();
	
	public ArrayList<Section> getSections() {
		return sectionDAO.readSections();
	}
	
	public Section getSection(int id) {
		return sectionDAO.getSection(id);
	}

	public Section addSection(int id_section, String niveau, String filiere) {
		int nb = sectionDAO.addSection(id_section, niveau, filiere);
		if (nb > 0) {
			return sectionDAO.getSection(id_section);
		}
		return null;
	}
	
	public Section editSection(int id_section, String niveau, String filiere) {
		int nb = sectionDAO.updateSection(id_section, niveau, filiere);
		if (nb > 0) {
			return sectionDAO.getSection(id_section);
		}
		return null;
	}
	
	public int deleteSection(int id_section) {
		return sectionDAO.deleteSection(id_section);	
	}
	
}
