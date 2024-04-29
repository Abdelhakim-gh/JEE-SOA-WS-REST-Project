package models;

public class Section {

	private int id_section;
	private String filiere;
	private String niveau;
	private String label;
	
	
	public Section() {
		
	}
	
	public Section(int id_section) {
		this.id_section = id_section;

	}
	
	// for matieres
	public Section(int id_section, String label) {
		this.id_section = id_section;
		this.label = label;
	}
	
	public Section(int id_section, String niveau, String filiere, String label) {
		this.id_section = id_section;
		this.niveau = niveau;
		this.filiere = filiere;
		this.label = label;
	}

	public int getId_section() {
		return id_section;
	}
	public void setId_section(int id_section) {
		this.id_section = id_section;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String lable) {
		this.label = lable;
	}
	@Override
	public String toString() {
		return "Section [id_section=" + id_section + ", filiere=" + filiere + ", niveau=" + niveau + ", label=" + label
				+ "]";
	}
	
}
