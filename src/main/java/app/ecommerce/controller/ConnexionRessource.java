package app.ecommerce.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;*/
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.authentication.AuthenticationManager;

import app.ecommerce.controller.DTO.AuthDto;
import app.ecommerce.dao.ICompteRepository;
import app.ecommerce.model.Admin;
import app.ecommerce.model.Client;
import app.ecommerce.model.Compte;
import app.ecommerce.util.JwtUtil;


@RestController
@RequestMapping("/connexion")
@CrossOrigin("*")

public class ConnexionRessource {
	@Autowired
	ICompteRepository daoCompte;
	

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	

	

	@PostMapping("")
	
	public Compte generateToken(@RequestBody AuthDto authDto) throws Exception {
		
		 
		
	
		
		Compte compte = daoCompte.findByUsername(authDto.getUsername());

		    if (compte != null) {
		    	
		        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		        if (passwordEncoder.matches(authDto.getPassword(), compte.getPassword())) {
		            // Mot de passe valide, retournez le compte
		        	System.out.println("compte trouvéé : " + compte);
		        	System.out.println("Attempting authentication for: " + authDto.getUsername());
		    		authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
		    		 
		    		
		    		daoCompte.setTokentById(compte.getIdCompte(),jwtUtil.generateToken(authDto.getUsername()));
		    		/*compte.setToken(jwtUtil.generateToken(authDto.getUsername()));*/
		    		
		    		
		    		
		    		
		        	
		            return compte;
		        }
		    }
		
		
		 
		
		    System.out.println("Authentication successful for: " + authDto.getUsername());
		    
    			throw new Exception("invalid username or password");
    			
	   
	    
		
	  
	    
	}
	
}
