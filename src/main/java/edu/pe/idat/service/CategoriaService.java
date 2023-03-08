package edu.pe.idat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		List<Categoria> liscategoria= new ArrayList<Categoria>();
		liscategoria = categoriarepository.findAll();
		if(liscategoria.isEmpty()) {
			Categoria cat = new Categoria();
			cat.setCodcategoria(0);
			liscategoria.add(cat);
		}
		
		return liscategoria;
	}
	
	public Categoria buscarCategoria(Integer codigo) {
		Categoria cat = new Categoria();
		cat = categoriarepository.findById(codigo).orElse(null);
		if (Objects.nonNull(cat)) {
			return cat;
		}else {
			Categoria cate = new Categoria();
			cate.setCodcategoria(0);
			return  cate ;
		}
		
	}
	
	public void registrarCategoria(Categoria cat) {
		categoriarepository.save(cat);
	}
	
	public void eliminarCategoria(Integer codigo) {
		categoriarepository.deleteById(codigo);
	}
	
	public void actualizarCategoria(Categoria cate) {
		categoriarepository.saveAndFlush(cate);
	}
}
