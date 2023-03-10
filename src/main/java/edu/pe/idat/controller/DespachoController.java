package edu.pe.idat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.DetallePedido;
import edu.pe.idat.model.Usuario;
import edu.pe.idat.service.ClienteService;

@Controller
public class DespachoController {
	
	@Autowired
	ClienteService clienteservice;
	

	@GetMapping("/despacho")
	public String despacho(Model model, final HttpSession session) {

		List<DetallePedido> listapedidos = (List<DetallePedido>) session.getAttribute("misesion");
		Usuario cliente = (Usuario) session.getAttribute("otrasesion");
		if (Objects.isNull(cliente)) {
			model.addAttribute("mensaje", "No estás registrado");
			return "carrito";
		} else {
			if (CollectionUtils.isEmpty(listapedidos)) {
				model.addAttribute("mensaje", "No tienes productos en el carrito");
				return "carrito";

			} else {
				return "despacho";
			}
		}
	}

	@GetMapping("/listarcliente")
	@ResponseBody
	public List<Cliente> listarcliente(final HttpSession session) {
		List<Cliente> listacliente = new ArrayList<Cliente>();
		Usuario usu = (Usuario) session.getAttribute("otrasesion");
		Cliente  cli=clienteservice.buscarcliente(usu.getCodusuario());
		listacliente.add(cli);
		return listacliente;
	}

	@GetMapping("/listarcarrito2")
	@ResponseBody
	public List<DetallePedido> listarcarrito(final HttpSession session) {
		List<DetallePedido> listapedidos = (List<DetallePedido>) session.getAttribute("misesion");
		if (CollectionUtils.isEmpty(listapedidos)) {
			listapedidos = new ArrayList<DetallePedido>();
		}
		return listapedidos;
	}

	@GetMapping("/venta")
	public String venta(Model model) {
		return "/venta";
	}
}
