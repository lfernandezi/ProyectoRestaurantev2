package edu.pe.idat.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pe.idat.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Transactional
	@Modifying
	@Query(value = "{call sp_MantRegistrarCliente(:dni, :nombre , :apellido,"
			+ ":email, :contraseña, :telefono ,:direccion, :estado)}", nativeQuery = true)
	void registrarCliente(@Param("dni") String xdni, @Param("nombre") String xnombre,
			@Param("apellido") String xapellido, @Param("email") String xemail,
			@Param("contraseña") String xcontrasenia, @Param("telefono") String xtelefono,
			@Param("direccion") String xdireccion, @Param("estado") String xestado);

	@Transactional
	@Modifying
	@Query(value = "{call sp_ActualizarCliente(:codcliente, :dni, :nombre , :apellido,"
			+ ":email, :contraseña, :telefono, :direccion, :estado)}", nativeQuery = true)
	void actualizarCliente(
			@Param("codcliente") int codcliente
			
			,@Param("dni") String xdni, @Param("nombre") String xnombre,
			@Param("apellido") String xapellido, @Param("email") String xemail,
			@Param("contraseña") String xcontrasenia, @Param("telefono") String xtelefono,
			@Param("direccion") String xdireccion, @Param("estado") String xestado);

	@Transactional
	@Modifying
	@Query(value = "{call sp_EliminarCliente(:email)}", 
	nativeQuery = true)
	void eliminarCliente(@Param("email")String email);
	

	@Query(value = "{call sp_buscarClientexdni(:dni)}",
			nativeQuery = true)
			List<Cliente> buscarporDni(@Param("dni")String dni);
	
	@Query(value = "{call sp_buscarClienteEmail(:email)}",
			nativeQuery = true)
			Cliente buscarClienteEmail(@Param("email")String email);
	
	
    @Query(value = "{call sp_buscarCliente(:codigo)}",
    		nativeQuery = true)
    Cliente buscarCliente(
    		@Param("codigo")Integer codigo);
    

    
	
	
	

}