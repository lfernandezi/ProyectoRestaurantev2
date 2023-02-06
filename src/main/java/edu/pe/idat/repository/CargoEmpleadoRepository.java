package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.CargoEmpleado;

@Repository
public interface CargoEmpleadoRepository extends JpaRepository<CargoEmpleado,Integer>{	
	
	/*@Query(value= "{call sp_MostrarCargoEmpleado}",nativeQuery = true)
	List<CargoEmpleado> ListarCargoEmpleado();
*/
}
