package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.AreaEmpleado;

@Repository
public interface AreaEmpleadoRepository extends JpaRepository<AreaEmpleado, Integer>{
	
	/*@Query(value= "{call sp_MostrarAreaEmpleado}",nativeQuery = true)
	List<AreaEmpleado> ListarAreaEmpleado();
*/
}
