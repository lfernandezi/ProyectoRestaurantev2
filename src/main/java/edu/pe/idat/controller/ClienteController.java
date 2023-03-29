package edu.pe.idat.controller;

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

import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.Usuario;
import edu.pe.idat.model.response.ResultadoResponse;
import edu.pe.idat.service.ClienteService;
import edu.pe.idat.service.UsuarioService;

@Controller
public class ClienteController {

	@Autowired
	ClienteService clienteservice;

	@Autowired
	UsuarioService ususervice;

	@GetMapping("/registrarCliente")
	public String registrarCliente(Model model, final HttpSession session) {
		Usuario usu = (Usuario) session.getAttribute("otrasesion");
		if (Objects.nonNull(usu)) {
			Cliente cli = clienteservice.buscarclientexEmail(usu.getEmail());
			model.addAttribute("nuevocliente", cli);
			return "registrarCliente";
		} else {
			model.addAttribute("nuevocliente", new Cliente());
			return "registrarCliente";

		}

	}

	@GetMapping("/listaclientes")
	public String listar(Model model) {
		
		return "listaclientes";
	}

	@GetMapping("/listarCliente")
	@ResponseBody
	public List<Cliente> listarCliente() {
		return clienteservice.listarCliente();
	}

	@PostMapping("/registrarCliente")
	public String registrarCliente(@ModelAttribute("nuevocliente") Cliente cliente, Model model) {
		int codigo = 0;
		String doc = cliente.getXdni();
		String tel = cliente.getXtelefono();
		String mensaje = "";
		if (cliente.getXcontrasenia() == "") {
			codigo = 6;
		}
		if (cliente.getXemail() == "") {
			codigo = 5;
		}
		if (cliente.getXdireccion() == "") {
			codigo = 4;
		}
		if (tel.length() != 9) {
			codigo = 7;
		}
		if (doc.length() != 8) {
			codigo = 1;
		}
		if (cliente.getXapellido() == "") {
			codigo = 3;
		}
		if (cliente.getXnombre() == "") {
			codigo = 2;
		}
		switch (codigo) {
		case 7:
			mensaje = "Teléfono inválido";
			break;
		case 6:
			mensaje = "No ingresó contraseña";
			break;
		case 5:
			mensaje = "No ingresó email";
			break;
		case 4:
			mensaje = "No ingresó dirección";
			break;
		case 3:
			mensaje = "No ingresó apellido";
			break;
		case 2:
			mensaje = "No ingresó nombre";
			break;
		case 1:
			mensaje = "Dni inválido";
			break;

		case 0:
			try {

				if (cliente.getCodcliente() == 0) {
					if (Objects.isNull(ususervice.buscarUsuario(cliente.getXemail()))) {
						clienteservice.RegistrarCliente(cliente);
						mensaje = "Cliente registrado";
						model.addAttribute("nuevocliente", new Cliente());
						model.addAttribute("mensaje1", mensaje);
						return "registrarcliente";
					} else {
						mensaje = "Email ya existe, no se pudo registrar";
					}

				} else {

					Cliente clibd = clienteservice.buscarcliente(cliente.getCodcliente());
					if (!clibd.getXemail().equals(cliente.getXemail())) {
						mensaje = "No es posible cambiar el correo";
					} else {

						if (clibd.getXnombre().equals(cliente.getXnombre())
								&& clibd.getXapellido().equals(cliente.getXapellido())
								&& clibd.getXdni().equals(cliente.getXdni())
								&& clibd.getXdireccion().equals(cliente.getXdireccion())
								&& clibd.getXtelefono().equals(cliente.getXtelefono())
								&& clibd.getXcontrasenia().equals(cliente.getXcontrasenia())) {
							mensaje = "No realizaste ningún cambio";
						} else {
							clienteservice.ActualizarCliente(cliente);
							mensaje = "ACTUALIZASTE TUS DATOS";
							
							model.addAttribute("mensaje1", mensaje);
							return "registrarcliente";
						}
					}
				}

			} catch (Exception e) {
				mensaje = "No se pudo registrar";
			}
		}
		model.addAttribute("mensaje", mensaje);
		return "registrarcliente";
	}

	@DeleteMapping("/eliminarCliente")
	@ResponseBody
	public ResultadoResponse eliminarCliente(@RequestBody Cliente objcliente) {
		String mensaje = "Cliente Eliminado Correctamente";
		Boolean respuesta = true;
		try {
			clienteservice.EliminarCliente(objcliente);
			//ususervice.eliminarUsuario(objcliente.getCodcliente());

		} catch (Exception e) {
			mensaje = "Cliente no Eliminado";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

	@GetMapping("/buscarCliente")
	@ResponseBody
	public List<Cliente> buscarCliente(@RequestParam("codcliente") int codigo) {
		List<Cliente> lista = new ArrayList<Cliente>();
		lista.add(clienteservice.buscarcliente(codigo));
		return lista;
	}

	@GetMapping("/buscarClientexdni")
	@ResponseBody
	public List<Cliente> buscarClientexdni(@RequestParam("dni") String dni) {
		return clienteservice.buscarclientexdni(dni);
	}

}
