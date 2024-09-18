package app.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonView;


import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="saved_products")
public class SavedProduct {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(views.ViewBase.class)

	private int idSavedProduct;


	private int idProduct;


	private int quantity;

	private int  idCompte;
	
	
	






	public SavedProduct(int idSavedProduct, int idProduct, int quantity, int idCompte) {
		super();
		this.idSavedProduct = idSavedProduct;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.idCompte = idCompte;
	}




	public SavedProduct() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getIdSavedProduct() {
		return idSavedProduct;
	}


	public void setIdSavedProduct(int idSavedProduct) {
		this.idSavedProduct = idSavedProduct;
	}




	public int getIdProduct() {
		return idProduct;
	}




	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}




	public int getIdCompte() {
		return idCompte;
	}




	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}




	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

	

}
