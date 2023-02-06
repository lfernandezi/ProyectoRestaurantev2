package edu.pe.idat.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.repository.CategoriaRepository;
import edu.pe.idat.repository.ProductoRepository;
import edu.pe.idat.model.Empleado;
import edu.pe.idat.model.Producto;
import edu.pe.idat.model.ProductoResponse;

@Service
public class ProductoService {	
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	CategoriaRepository cateRepository;
	
	public List<ProductoResponse> listarProducto(){
		
		List<Producto> lisprod= productoRepository.findAll();
		List<ProductoResponse> nvalistaprod=new ArrayList<ProductoResponse>();
		
		for (Producto producto:lisprod) {
			ProductoResponse p=new ProductoResponse();
			p.setCodproducto(producto.getCodproducto());
			p.setNombre(producto.getNombre());
			p.setDescripcion(producto.getDescripcion());
			p.setPrecio(producto.getPrecio());
			p.setCodcategoria(cateRepository.findById(producto.getCodcategoria()).orElse(null));
			
			nvalistaprod.add(p);
		}
		
		return nvalistaprod;
	}	

	public void registrarProducto(Producto producto) {
		if(producto.getCodproducto() == 0) {
			
			//productoRepository.registrarProducto(producto.getNombre(),producto.getDescripcion(),producto.getCategoria(),producto.getPrecio());  
			
			Producto prd= new Producto();
			prd.setNombre(producto.getNombre());
			prd.setDescripcion(producto.getDescripcion());
			prd.setPrecio(producto.getPrecio());
			prd.setCodcategoria(producto.getCodcategoria());
			
			productoRepository.save(prd);
			    
		}else {
			//productoRepository.actualizarProducto(producto.getCodproducto(),producto.getNombre(),producto.getDescripcion(),producto.getCategoria(),producto.getPrecio());
			
			productoRepository.saveAndFlush(producto);
		}
	}
	
	public void eliminarProducto(Producto producto) {
		productoRepository.deleteById(producto.getCodproducto());
	}
	
	public List<Producto> listarProductoxCategorias(int codcategoria){
		return productoRepository.listarProductoxCategoria(codcategoria);
	}
	
	/*public List<Producto> listarProductoxCategorias(String categoria){
		return productoRepository.listarProductoxCategoria(categoria);
	}*/
		
		
	public Producto buscarProducto(Integer codigo) {
        return productoRepository.findById(codigo).orElse(null);
    }
	
	
}


