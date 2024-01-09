package app.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;


@Entity
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
@DiscriminatorColumn(name="type_compte",columnDefinition = "ENUM('admin','client')")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "className")
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idCompte;
	
	String username;
	String password;
	
	
	
	
	
	public Compte(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Compte() {
		super();
		
	}
	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	

}
