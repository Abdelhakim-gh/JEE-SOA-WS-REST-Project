package models;

public class Classe {

	private int id_classe;
	private String lable;
	private Section section;
	
	
	public Classe(int id_classe) {
		this.id_classe = id_classe;
	}
	
	public Classe(int id_classe, String lable) {
		this.id_classe = id_classe;
		this.lable = lable;
	}

	
	public Classe(int id_classe, String lable, Section section) {
		super();
		this.id_classe = id_classe;
		this.lable = lable;
		this.section = section;
	}

	public int getId_classe() {
		return id_classe;
	}

	public void setId_classe(int id_classe) {
		this.id_classe = id_classe;
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
	
	
	
}
