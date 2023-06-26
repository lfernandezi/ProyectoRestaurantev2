package edu.pe.idat.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.Pedido;

@Repository  
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
	
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_IngresarPedido(:direccion, :referencia, "
	+ ":ubicacion, :monto, :estado, :motivo, :codcliente)}", nativeQuery = true)
	void ingresarPedido( @Param("direccion") String direccion,  @Param("referencia") String referencia, @Param("ubicacion") String ubicacion,
			 @Param("monto") Double monto, @Param("estado") String estado, @Param("motivo") String motivo,
			 @Param("codcliente") Integer codcliente);
			 
	
	@Transactional
	@Modifying
	@Query(value="{call sp_ActualizarEstadoPedido(:codpedido, :estado)}",
			nativeQuery=true)
	void actualizarEstadoPedido (@Param("codpedido")Integer codpedido,
			                 @Param("estado")String estado);	
	
	
	@Query(value = "{call sp_ListarPedidoxEstado(:estado)}",
			nativeQuery = true)
	public	List<Pedido> buscarPedidoporEstado(@Param("estado")String estado);
	
	@Query(value = "{call sp_ListarPedidoxCliente(:codcliente)}",
			nativeQuery = true)
	public	List<Pedido> buscarPedidoporCliente(
			@Param("codcliente")int codcliente);
	
	@Query(value = "{call sp_ListarUltimoPedidoxCliente(:codcliente)}",
			nativeQuery = true)
	public List<Pedido> buscarUltimoPedido(@Param("codcliente")int codcliente);
		
}

