package edu.pe.idat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.model.Categoria;
import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.Empleado;
import edu.pe.idat.model.Producto;
import edu.pe.idat.model.ProductoResponse;
import edu.pe.idat.model.Usuario;
import edu.pe.idat.model.response.ResultadoResponse;
import edu.pe.idat.service.CategoriaService;
import edu.pe.idat.service.ProductoService;

@Controller
public class CategoriaController {

	@Autowired
	ProductoService productoService;

	@Autowired
	CategoriaService categoriaservice;

	@GetMapping("/listarcategorias")
	public String menua(Model model) {
		return "listarcategorias";
	}
	
	@GetMapping("/listarCategorias")
	@ResponseBody
	public List<Categoria> listarCategorias(){
	return categoriaservice.ListarCategoria();
	}

	@GetMapping("/buscarCategoria")
	@ResponseBody
	public List<Categoria> buscarProducto(@RequestParam("codcategoria") Integer codigo) {
		List <Categoria> listcategoria = new ArrayList<Categoria>();
		listcategoria.add(categoriaservice.buscarCategoria(codigo)); 
		return listcategoria;
	}
	

	@PostMapping("/registrarCategoria")
	@ResponseBody
	public ResultadoResponse registrarCategoria(@RequestBody Categoria cat) {
		String mensaje="Categoria registrada correctamente ";
		Boolean respuesta=true;
		
		if(cat.getCodcategoria() == 0) {
			try {
				categoriaservice.registrarCategoria(cat);			
			}catch(Exception ex) {
				mensaje="CATEGORIA NO REGISTRADA";
				respuesta=false;
			}
			
		}else {
			try {
				categoriaservice.actualizarCategoria(cat);	
				mensaje="Categoria actualizada correctamente";
			}catch(Exception ex) {
				mensaje="CATEGORIA NO ACTUALIZADA";
				respuesta=false;
			}
		}
		
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@DeleteMapping("/eliminarCategoria")
	@ResponseBody
	public ResultadoResponse eliminarCategoria(@RequestBody Categoria cat) {
		String mensaje="CATEGORIA ELIMINADOA CORRECTAMENTE";
		Boolean respuesta=true;
		try {
			categoriaservice.eliminarCategoria(cat.getCodcategoria());
		}catch (Exception e) {
			mensaje="CATEGORIA NO ELIMINADA";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
}
