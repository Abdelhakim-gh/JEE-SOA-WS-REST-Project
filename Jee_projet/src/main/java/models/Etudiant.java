package models;

import java.util.ArrayList;

public class Etudiant  {
	
	private User user;
	private int id_etud;
	private String cne;
	private float prix;
	private int payement;
	private Classe classe;
	private Section section;
	private ArrayList<Matiere> matieres;
	
	public Etudiant(int id_etud, String cne, float prix, int payement) {
		this.id_etud = id_etud;
		this.cne = cne;
		this.prix = prix;
		this.payement = payement;
	}
	
	public Etudiant(User user, int id_etud, String cne, Section section) {
		this.user = user;
		this.id_etud = id_etud;
		this.cne = cne;
		this.section = section;
	}
	
	public Etudiant(User user, int id_etud, String cne, float prix, int payement, Classe classe, Section section) {
		super();
		this.user = user;
		this.id_etud = id_etud;
		this.cne = cne;
		this.prix = prix;
		this.payement = payement;
		this.classe = classe;
		this.section = section;
	}
	
	public Etudiant(User user, int id_etud, String cne, float prix, int payement, Classe classe, Section section,
			ArrayList<Matiere> matieres) {
		super();
		this.user = user;
		this.id_etud = id_etud;
		this.cne = cne;
		this.prix = prix;
		this.payement = payement;
		this.classe = classe;
		this.section = section;
		this.matieres = matieres;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId_etud() {
		return id_etud;
	}
	public void setId_etud(int id_etud) {
		this.id_etud = id_etud;
	}
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getPayement() {
		return payement;
	}
	public void setPayement(int payement) {
		this.payement = payement;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public ArrayList<Matiere> getMatieres() {
		return matieres;
	}
	public void setMatieres(ArrayList<Matiere> matieres) {
		this.matieres = matieres;
	}
	@Override
	public String toString() {
		return "Etudiant [user=" + user + ", id_etud=" + id_etud + ", cne=" + cne + ", section=" + section + "]";
	}
	
}
