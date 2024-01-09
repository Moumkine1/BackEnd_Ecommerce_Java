package app.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.ecommerce.controller.DTO.SubscribeDto;
import app.ecommerce.dao.IClientRepository;
import app.ecommerce.dao.ICompteRepository;
import app.ecommerce.model.Client;
import app.ecommerce.model.Compte;

@RestController
@RequestMapping("/subscribe")
@CrossOrigin("*")
public class SubscribeRessource {
	
	
	ICompteRepository daoCompte;
	
	IClientRepository daoClient;
	
	public Client creerClient(@RequestBody SubscribeDto subscribe) {
		
	
		List<Client> clients = daoClient.findAll();
		
		
		
		for ( Client s : clients) {
			
			
			if (subscribe.getUsername().equals(s.getUsername())) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom utilisateur existe déjà ");
			}
				
		
		}
		
		
		
		Client newClient = new Client();
		
		newClient.setUsername(subscribe.getUsername());
		
		newClient.setPassword(subscribe.getPassword());
		newClient.setNom(subscribe.getNom());
		newClient.setPrenom(subscribe.getPrenom());
		newClient.setMail(subscribe.getMail());
		newClient.setTelephone(subscribe.getTelephone());
		
		
		
		return daoCompte.save(newClient);
		
		
		

}
}
