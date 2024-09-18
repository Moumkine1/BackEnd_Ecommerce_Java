package app.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecommerce.dao.IAdminRepository;
import app.ecommerce.dao.IClientRepository;
import app.ecommerce.dao.ICompteRepository;
import app.ecommerce.dao.IProductRepository;
import app.ecommerce.model.Admin;
import app.ecommerce.model.Client;
import app.ecommerce.model.views;
import app.ecommerce.util.JwtUtil;
@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminRessource {
	

		
		@Autowired
		IAdminRepository daoAdmin;
		
		@Autowired
		ICompteRepository daoCompte;
		
		
		
		@Autowired
		private JwtUtil jwtUtil;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		
		
		@GetMapping("")
		@JsonView(views.ViewConnexion.class)
		public List<Admin> findAllClient(){
			List<Admin> admins = daoAdmin.findAll();
			return admins ;
		}
		@GetMapping("/{id}")
		@JsonView(views.ViewConnexion.class)
		public Admin findByIdClient(@PathVariable Integer id) {
			Optional<Admin> admin = daoAdmin.findById(id);
			
			

			if (admin.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}

			return admin.get();
		}
		
		

		
		@PostMapping("")
		@CrossOrigin(origins = "http://localhost:4200")
		public Admin create( @RequestBody Admin admin/*, BindingResult result*/) {

			
	List<Admin> admins = daoAdmin.findAll();
			
			
			
			for ( Admin s : admins) {
				
				
				if (admin.getUsername().equals(s.getUsername()))  {
					
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom utilisateur existe déjà !");
				}
					
			
			}
			
			
			String encodedPassword = passwordEncoder.encode(admin.getPassword());
		    admin.setPassword(encodedPassword);
		    admin= daoAdmin.save(admin);
		
		    daoCompte.setTokentById(admin.getIdCompte(),jwtUtil.generateToken(admin.getUsername()));
			
			
			return admin;
			
		}
		
		@PutMapping("/{id}")
		@JsonView(views.ViewConnexion.class)
		public Admin updateClient(@PathVariable Integer id, @RequestBody Admin admin) {
			if (id != admin.getIdCompte() || ! daoAdmin.existsById(id)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}

			admin = daoAdmin.save(admin);

			return admin;
		}
		
}
