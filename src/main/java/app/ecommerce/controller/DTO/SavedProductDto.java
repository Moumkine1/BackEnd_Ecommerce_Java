package app.ecommerce.controller.DTO;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import app.ecommerce.model.Compte;
import app.ecommerce.model.Product;
import app.ecommerce.model.views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;

public class SavedProductDto {
	
        private int idSavedProduct;
	
        
        private int idProduct;

       
	    private int quantity;
        
	    private int idCompte;
	    
		public int getIdSavedProduct() {
			return idSavedProduct;
		}

		public void setIdSavedProduct(int idSavedProduct) {
			this.idSavedProduct = idSavedProduct;
		}

		

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
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

		public SavedProductDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public SavedProductDto(int idSavedProduct, int idProduct, int quantity, int idCompte) {
			super();
			this.idSavedProduct = idSavedProduct;
			this.idProduct = idProduct;
			this.quantity = quantity;
			this.idCompte = idCompte;
		}

	
	    
	

}
