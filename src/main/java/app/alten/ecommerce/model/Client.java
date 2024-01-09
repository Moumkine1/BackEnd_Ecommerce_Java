package app.alten.ecommerce.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;

@Entity
public class Client extends Compte {
	
	@JsonView(views.ViewBase.class)
	String nom;
	@JsonView(views.ViewBase.class)
	String prenom;
	@JsonView(views.ViewBase.class)
	String telephone;
	@JsonView(views.ViewBase.class)
	String email;
	
	@ManyToMany
	@JsonView(views.ViewProduitClient.class)
	@JoinTable(name="produit_client",
	           joinColumns=@JoinColumn(name="idProduct"), 
	           inverseJoinColumns=@JoinColumn(name="idCompte"))
    List<Product> listeProduits ;
 	
	public Client(String username, String password, String nom, String prenom, String telephone, String email) {
		super(username, password);
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	


}
