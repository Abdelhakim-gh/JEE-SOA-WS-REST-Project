package models;

import java.util.ArrayList;

public class Prof {

	private User user;
	private int id_prof;
	private float salaire;
	private int payement;
	private ArrayList<Matiere> matieres;
	private ArrayList<Section> sections;
	
	public Prof(int id_prof, float salaire, int payement) {
		this.id_prof = id_prof;
		this.salaire = salaire;
		this.payement = payement;
	}
	
	public Prof(User user, int id_prof, float salaire, int payement, ArrayList<Matiere> matieres,
			ArrayList<Section> sections) {
		super();
		this.user = user;
		this.id_prof = id_prof;
		this.salaire = salaire;
		this.payement = payement;
		this.matieres = matieres;
		this.sections = sections;
	}

	public ArrayList<Section> getSections() {
		return sections;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	public Prof(User user, int id_prof, float salaire, int payement) {
		this.user = user;
		this.id_prof = id_prof;
		this.salaire = salaire;
		this.payement = payement;
	}
	
	public Prof(User user, int id_prof, float salaire, int payement, ArrayList<Matiere> matieres) {
		super();
		this.user = user;
		this.id_prof = id_prof;
		this.salaire = salaire;
		this.payement = payement;
		this.matieres = matieres;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId_prof() {
		return id_prof;
	}
	public void setId_prof(int id_prof) {
		this.id_prof = id_prof;
	}
	public float getSalaire() {
		return salaire;
	}
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}
	public int getPayement() {
		return payement;
	}
	public void setPayement(int payement) {
		this.payement = payement;
	}
	public ArrayList<Matiere> getMatieres() {
		return matieres;
	}
	public void setMatieres(ArrayList<Matiere> matieres) {
		this.matieres = matieres;
	}
	@Override
	public String toString() {
		return "Prof [user=" + user + ", id_prof=" + id_prof + ", salaire=" + salaire + ", payement=" + payement
				+ ", matieres=" + matieres + ", sections=" + sections + "]";
	}
	
}
