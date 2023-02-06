package edu.pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>  {
	@Query(value = "{call sp_MostrarUsuario(:email)}",
			nativeQuery = true)
			Login mostrarUsuario(
			@Param("email") String email);
}
