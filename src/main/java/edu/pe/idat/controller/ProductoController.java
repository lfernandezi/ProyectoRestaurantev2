package edu.pe.idat.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.pe.idat.service.CategoriaService;
import edu.pe.idat.service.ProductoService;


import edu.pe.idat.model.Producto;
import edu.pe.idat.model.Usuario;
import edu.pe.idat.model.response.ProductoResponse;
import edu.pe.idat.model.response.ResultadoResponse;

@Controller
public class ProductoController {

	@Autowired
	ProductoService productoService;

	@Autowired
	CategoriaService categoriaservice;

	@GetMapping("/listarproducto")
	public String listarproducto(Model model, final HttpSession session) {
		Usuario usu = (Usuario) session.getAttribute("sesionempl");
		if (Objects.isNull(usu)) {
			model.addAttribute("mensaje", "No ha iniciado sesión");
			model.addAttribute("usuario", new Usuario());

			return "login";
		} else {

			model.addAttribute("nuevoproducto", new Producto());
			return "/listarproducto";

		}

	}

	@GetMapping("/listarProducto")
	@ResponseBody
	public List<ProductoResponse> listarProducto() {
		return productoService.listarProducto();
	}

	@PostMapping("/registrarProducto")
	@ResponseBody
	public ResultadoResponse registrarProducto(@RequestBody Producto objProducto) {
		String mensaje = "COMIDA REGISTRADA CORRECTAMENTE ";
		Boolean respuesta = true;

		try {
			productoService.registrarProducto(objProducto);
		} catch (Exception ex) {
			mensaje = "COMIDA NO REGISTRADA";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

	@PostMapping("/registrarProducto2")
	public String registrarProducto2(@ModelAttribute("nuevoproducto") Producto producto, Model model, @RequestParam ("file") MultipartFile imagen) {
		String mensaje = ""; 
		
		if (!imagen.isEmpty()){
			Path directorioImagenes = Paths.get("src//main//resources//static//img");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				producto.setImagen(imagen.getOriginalFilename());
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			productoService.registrarProducto(producto);
		} catch (Exception ex) {
			mensaje = "COMIDA NO REGISTRADA";
			
		}
		
		model.addAttribute("mensaje", mensaje);
		
		return "listarproducto";
	}

	@DeleteMapping("/eliminarProducto")
	@ResponseBody
	public ResultadoResponse eliminarProducto(@RequestBody Producto objProducto) {
		String mensaje = "COMIDA ELIMINADA CORRECTAMENTE";
		Boolean respuesta = true;
		try {
			productoService.eliminarProducto(objProducto);
		} catch (Exception e) {
			mensaje = "COMIDA NO ELIMINADA";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

	@GetMapping("/buscarProducto")
	@ResponseBody
	public List<ProductoResponse> buscarProducto(@RequestParam("codproducto") Integer codigo) {
		List<ProductoResponse> listproducto = new ArrayList<ProductoResponse>();
		listproducto.add(productoService.buscarProducto(codigo));
		return listproducto;
	}

	/*
	 * @GetMapping("/listarCategorias")
	 * 
	 * @ResponseBody public List<Categoria> listarCategorias(){ return
	 * categoriaservice.ListarCategoria(); }
	 */
}
