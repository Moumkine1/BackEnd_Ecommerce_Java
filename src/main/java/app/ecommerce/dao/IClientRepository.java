package app.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.ecommerce.model.Client;

public interface IClientRepository extends JpaRepository<Client, Integer>{

}
