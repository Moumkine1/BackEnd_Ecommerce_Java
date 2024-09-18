package app.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecommerce.dao.IProductRepository;
import app.ecommerce.model.ListeProduits;
import app.ecommerce.model.Product;
import app.ecommerce.model.views;



@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductRessource {
	
	
	
	@Autowired
	IProductRepository repository; 
	@Autowired 
	IProductRepository daoProduit;
	 
	
	@GetMapping("")
	@PreAuthorize("permitAll()")
	
	public ArrayList<Product> getAllProduct(){
	 

	
	
		return (ArrayList<Product>) repository.findAll();
	}
	
	@GetMapping("/{id}")
	
	public Product getProduct(@PathVariable int id ) {
		
		Optional<Product> product = repository.findById(id);
		
		if ( product.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);			
			
		}
		
		return product.get();
		
		
	}
	
	
	
	@PostMapping("")
	public Product createProduct(@RequestBody Product product) {
	
		repository.save(product);
		
		return product ;
		
	}
	
	@PutMapping("/{id}")
	public Product update(@PathVariable int id, @RequestBody Product prodd) {
    
		System.out.println("Voici l'id "+prodd.getIdProduct());
	    if ( !daoProduit.existsById(id)) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	    }

	    prodd = daoProduit.save(prodd);

	    return prodd;
	}

	
    @PatchMapping("/{id}")
   
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
    	if ((findProd.getPrice()!= prod.getPrice() ) && (prod.getPrice()!= null)) {
			
    		findProd .setPrice(prod.getPrice());
    	}
    	if (findProd.getQuantit()!= prod.getQuantit() && prod.getQuantit()!= null) {
    		findProd .setQuantit(prod.getQuantit());
    	}
    	if ( ! (findProd.getInventoryStatus().equals(prod.getInventoryStatus())) && prod.getInventoryStatus()!=null) {
    		findProd .setInventoryStatus(prod.getInventoryStatus());
    	}
    	if ( ! (findProd.getCategory().equals(prod.getCategory())) &&  prod.getCategory()!=null) {
    		findProd .setCategory(prod.getCategory());
    	}
    	if ( ! (findProd.getListeImage().equals(prod.getListeImage())) && prod.getListeImage()!=null) {
    		
    		LinkedHashSet<String> maListe =  new LinkedHashSet<String> (findProd.getListeImage());
    		
    		
    		maListe.addAll(prod.getListeImage());
    		findProd .setListeImage(maListe);
    		daoProduit.setListeImage(findProd.getIdProduct(),maListe);
    	}
    	
    	if (findProd.getRating()!= prod.getRating() && prod.getRating()!= null ) {
    		findProd .setRating(prod.getRating());
    	}
    	
		return  repository.save(findProd);

	
	}
    
    

	@DeleteMapping("/{id}")
	
	public void deleteProduct(@PathVariable int id ) {
		
		
		if (! repository.existsById(id) ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
       repository.deleteById(id);
	}

}
