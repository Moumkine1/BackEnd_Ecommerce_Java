package app.ecommerce.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.ecommerce.model.Product;
import jakarta.transaction.Transactional;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.listeImage = :listeImage WHERE p.idProduct = :idProduit")
    void setListeImage(@Param("idProduit") int idProduit, @Param("listeImage") LinkedHashSet<String> listeImage);
}

