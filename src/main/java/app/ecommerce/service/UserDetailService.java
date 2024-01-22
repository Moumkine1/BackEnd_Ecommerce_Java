package app.ecommerce.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.ecommerce.dao.ICompteRepository;
import app.ecommerce.model.Compte;
@Service
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private ICompteRepository daoCompte;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Compte compte = daoCompte.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(compte.getUsername(),compte.getPassword(),new ArrayList<>());
	}
	
	



}

