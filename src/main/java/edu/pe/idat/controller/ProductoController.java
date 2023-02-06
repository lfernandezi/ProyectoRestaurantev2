package edu.pe.idat.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.pe.idat.service.CategoriaService;
import edu.pe.idat.service.ProductoService;
import edu.pe.idat.model.Categoria;
import edu.pe.idat.model.Producto;
import edu.pe.idat.model.ProductoResponse;
import edu.pe.idat.model.response.ResultadoResponse;

@Controller
public class ProductoController {
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaservice;
	
	@GetMapping("/listarproducto")	
	public String listarproducto ()
	//(Model model) 
	{
		//model.addAttribute("listadoproducto", productoService.listarProducto());
		return "listarproducto";
	}
	
	@GetMapping("/listarProducto")
	@ResponseBody
	public List<ProductoResponse> listarProducto(){
		return productoService.listarProducto();
	}	
	
	@PostMapping("/registrarProducto")
	@ResponseBody
	public ResultadoResponse registrarProducto(@RequestBody Producto objProducto) {
		String mensaje="COMIDA REGISTRADA CORRECTAMENTE ";
		Boolean respuesta=true;
		try {
			productoService.registrarProducto(objProducto);			
		}catch(Exception ex) {
			mensaje="COMIDA NO REGISTRADA";
			respuesta=false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@DeleteMapping("/eliminarProducto")
	@ResponseBody
	public ResultadoResponse eliminarProducto(@RequestBody Producto objProducto) {
		String mensaje="COMIDA ELIMINADA CORRECTAMENTE";
		Boolean respuesta=true;
		try {
			productoService.eliminarProducto(objProducto);
		}catch (Exception e) {
			mensaje="COMIDA NO ELIMINADA";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	
	@GetMapping("/buscarProducto")
	@ResponseBody
	public List<Producto> buscarProducto(@RequestParam("codproducto") Integer codigo) {
		List <Producto> listproducto = new ArrayList<Producto>();
		listproducto.add(productoService.buscarProducto(codigo)); 
		return listproducto;
	}
	
	
	@GetMapping("/listarCategorias")
	@ResponseBody
	public List<Categoria> listarCategorias(){
	return categoriaservice.ListarCategoria();
	}
	
}

