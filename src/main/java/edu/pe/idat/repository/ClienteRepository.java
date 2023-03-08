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
	@Query(value = "{call sp_ActualizarCliente(:idcliente, :dni,:nombre, :apellido, :email, :contrasenia, :telefono, :direccion, :estado)}", 
	nativeQuery = true)
	void actualizarCliente(@Param("idcliente")Integer idcliente, 
			               @Param("dni") String dni,
			               @Param("nombre") String nombre,
			               @Param("apellido") String apellido,
	                       @Param("email")String email, 
                           @Param("contrasenia") String contrasenia,
                           @Param("telefono") String telefono,
                           @Param("direccion") String direccion,
	                       
                           @Param("estado") String estado);

	@Transactional
	@Modifying
	@Query(value = "{call sp_EliminarCliente(:idcliente)}", 
	nativeQuery = true)
	void eliminarCliente(@Param("idcliente")Integer idcliente);
	
	
	

	@Query(value = "{call sp_buscarClientexdni(:dni)}",
			nativeQuery = true)
			List<Cliente> buscarporDni(@Param("dni")String dni);
	
	
    @Query(value = "{call sp_buscarCliente(:codigo)}",
    		nativeQuery = true)
    Cliente buscarCliente(@Param("codigo")Integer codigo);
    
    
	
	
	

}