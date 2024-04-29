package models;

import java.sql.Timestamp;

public class User {
	
	private int id_user;
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	private String password;
	private String role;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int etud_id;
	private int prof_id;
	
	public User(int id_user) {
		this.id_user = id_user;
	}
	
	public User(int id_user, String nom, String prenom, String email, String tel, String password, String role) {
		this.id_user = id_user;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.role = role;
	}
	
	public User(String nom, String prenom, String email, String tel, String password, String role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.role = role;
	}
	
	public User(String nom, String prenom, String email, String tel, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
	}
	
	// for admin 
	public User(int id_user, String nom, String prenom, String email, String tel, String password) {
		this.id_user = id_user;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
	}
	
	// for admin 
	public User(int id_user, String nom, String prenom, String email, String tel, String password, String role,
			Timestamp created_at, Timestamp updated_at) {
		super();
		this.id_user = id_user;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.role = role;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	// for other users
	public User(int id_user, String nom, String prenom, String email, String tel, String password, String role,
			Timestamp created_at, Timestamp updated_at, int etud_id, int prof_id) {
		super();
		this.id_user = id_user;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.password = password;
		this.role = role;
		this.created_at = created_at;
		this.updated_at = updated_at;
		// if user is etudiant prof_id should be -1 to indicate null
		if (this.role.compareTo("etudiant")==0) {
			this.etud_id = etud_id;
			this.prof_id = -1;
		}
		// if user is prof etud_id should be -1 to indicate null
		else if(this.role.compareTo("prof")==0) {
			this.etud_id = -1;
			this.prof_id = prof_id;
		}

	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public int getEtud_id() {
		return etud_id;
	}

	public void setEtud_id(int etud_id) {
		this.etud_id = etud_id;
	}

	public int getProf_id() {
		return prof_id;
	}

	public void setProf_id(int prof_id) {
		this.prof_id = prof_id;
	}
		
	@Override
	public String toString() {
		return "User [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", password="
				+ password + ", role=" + role + "]";
	}
}
