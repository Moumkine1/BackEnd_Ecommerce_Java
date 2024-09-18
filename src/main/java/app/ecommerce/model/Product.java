package app.ecommerce.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
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

public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

  @Column(nullable=false)
	@JsonView(views.ViewBase.class)
	private int idProduct;
	@JsonView(views.ViewBase.class)
	private String code;
	@JsonView(views.ViewBase.class)
	private String name;
	@JsonView(views.ViewBase.class)
	private String description;
	@JsonView(views.ViewBase.class)
	private Float price;
	@JsonView(views.ViewBase.class)
	private Integer quantit;
	
	@Column(name="inventoryStatus")
	@JsonView(views.ViewBase.class)
	private String inventoryStatus;
	@JsonView(views.ViewBase.class)
	private String category;
	@JsonView(views.ViewBase.class)
	@Column(nullable=false, columnDefinition="VARCHAR(255)")
	private LinkedHashSet<String> listeImage;
	@JsonView(views.ViewBase.class)
	private Integer rating;
	
	@ManyToMany(mappedBy="listeProduits")
	@JsonIgnore

	private Set<Client> listeClients = new HashSet<Client>();
	
	
	
	
	
	
	
	
	





	


	public Product(int idProduct, String code, String name, String description, Float price, Integer quantity,
			String inventoryStatus, String category, LinkedHashSet<String> listeImage, Integer rating,
			Set<Client> listeClients) {
		super();
		this.idProduct = idProduct;
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantit = quantit;
		this.inventoryStatus = inventoryStatus;
		this.category = category;
		this.listeImage = listeImage;
		this.rating = rating;
		this.listeClients = listeClients;
		
	}

	public Integer getRating() {
		return rating;
	}
	
	public Product() {
		super();
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getQuantit() {
		return quantit;
	}
	public void setQuantit(Integer quantit) {
		this.quantit = quantit;
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

	public LinkedHashSet<String> getListeImage() {
		return listeImage;
	}

	public void setListeImage(LinkedHashSet<String> listeImage) {
		this.listeImage = listeImage;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Set<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(Set<Client> listeClients) {
		this.listeClients = listeClients;
	}

	

	

	

	

        
        
	

	
	
	
	
}
