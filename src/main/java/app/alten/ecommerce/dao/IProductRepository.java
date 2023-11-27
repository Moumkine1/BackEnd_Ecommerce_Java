package app.alten.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import app.alten.ecommerce.model.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {

}
