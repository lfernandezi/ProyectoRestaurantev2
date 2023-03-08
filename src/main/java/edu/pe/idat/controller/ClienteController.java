package edu.pe.idat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import edu.pe.idat.model.response.ResultadoResponse;
import edu.pe.idat.service.ClienteService;
import edu.pe.idat.service.LoginService;

@Controller
public class ClienteController {

	@Autowired
	ClienteService clienteservice;
	
	@Autowired
	LoginService loginservice;
	

	@GetMapping("/registrarCliente")
	public String registrarCliente(Model model) {
		model.addAttribute("nuevocliente", new Cliente());
		return "registrarCliente";
	}

	@GetMapping("/listaclientes")
	public String listar(Model model) {
		//model.addAttribute("lstClientes", clienteservice.listarCliente());
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
				if (Objects.isNull(loginservice.MostrarUsuario(cliente.getXemail())) ) {
					clienteservice.RegistrarCliente(cliente);
					mensaje = "Cliente registrado";
					model.addAttribute("nuevocliente", new Cliente());
					}else {
					mensaje = "Email ya existe, no se pudo registrar";
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
		} catch (Exception e) {
			mensaje = "Cliente no Eliminado";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@GetMapping("/buscarCliente")
	@ResponseBody
	public List<Cliente> buscarCliente(@RequestParam("codcliente") Integer codigo) {
		List <Cliente> lista = new ArrayList<Cliente>();
		lista.add(clienteservice.buscarcliente(codigo));
		return lista;
	}
	
	@GetMapping("/buscarClientexdni")
	@ResponseBody
	public List<Cliente> buscarClientexdni(@RequestParam("dni") String dni) {
	return clienteservice.buscarclientexdni(dni);
	}
	
	
}
