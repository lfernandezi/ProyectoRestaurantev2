package edu.pe.idat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

import edu.pe.idat.model.Empleado;
import edu.pe.idat.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {	
	
	/*@Transactional
	@Modifying
	@Query(value="{call sp_RegistrarProducto(:nombre, :descripcion, :categoria, :precio)}",
	nativeQuery = true)											
	void registrarProducto (@Param("nombre")String nombre,
			              @Param("descripcion")String descripcion,
						  @Param("categoria")String categoria,
						  @Param("precio")Double precio);					  	
	
	
	@Transactional
	@Modifying
	@Query(value="{call sp_ActualizarProducto(:codproducto, :nombre, :descripcion, :categoria, :precio)}",
			nativeQuery=true)
	void actualizarProducto (@Param("codproducto")Integer codproducto,
			                 @Param("nombre")String nombre,
			                 @Param("descripcion")String descripcion,
							 @Param("categoria")String categoria,
							 @Param("precio")Double precio);			 
	
	
	/*@Transactional
	@Modifying
	@Query(value="{call sp_EliminarProducto(:codproducto)}",
			nativeQuery=true)
	void eliminarProducto (@Param("codproducto")int codproducto);
	
	
	@Query(value="{call sp_ListarProductoxCategorias(:categoria)}",
			nativeQuery=true)
	List<Producto> listarProductoxCategoria (@Param("categoria")String categoria);*/
	
	@Query(value="{call sp_ListarProductoxCategorias(:codcategoria)}",
			nativeQuery=true)
	List<Producto> listarProductoxCategoria (@Param("codcategoria")int codcategoria);
	
	/*@Query(value = "{call sp_buscarProducto(:codigo)}",
    		nativeQuery = true)
    Producto buscarProducto(@Param("codigo")Integer codigo);*/
	
}
