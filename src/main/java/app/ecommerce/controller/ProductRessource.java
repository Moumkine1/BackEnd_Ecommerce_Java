package app.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecommerce.dao.IProductRepository;
import app.ecommerce.model.Product;
import app.ecommerce.model.views;



@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductRessource {
	
	
	@Autowired
	IProductRepository repository; 
	 
	
	@GetMapping("")
	@CrossOrigin(origins = "http://localhost:4200/admin")
	public List<Product> getAllProduct(){
	
	return repository.findAll();
		
	}
	
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200/admin")
	public Optional<Product> getProduct(@PathVariable int id ) {
		
		Optional<Product> product = repository.findById(id);
		
		if ( product.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);			
			
		}
		
		return product;
		
		
	}
	
	
	
	@PostMapping("")
	@CrossOrigin(origins = "http://localhost:4200/admin")
	public Product createProduct(@RequestBody Product product) {
	
		repository.save(product);
		
		return product ;
		
	}
	
    @PatchMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200/admin")
    public Product updateProduct(@PathVariable  int id, @RequestBody Product prod ) {
    	
    	if (!repository.existsById(id)) {
    		
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} 
    	
    	
    	Product findProd = repository.findById(id).get();
    	if (!(findProd.getCode().equals(prod.getCode())) && prod.getCode()!=null) {
    		findProd.setCode(prod.getCode());
    	}
    	
    	
    	if ( ! (findProd.getName().equals(prod.getName())) && prod.getName()!=null) {
    		findProd .setName(prod.getName());
    	}
    	
    	if ( ! (findProd.getDescription().equals(prod.getDescription())) && prod.getDescription()!=null) {
    		findProd .setDescription(prod.getDescription());
    	}
    	if ((findProd.getPrice()!= prod.getPrice() ) && (prod.getPrice()!=0.0)) {
			
    		findProd .setPrice(prod.getPrice());
    	}
    	if (findProd.getQuantity()!= prod.getQuantity() && prod.getQuantity()!= 0 ) {
    		findProd .setQuantity(prod.getQuantity());
    	}
    	if ( ! (findProd.getInventoryStatus().equals(prod.getInventoryStatus())) && prod.getInventoryStatus()!=null) {
    		findProd .setInventoryStatus(prod.getInventoryStatus());
    	}
    	if ( ! (findProd.getCategory().equals(prod.getCategory())) &&  prod.getCategory()!=null) {
    		findProd .setCategory(prod.getCategory());
    	}
    	if ( ! (findProd.getImage().equals(prod.getImage())) && prod.getImage()!=null) {
    		findProd .setImage(prod.getImage());
    	}
    	
    	if (findProd.getRating()!= prod.getRating() && prod.getRating()!= 0 ) {
    		findProd .setRating(prod.getRating());
    	}
    	
		return  repository.save(findProd);

	
	}
    
    

	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200/admin")
	public void deleteProduct(@PathVariable int id ) {
		
		
		if (! repository.existsById(id) ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
       repository.deleteById(id);
	}

}
