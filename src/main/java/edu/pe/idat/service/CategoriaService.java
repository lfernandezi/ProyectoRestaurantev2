package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Categoria;
import edu.pe.idat.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriarepository;
	
	/*public List<CategoriaForm> ListarCategoria(){
		return categoriarepository.ListarCategoria();
	}*/

	public List<Categoria> ListarCategoria(){
		return categoriarepository.findAll();
	}
	
	public Categoria buscarCategoria(Integer codigo) {
		return categoriarepository.findById(codigo).orElse(null)  ;
	}
}
