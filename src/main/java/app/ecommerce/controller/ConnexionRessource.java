package app.ecommerce.controller;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.ecommerce.controller.DTO.AuthDto;
import app.ecommerce.dao.ICompteRepository;
import app.ecommerce.model.Compte;

@RestController
@RequestMapping("/connexion")
@CrossOrigin("*")
public class ConnexionRessource {
	
	ICompteRepository daoCompte;
	
	@GetMapping("")
	public Compte findCompte(@RequestBody AuthDto authDto) {
		
	        Compte compte = daoCompte.findByUserNameAndPassword(authDto.getUsername(), authDto.getPassword());	
	        
	        if(compte==null) {
	        	
	        	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        }
	        
	        return compte;
	        
	        
	
	}

}
