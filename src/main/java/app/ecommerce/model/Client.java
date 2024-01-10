package app.ecommerce.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	String mail;
	
	@ManyToOne
	//@JsonView(views.ViewProduitClient.class)
	@JsonView(views.ViewBase.class)
	@JoinColumn(name="id_produit")
    Product produitPrincipale;
 	
	public Client(int idCompte, String username, String password, String nom, String prenom, String telephone,
			String mail, Product produitPrincipale) {
		super(idCompte, username, password);
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.produitPrincipale = produitPrincipale;
	}

	
	
	public Client(int idCompte, String username, String password) {
		super(idCompte, username, password);
		// TODO Auto-generated constructor stub
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	


}
