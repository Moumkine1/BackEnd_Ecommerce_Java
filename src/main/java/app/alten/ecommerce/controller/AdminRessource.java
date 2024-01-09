package app.alten.ecommerce.controller;

import java.util.List;
import java.util.Optional;

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

import com.fasterxml.jackson.annotation.JsonView;

import app.alten.ecommerce.dao.IAdminRepository;
import app.alten.ecommerce.dao.ICompteRepository;
import app.alten.ecommerce.model.Admin;
import app.alten.ecommerce.model.views;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminRessource {
	
	
	@Autowired
	IAdminRepository daoAdmin;
	
	@Autowired
	ICompteRepository daoCompte;
	
	
	
	@GetMapping("")
	@JsonView(views.ViewConnexion.class)
	public List<Admin> findAllAdmint(){
		List<Admin> admins = daoAdmin.findAll();
		return admins ;
	}
	@GetMapping("/{id}")
	@JsonView(views.ViewConnexion.class)
	public Optional<Admin> findByIdAdmin(@PathVariable Integer id) {
		Optional<Admin> client = daoAdmin.findById(id);
		
		

		if (client.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return client;
	}
	
	@PostMapping("")
	public Admin create( @RequestBody Admin admin/*, BindingResult result*/) {
//		if (result.hasErrors()) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le client n'a pu être créé");
//		}
		admin= daoCompte.save(admin);
		
		return admin;
		
	}
	
	@PutMapping("/{id}")
	@JsonView(views.ViewConnexion.class)
	public Admin updateAdmint(@PathVariable Integer id, @RequestBody Admin admin) {
		if (id != admin.getIdCompte() || !daoAdmin.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		admin = daoAdmin.save(admin);

		return admin;
	}
	
	@DeleteMapping("/{id}")
	public void deleteAdmin(@PathVariable Integer id) {
		if (!daoAdmin.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	
		daoAdmin.deleteById(id);
	}
	
	

}
