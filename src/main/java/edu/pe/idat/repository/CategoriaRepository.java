package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	@Query(value= "{call sp_MostrarCategorias}",nativeQuery = true)
	List<Categoria> ListarCategoria();
	
	
	
	
	
	
		
	
	
	

}
