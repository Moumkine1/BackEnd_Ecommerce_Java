package app.ecommerce.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;

@Entity

public class Client extends Compte {
	
	@JsonView(views.ViewBase.class)
	private String nom;
	@JsonView(views.ViewBase.class)
	private String prenom;
	@JsonView(views.ViewBase.class)
	private String telephone;
	@JsonView(views.ViewBase.class)
	private  String mail;
	@Column(nullable = false)
	@JsonView(views.ViewBase.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate naissance;
	
	@ManyToMany
	@JsonView(views.ViewBase.class)
	@JoinTable(name="client_produit",
	joinColumns = @JoinColumn(name="client_id"),
	inverseJoinColumns = @JoinColumn(name="product_id")
	
			)
	private Set<Product> listeProduits=new HashSet<Product>();
	
	
	
 	
	

	
	

	

	



	public Client(int idCompte, String username, String password, String nom, String prenom, String telephone,
			String mail, LocalDate naissance, Set<Product> listeProduits) {
		super(idCompte, username, password);
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.naissance = naissance;
		this.listeProduits = listeProduits;
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
	
	
	public LocalDate getNaissance() {
		return naissance;
	}

	public void setNaissance(LocalDate naissance) {
		this.naissance = naissance;
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

	public Set<Product> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(Set<Product> listeProduits) {
		this.listeProduits = listeProduits;
	}

	
	

	
	
	


}
