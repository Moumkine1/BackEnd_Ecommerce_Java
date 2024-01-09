package app.alten.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.alten.ecommerce.model.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
