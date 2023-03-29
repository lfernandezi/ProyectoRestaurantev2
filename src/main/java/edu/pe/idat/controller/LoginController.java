package edu.pe.idat.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.model.Usuario;
import edu.pe.idat.model.response.ResultadoResponse;
import edu.pe.idat.service.ClienteService;

import edu.pe.idat.service.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService ususervice;

	@Autowired
	private ClienteService clienteservice;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());

		return "login";
	}

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
					if (!us.getPassword().equals(loginbd.getPassword())) {
						model.addAttribute("mensaje", "Contraseña incorrecta");
						return "login";
					} else {
						if (us.getRol() != null) {
							if (loginbd.getRol().equals("ROLE_CLIENTE")) {
								model.addAttribute("mensaje", "No tiene acceso de colaborador");
								return "login";
							} else {
								request.getSession().setAttribute("sesionempl", loginbd);
								model.addAttribute("mensaje", "" + loginbd.getEmail());
								if (loginbd.getRol().equals("ROLE_ADMIN")) {
									return "Menus/menua";
								} else {
									return "Menus/menue";
								}
							}
						} else {
							if (!loginbd.getRol().equals("ROLE_CLIENTE")) {
								model.addAttribute("mensaje", "Para ver carta ingrese con usuario cliente");
								return "login";
							} else {
								model.addAttribute("mensaje", "" + loginbd.getEmail());
								request.getSession().setAttribute("otrasesion", loginbd);
								return "Menus/menu";
							}
						}
					}
					/*
					 * if (us.getPassword().equals(loginbd.getPassword())) {
					 * model.addAttribute("mensaje", "" + loginbd.getEmail());
					 * request.getSession().setAttribute("otrasesion", loginbd); if
					 * (loginbd.getRol().equals("ROLE_CLIENTE")) { return "Menus/menu"; } else if
					 * (loginbd.getRol().equals("ROLE_ADMIN")) { return "Menus/menua"; } else {
					 * return "Menus/menue"; } } else { model.addAttribute("mensaje",
					 * "Contraseña incorrecta"); return "login"; }
					 */
				}
			} catch (Exception e) {
				model.addAttribute("mensaje", "Error, contacte con administrador");
				return "login";
			}
		}
	}

	@GetMapping("/verusuario")
	@ResponseBody
	public ResultadoResponse verusuario(final HttpSession session) {
		Boolean respuesta = true;
		String mensaje = "algo";
		try {
			Usuario cliente = (Usuario) session.getAttribute("otrasesion");

			mensaje = cliente.getEmail();
		} catch (Exception e) {
			respuesta = false;
			mensaje = "No estás registrado";
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

	
	@GetMapping("/verusuarioempl")
	@ResponseBody
	public ResultadoResponse verusuarioempl(final HttpSession session) {
		Boolean respuesta = true;
		String mensaje = "algo";
		try {
			Usuario emp = (Usuario) session.getAttribute("sesionempl");

			mensaje = emp.getEmail();
		} catch (Exception e) {
			respuesta = false;
			mensaje = "No estás registrado";
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

	@GetMapping("/cerrarsesion")
	@ResponseBody
	public Boolean cerrarsesion(final HttpSession session) {
		session.getAttribute("otrasesion");
		session.invalidate();
		try {
			Usuario cliente = (Usuario) session.getAttribute("otrasesion");

			return false;
		} catch (Exception e) {
			return true;
		}

	}

	@GetMapping("/cerrarsesionempl")
	@ResponseBody
	public Boolean cerrarsesionempl(final HttpSession session) {
		session.getAttribute("sesionempl");
		session.invalidate();
		try {
			Usuario cliente = (Usuario) session.getAttribute("sesionempl");

			return false;
		} catch (Exception e) {
			return true;
		}

	}

}
