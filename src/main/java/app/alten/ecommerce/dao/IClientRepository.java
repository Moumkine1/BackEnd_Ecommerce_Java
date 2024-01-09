package app.alten.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.alten.ecommerce.model.Client;

public interface IClientRepository extends JpaRepository<Client, Integer>{

}
