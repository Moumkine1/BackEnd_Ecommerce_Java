package app.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.ecommerce.model.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {

}
