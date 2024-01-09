package app.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecommerce.dao.IAdminRepository;
import app.ecommerce.dao.IClientRepository;
import app.ecommerce.dao.ICompteRepository;
import app.ecommerce.model.Client;
import app.ecommerce.model.views;


@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientRessource {
	
	@Autowired
	IClientRepository daoClient;
	
	@Autowired
	ICompteRepository daoCompte;
	
	
	
	@GetMapping("")
	@JsonView(views.ViewConnexion.class)
	public List<Client> findAllClient(){
		List<Client> clients = daoClient.findAll();
		return clients ;
	}
	@GetMapping("/{id}")
	@JsonView(views.ViewConnexion.class)
	public Optional<Client> findByIdClient(@PathVariable Integer id) {
		Optional<Client> client = daoClient.findById(id);
		
		

		if (client.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return client;
	}
	
	@PostMapping("")
	public Client create( @RequestBody Client client/*, BindingResult result*/) {
//		if (result.hasErrors()) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le client n'a pu être créé");
//		}
		client= daoCompte.save(client);
		
		return client;
		
	}
	
	@PutMapping("/{id}")
	@JsonView(views.ViewConnexion.class)
	public Client updateClient(@PathVariable Integer id, @RequestBody Client client) {
		if (id != client.getIdCompte() || !daoClient.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		client = daoClient.save(client);

		return client;
	}
	
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Integer id) {
		if (!daoClient.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	
		daoClient.deleteById(id);
	}
	
	
	
	
	
	
}