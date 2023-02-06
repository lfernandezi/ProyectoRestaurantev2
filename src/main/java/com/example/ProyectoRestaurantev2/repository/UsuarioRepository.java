package com.example.ProyectoRestaurantev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProyectoRestaurantev2.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query(value = "{call sp_buscarUsuarioEmail(:email)}",
			nativeQuery = true)
			Usuario buscarxEmail(
			@Param("email") String email);

}
