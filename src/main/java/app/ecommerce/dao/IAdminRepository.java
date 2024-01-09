package app.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import app.ecommerce.model.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
