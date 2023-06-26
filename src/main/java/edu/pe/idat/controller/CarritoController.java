package edu.pe.idat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.model.Carrito;
import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.DetallePedido;
import edu.pe.idat.model.Usuario;
import edu.pe.idat.model.response.ResultadoResponse;
import edu.pe.idat.service.CarritoService;

@Controller
public class CarritoController {

	@Autowired
	CarritoService carritoservice;

	@GetMapping("/carrito")
	public String listarpedidos(Model model, final HttpSession session) {
		Usuario cliente = (Usuario) session.getAttribute("otrasesion");
		if (Objects.isNull(cliente)) {
			model.addAttribute("mensaje", "No ha iniciado sesión");
			return "Menus/menu";
		} else {
			List<Carrito> listapedidos = (List<Carrito>) session.getAttribute("misesion");
			if (CollectionUtils.isEmpty(listapedidos)) {
				listapedidos = new ArrayList<Carrito>();
				Carrito p= new Carrito();
				listapedidos.add(p);
				model.addAttribute("compra", 0);
				model.addAttribute("listadopedidos", listapedidos);

			}
			return "carrito";
		}
	}

	@GetMapping("/listarcarrito")
	@ResponseBody
	public List<Carrito> listarcarrito(final HttpSession session) {
		List<Carrito> listapedidos = (List<Carrito>) session.getAttribute("misesion");
		if (CollectionUtils.isEmpty(listapedidos)) {
			listapedidos = new ArrayList<Carrito>();
			Carrito p= new Carrito();
			listapedidos.add(p);
			
		}
		return listapedidos;
	}

	@PostMapping("/registrarpedido")
	@ResponseBody
	public ResultadoResponse registrarpedido(@RequestBody Carrito pedido, final HttpServletRequest request) {

		String mensaje = "Se incluyó en carrito";
		Boolean respuesta = true;
		List<Carrito> listapedido = (List<Carrito>) request.getSession().getAttribute("misesion");
		if (CollectionUtils.isEmpty(listapedido)) {
			listapedido = new ArrayList<Carrito>();
		}
		try {
			request.getSession().setAttribute("misesion", carritoservice.agregarPedido(pedido, listapedido));
		} catch (Exception ex) {
			mensaje = "No se pudo incluir en carrito";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

	@DeleteMapping("/eliminardecarrito")
	@ResponseBody
	public ResultadoResponse eliminardecarrito(@RequestBody Carrito pedido, final HttpServletRequest request) {
		String mensaje = "Se eliminó del carrito";
		Boolean respuesta = true;
		List<Carrito> listapedido = (List<Carrito>) request.getSession().getAttribute("misesion");
		try {
			request.getSession().setAttribute("misesion", carritoservice.eliminarproductodepedido(pedido, listapedido));
		} catch (Exception ex) {
			mensaje = "No se pudo eliminar de carrito";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

	@PostMapping("/actualizacarrito")
	@ResponseBody
	public ResultadoResponse actualizacarrito(@RequestBody Carrito pedido, final HttpServletRequest request) {
		String mensaje = "Se actualizó cantidad";
		Boolean respuesta = true;
		List<Carrito> listapedido = (List<Carrito>) request.getSession().getAttribute("misesion");
		try {
			request.getSession().setAttribute("misesion", carritoservice.actualizacantidadpedido(pedido, listapedido));
		} catch (Exception ex) {
			mensaje = "No se pudo actualizar carrito";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

}
