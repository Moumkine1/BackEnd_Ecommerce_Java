package app.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecommerce.controller.DTO.SavedProductDto;
import app.ecommerce.dao.ICompteRepository;
import app.ecommerce.dao.IProductRepository;
import app.ecommerce.dao.ISavedProductRepository;
import app.ecommerce.mapper.MapperSavedProduct;
import app.ecommerce.model.Client;
import app.ecommerce.model.Compte;
import app.ecommerce.model.Product;
import app.ecommerce.model.SavedProduct;
import app.ecommerce.model.views;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/panier")
@CrossOrigin(origins = "http://localhost:4200")
public class SavedProductRessource {
	
	@Autowired
     ISavedProductRepository daoSavedProduct;
	
	@Autowired
	IProductRepository daoProduct;
	
	@Autowired
	MapperSavedProduct Mapper;
	
	@Autowired
    ICompteRepository daoCompte;
	
	
	@GetMapping("")
	public List<SavedProduct> getSavedProduct(){
		
		return daoSavedProduct.findAll();
		
	}
	
	@GetMapping("/{idCompte}")
	public SavedProduct[] getSavedProductByIdCompte(@PathVariable int idCompte){
		
		return daoSavedProduct.findByIdCompte(idCompte);
		
	}
	
	
	
	
	
	@PostMapping("")
   
    
    public void creatSavedProduct( @RequestBody ArrayList<SavedProductDto> savedProduct) {
        SavedProduct resultat;
        

        	for(SavedProductDto s : savedProduct) {
        	System.out.println("L'ID du product"+s.getIdCompte());
               
            
            if (s.getIdProduct() != 0) {
                Product product = daoProduct.findById(s.getIdProduct())
                    .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'ID spécifié: " + s.getIdProduct()));
    
                SavedProduct[] recup = daoSavedProduct.findByIdCompte(s.getIdCompte());
    
                for (SavedProduct existant : recup) {
                    if (s.getIdProduct() == existant.getIdProduct()) {
                        daoSavedProduct.deleteByIdProduct(existant.getIdProduct());
                    }
                }
    
               
               resultat=Mapper.mapper(s);
                daoSavedProduct.save(resultat);
              
            }
            }
        
	}
	}
    
	


