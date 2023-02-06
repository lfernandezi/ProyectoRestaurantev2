package edu.pe.idat.repository;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {	
	/*
	@Transactional
	@Modifying
	@Query(value="{call sp_InsertaEmpleado(:nombre, :apellido, :dni, :cargo ,:contrasena, :fecha_ingreso, :area)}",
			nativeQuery = true)											
	void InsertaEmpleado (@Param("nombre")String nombre,
						  @Param("apellido")String apellido,
						  @Param("dni")String dni,
						  @Param("cargo")Integer cargo,
						  @Param("contrasena")String contrasena,
						  @Param("fecha_ingreso")Date fecha_ingreso,
	 					  @Param("area")Integer area);
	
	
	@Transactional
	@Modifying
	@Query(value="{call sp_ActualizarEmpleado(:codempleado, :nombre, :apellido, :dni, :cargo, :contrasena, :fecha_ingreso, :area)}",
			nativeQuery=true)
	void actualizarEmpleado (@Param("codempleado")Integer codempleado,
							 @Param("nombre")String nombre,
							 @Param("apellido")String apellido,
							 @Param("dni")String dni, 
							 @Param("cargo")String cargo,
							 @Param("contrasena")String contrasena,
							 @Param("fecha_ingreso")Date fecha_ingreso,
							 @Param("area")String area);
	
	
	@Transactional
	@Modifying
	@Query(value="{call sp_EliminarEmpleado(:codempleado)}",
			nativeQuery=true)
	void eliminarEmpleado (@Param("codempleado")int codempleado);
	
	
	@Query(value = "{call sp_buscarEmpleado(:codigo)}",
    		nativeQuery = true)
    Empleado buscarEmpleado(@Param("codigo")Integer codigo);
	*/
	
	@Query(value = "{call sp_buscarEmpleadoxdni(:dni)}",
    		nativeQuery = true)
    Empleado buscarEmpleado(@Param("dni")String dni);
	
	
	
}

