package app.ecommerce.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(views.ViewBase.class)
	int idProduct;
	@JsonView(views.ViewBase.class)
	String code;
	@JsonView(views.ViewBase.class)
	String name;
	@JsonView(views.ViewBase.class)
	String description;
	@JsonView(views.ViewBase.class)
	float price;
	@JsonView(views.ViewBase.class)
	int quantity;
	
	@Column(name="inventoryStatus")
	@JsonView(views.ViewBase.class)
	String inventoryStatus;
	@JsonView(views.ViewBase.class)
	String category;
	@JsonView(views.ViewBase.class)
	String image;
	@JsonView(views.ViewBase.class)
	int rating;
	
	@OneToMany(mappedBy="produitPrincipale")
	//@JsonView(views.ViewBase.class)
	@JsonIgnore
	 List<Client>  clients;
	
	
	
	
	
	public int getRating() {
		return rating;
	}
	
	public Product() {
		super();
	}
	
	public Product(int idProduct, String code, String name, String description, float price, int quantity,
			String inventoryStatus, String category, String image, int rating, List<Client> clients) {
		super();
		this.idProduct = idProduct;
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.inventoryStatus = inventoryStatus;
		this.category = category;
		this.image = image;
		this.rating = rating;
		this.clients = clients;
	}

	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int id) {
		this.idProduct = idProduct;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getInventoryStatus() {
		return inventoryStatus;
	}
	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

        
        
	

	
	
	
	
}
