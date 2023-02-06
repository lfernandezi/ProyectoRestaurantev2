package edu.pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import edu.pe.idat.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query(value = "{call sp_buscarUsuario(:email)}",
			nativeQuery = true)
			Usuario buscarUsuarioxEmail(
			@Param("email") String email);
}
