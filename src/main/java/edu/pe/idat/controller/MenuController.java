package edu.pe.idat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.pe.idat.model.Cliente;
import edu.pe.idat.service.CategoriaService;

@Controller
public class MenuController {
	
	/*@GetMapping("/Menus/menu")
	public String menu(Model model) {
		return "/Menus/menu";
	}*/
	
	@GetMapping("/Menus/menua")
	public String menua(Model model) {
		return "/Menus/menua";
	}
	
	@GetMapping("/Menus/menue")
	public String menue(Model model) {
		return "/Menus/menue";
	}
	
	@GetMapping("/Menus/menu")
	public String menu(Model model,final HttpSession session) {
		Cliente cliente=(Cliente) session.getAttribute("otrasesion");
		try {
		model.addAttribute("mensaje", cliente.getXnombre()+""+ cliente.getXapellido());
		return "/Menus/menu";
		}catch(Exception e){
		model.addAttribute("mensaje", "Aún no estás registrado");
		return "/Menus/menu";
		}
	}	
}
