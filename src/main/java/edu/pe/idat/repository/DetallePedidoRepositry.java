package edu.pe.idat.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pe.idat.model.DetallePedido;

public interface DetallePedidoRepositry extends JpaRepository<DetallePedido, Integer> {

	@Transactional
	@Modifying
	@Query(value = "{call sp_IngresarDetallePedido(:codproducto, :cantidad,:nombreproducto, :precio,:subtotal)}", nativeQuery = true)
	void ingresarDetallePedido(@Param("codproducto") Integer codproducto, @Param("cantidad") int cantidad,
			@Param("nombreproducto") String nombreproducto, @Param("precio") Double precio, @Param("subtotal") Double subtotal);

	@Query(value = "{call sp_VerDetallePedido(:codpedido)}", nativeQuery = true)
	List<DetallePedido> verDetallePedido(@Param("codpedido") int codpedido);

}
