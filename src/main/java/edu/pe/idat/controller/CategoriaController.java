package edu.pe.idat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.Producto;
import edu.pe.idat.service.CategoriaService;
import edu.pe.idat.service.ProductoService;

@Controller
public class CategoriaController {
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	CategoriaService categoriaservice;
	
	@GetMapping("/categorias")	
	public String categorias (Model model, final HttpSession session){
		Cliente cliente=(Cliente) session.getAttribute("otrasesion");
		try {
		model.addAttribute("listadoCategoria", categoriaservice.ListarCategoria());
		model.addAttribute("mensaje", cliente.getXnombre()+""+ cliente.getXapellido());
		return "categorias";
		}catch(Exception e){
		model.addAttribute("mensaje", "Aún no estás registrado");
		model.addAttribute("listadoCategoria", categoriaservice.ListarCategoria());
		return "categorias";
	}
}		
	
	/*@GetMapping("/Categorias/listarProductoxCategorias")
	@ResponseBody
	public List<Producto> listarProductoxCategorias (
	@RequestParam("categoria") String categoria){
	return productoService.listarProductoxCategorias(categoria);
	}	*/
	
	@GetMapping("/Categorias/listarProductoxCategorias")
	@ResponseBody
	public List<Producto> listarProductoxCategorias (
	@RequestParam("codcategoria") int codcategoria){
	return productoService.listarProductoxCategorias(codcategoria);
	}	
}
