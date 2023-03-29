package edu.pe.idat.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.model.Producto;
import edu.pe.idat.model.Usuario;
import edu.pe.idat.service.CategoriaService;
import edu.pe.idat.service.ProductoService;

@Controller
public class MenuController {

	@Autowired
	CategoriaService categoriaservice;

	@Autowired
	ProductoService productoService;

	@GetMapping("/Menus/menua")
	public String menua(Model model, final HttpSession session) {

		Usuario usu = (Usuario) session.getAttribute("sesionempl");
		model.addAttribute("mensaje", usu.getEmail());
		if (Objects.isNull(usu)) {
			return "/login";
		}
		return "/Menus/menua";
	}

	@GetMapping("/Menus/menue")
	public String menue(Model model, final HttpSession session) {
		Usuario usu = (Usuario) session.getAttribute("sesionempl");
		model.addAttribute("mensaje", usu.getEmail());
		if (Objects.isNull(usu)) {
			return "/login";
		}
		return "/Menus/menue";
	}

	@GetMapping("/Menus/menu")
	public String menu(Model model, final HttpSession session) {

		Usuario usu = (Usuario) session.getAttribute("otrasesion");
		try {
			model.addAttribute("mensaje", usu.getEmail());
			model.addAttribute("listadoCategoria", categoriaservice.ListarCategoria());
			return "/Menus/menu";
		} catch (Exception e) {
			model.addAttribute("mensaje", "Aún no estás registrado");
			model.addAttribute("listadoCategoria", categoriaservice.ListarCategoria());
			return "/Menus/menu";
		}
	}

	@GetMapping("/listarProductoxCategorias")
	@ResponseBody
	public List<Producto> listarProductoxCategorias(@RequestParam("codcategoria") int codcategoria) {
		return productoService.listarProductoxCategorias(codcategoria);
	}

}
