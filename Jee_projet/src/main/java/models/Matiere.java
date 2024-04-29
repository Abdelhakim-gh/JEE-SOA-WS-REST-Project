package models;

public class Matiere {
	
	private int id_matiere;
	private String lable;
	private Section section;
	private float prix;
	
	public Matiere(int id_matiere, String lable, float prix) {
		this.id_matiere = id_matiere;
		this.lable = lable;
		this.prix = prix;
	}
	
	public Matiere(int id_matiere, String lable, Section section, float prix) {
		super();
		this.id_matiere = id_matiere;
		this.lable = lable;
		this.section = section;
		this.prix = prix;
	}

	public int getId_matiere() {
		return id_matiere;
	}

	public void setId_matiere(int id_matiere) {
		this.id_matiere = id_matiere;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Matiere [id_matiere=" + id_matiere + ", lable=" + lable + ", section=" + section + ", prix=" + prix
				+ "]";
	}
		
}
