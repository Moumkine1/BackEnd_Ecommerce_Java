package app.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecommerce.dao.IAdminRepository;
import app.ecommerce.dao.IClientRepository;
import app.ecommerce.dao.ICompteRepository;
import app.ecommerce.dao.IProductRepository;
import app.ecommerce.model.Client;
import app.ecommerce.model.Compte;
import app.ecommerce.model.Product;
import app.ecommerce.model.views;
import app.ecommerce.util.JwtUtil;


@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientRessource {
	
	@Autowired
	IClientRepository daoClient;
	
	@Autowired
	ICompteRepository daoCompte;
	
	@Autowired
	IProductRepository daoProduit;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	
	
	@GetMapping("")
	@JsonView(views.ViewConnexion.class)
	public List<Client> findAllClient(){
		List<Client> clients = daoClient.findAll();
		return clients ;
	}
	@GetMapping("/{id}")
	@JsonView(views.ViewConnexion.class)
	public Client findByIdClient(@PathVariable Integer id) {
		Optional<Client> client = daoClient.findById(id);
		
		

		if (client.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return client.get();
	}
	
	

	
	@PostMapping("")
	public Client create(@RequestBody Client client) {
	    List<Client> clients = daoClient.findAll();
	    for (Client s : clients) {
	        if (client.getUsername().equals(s.getUsername())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom utilisateur existe déjà !");
	        }
	        if (client.getMail().equals(s.getMail())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'email existe déjà !");
	        }
	    }

	    String encodedPassword = passwordEncoder.encode(client.getPassword());
	    client.setPassword(encodedPassword);
	    client = daoClient.save(client);
	    daoCompte.setTokentById(client.getIdCompte(), jwtUtil.generateToken(client.getUsername()));

	    return client;
	}
	
	@PutMapping("/{id}")
	@JsonView(views.ViewConnexion.class)
	public Client updateClient(@PathVariable Integer id, @RequestBody Client client) {
		if (id != client.getIdCompte() || ! daoClient.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		client.setIdCompte(id);
		client = daoClient.save(client);

		return client;
	}
	
	
	@PutMapping("/{idClient}/affecterProduit/{idProduct}")
	public Client assignerProduit(@PathVariable Integer idClient, @PathVariable Integer idProduct){
		
		Set<Product> produits =null;
		Product produit = daoProduit.findById(idProduct).get();
		
		Client client= daoClient.findById(idClient).get();
		 
		produits= client.getListeProduits();
		
		produits.add(produit);
		client.setListeProduits(produits);
		
		return  daoClient.save(client);		
		
	
	}
	
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Integer id) {
		if (!daoClient.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	
		daoClient.deleteById(id);
	}
	
	
	
	
	
	
}