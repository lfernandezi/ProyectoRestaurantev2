package edu.pe.idat.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.Login;
import edu.pe.idat.model.Usuario;
import edu.pe.idat.service.ClienteService;
import edu.pe.idat.service.LoginService;
import edu.pe.idat.service.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginservice;

	@Autowired
	private UsuarioService ususervice;

	@Autowired
	private ClienteService clienteservice;

	/*
	 * @GetMapping("/login") public String login(Model model) {
	 * model.addAttribute("usuario", new LoginForm()); return "login"; }
	 */

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
	
		return "login";
	}

	/*
	 * @PostMapping("/login") public String login(@ModelAttribute("loginform")
	 * LoginForm login, final HttpServletRequest request, Model model) { String
	 * email = login.getUsuario(); LoginForm loginbd = new LoginForm(); if
	 * (email.equals("") || login.getContraseña().equals("")) {
	 * model.addAttribute("mensaje", "Complete los campos requeridos"); return
	 * "login"; } else {
	 * 
	 * loginbd = loginservice.MostrarUsuario(email); try { if
	 * (Objects.isNull(loginbd)) { model.addAttribute("mensaje",
	 * "Usuario no registrado"); return "login"; } else { if
	 * (login.getContraseña().equals(loginbd.getContraseña())) { ClienteForm cliente
	 * = new ClienteForm(); cliente =
	 * clienteservice.buscarcliente(loginbd.getCodcliente());
	 * model.addAttribute("mensaje", "" + cliente.getXnombre() + " " +
	 * cliente.getXapellido());
	 * 
	 * request.getSession().setAttribute("otrasesion", cliente); return
	 * "Menus/menu"; } else { model.addAttribute("mensaje",
	 * "Contraseña incorrecta"); return "login"; } } } catch (Exception e) {
	 * model.addAttribute("mensaje", "Error, contacte con administrador"); return
	 * "login"; }
	 * 
	 * }
	 * 
	 * }
	 */

	@PostMapping("/login")
	public String login(@ModelAttribute("usuario") Usuario us, final HttpServletRequest request, Model model) {
		String email = us.getEmail();
		Usuario loginbd = new Usuario();
		if (email.equals("") || us.getPassword().equals("")) {
			model.addAttribute("mensaje", "Complete los campos requeridos");
			return "login";
		} else {

			loginbd = ususervice.buscarUsuario(email);
			try {
				if (Objects.isNull(loginbd)) {
					model.addAttribute("mensaje", "Usuario no registrado");
					return "login";
				} else {
					if (us.getPassword().equals(loginbd.getPassword())) {
						// ClienteForm cliente = new ClienteForm();
						// cliente = clienteservice.buscarcliente(loginbd.getCodusuario());
						model.addAttribute("mensaje", "" + loginbd.getEmail());

						request.getSession().setAttribute("otrasesion", loginbd);

						if (loginbd.getRol().equals("ROLE_CLIENTE")) {
							return "Menus/menu";
						} else if (loginbd.getRol().equals("ROLE_ADMIN")) {
							return "Menus/menua";
						} else {
							return "Menus/menue";

						}
					} else {
						model.addAttribute("mensaje", "Contraseña incorrecta");
						return "login";
					}
				}
			} catch (Exception e) {
				model.addAttribute("mensaje", "Error, contacte con administrador");
				return "login";
			}

		}

	}

}
