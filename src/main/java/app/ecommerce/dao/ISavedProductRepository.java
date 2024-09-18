package app.ecommerce.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.ecommerce.controller.DTO.SavedProductDto;
import app.ecommerce.model.SavedProduct;
import jakarta.transaction.Transactional;

@Transactional
public interface ISavedProductRepository extends JpaRepository<SavedProduct, Integer>{

	@Query("select c from SavedProduct c where c.idCompte = :idCompte")
	public SavedProduct[] findByIdCompte( @Param("idCompte") int idCompte);
	
	@Modifying
	@Query("Delete  from SavedProduct c where c.idProduct = :idProduct")
	public void deleteByIdProduct( @Param("idProduct") int idProduct);
	
}
