package app.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.ecommerce.model.Compte;
import jakarta.transaction.Transactional;

public interface ICompteRepository extends JpaRepository<Compte, Integer> {
	
	@Query("select c from Compte c where c.username = :username and c.password = :password")
	public  Compte findByUserNameAndPassword(@Param("username") String username ,@Param("password") String password);
	
	@Query("select c from Compte c where c.username = :username")
	public Compte findByUsername( @Param("username") String username);
	
	@Transactional
	@Modifying
	@Query("UPDATE Compte c SET c.token =:token  WHERE c.id =:idCompte")
	public void setTokentById(@Param("idCompte") int i, @Param("token") String token);

}
