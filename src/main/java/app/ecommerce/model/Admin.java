package app.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;

@Entity
public class Admin extends Compte {
	@JsonView(views.ViewBase.class)
	private String nom;
	@JsonView(views.ViewBase.class)
	private String prenom;
	
	
	
	
	
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Admin(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}




	public Admin(String username, String password, String nom, String prenom) {
		super(username, password);
		this.nom = nom;
		this.prenom = prenom;
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
	
	
	

}
