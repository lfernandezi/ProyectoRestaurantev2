package edu.pe.idat.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.repository.CategoriaRepository;
import edu.pe.idat.repository.ProductoRepository;
import edu.pe.idat.model.Empleado;
import edu.pe.idat.model.Producto;
import edu.pe.idat.model.response.EmpleadoResponse;
import edu.pe.idat.model.response.ProductoResponse;

@Service
public class ProductoService {	
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	CategoriaRepository cateRepository;
	
	public List<ProductoResponse> listarProducto(){
		
		List<Producto> lisprod= productoRepository.findAll();
		List<ProductoResponse> nvalistaprod=new ArrayList<ProductoResponse>();
		if (!lisprod.isEmpty()) {
			for (Producto producto:lisprod) {
				ProductoResponse p=new ProductoResponse();
				p.setCodproducto(producto.getCodproducto());
				p.setNombre(producto.getNombre());
				p.setDescripcion(producto.getDescripcion());
				p.setPrecio(producto.getPrecio());
				p.setCodcategoria(cateRepository.findById(producto.getCodcategoria()).orElse(null));
				
				nvalistaprod.add(p);
			}
		}else {
			ProductoResponse p=new ProductoResponse();
			p.setCodproducto(0);
			nvalistaprod.add(p);
		}
		
		
		return nvalistaprod;
	}	

	public void registrarProducto(Producto producto) {
		if(producto.getCodproducto() == 0) {
			
			productoRepository.save(producto);  
			
			/*Producto prd= new Producto();
			prd.setNombre(producto.getNombre());
			prd.setDescripcion(producto.getDescripcion());
			prd.setPrecio(producto.getPrecio());
			prd.setCodcategoria(producto.getCodcategoria());
			prd.setImagen(null);
			productoRepository.save(prd);*/
			    
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
		
		
	public ProductoResponse buscarProducto(Integer codigo) {
		
		Producto pro = productoRepository.findById(codigo).orElse(null);;  
		ProductoResponse p= new ProductoResponse();
		if(Objects.nonNull(pro)) {
			
		p.setCodproducto(pro.getCodproducto());
		p.setNombre(pro.getNombre());
		p.setDescripcion(pro.getDescripcion());
		p.setPrecio(pro.getPrecio());
		p.setCodcategoria(cateRepository.findById(pro.getCodcategoria()).orElse(null));
		
		}
		return p;
      
    }
	
	
}


