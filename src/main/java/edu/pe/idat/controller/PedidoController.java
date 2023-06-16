package edu.pe.idat.controller;


import java.io.FileInputStream;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.model.Carrito;
import edu.pe.idat.model.DetallePedido;
import edu.pe.idat.model.Pedido;
import edu.pe.idat.model.Usuario;
import edu.pe.idat.model.response.ResultadoResponse;

import edu.pe.idat.service.DetallaPedidoService;
import edu.pe.idat.service.PedidoService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	DetallaPedidoService detalleservice;

	@PostMapping("/ingresarPedido")
	@ResponseBody
	public ResultadoResponse ingresarPedido(@RequestBody Pedido pedido, final HttpSession session) {
		String mensaje = "Tu pedido ha sido ingresado ";
		Boolean respuesta = true;
		try {
			pedidoService.IngresarPedido(pedido);
			List<Carrito> listapedidos = (List<Carrito>) session.getAttribute("misesion");
			List<DetallePedido> listadetalle = new ArrayList<DetallePedido>();
			for(Carrito carrito : listapedidos) {
				DetallePedido detalle= new DetallePedido();
				detalle.setCodproducto(carrito.getCodproducto());
				
				detalle.setCantidad(carrito.getCantidad());
				detalle.setNombreproducto(carrito.getNombreproducto());
				detalle.setPrecio(carrito.getPrecio());
				detalle.setSubtotal(carrito.getSubtotal());
				
				listadetalle.add(detalle);
			}
			
			
			detalleservice.IngresarDetallePedido(listadetalle);
			
			//session.invalidate();
		} catch (Exception ex) {
			mensaje = "Tu pedido NO fue ingresado";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	
	@GetMapping("/listarpedido")
	public String listarpedido (Model model,final HttpSession session) {
		
		Usuario usu = (Usuario) session.getAttribute("sesionempl");
		if (Objects.isNull(usu)){
			model.addAttribute("mensaje", "No estás registrado");
			model.addAttribute("usuario", new Usuario());
			return "login";
		} else {
			model.addAttribute("listarpedido", pedidoService.listarPedido());
			return "listarpedido";
		}
	
	}
	
	
	@GetMapping("/obtenerPdf")
	public ResponseEntity<byte []> getPDF() throws Exception, JRException {
		
		JRBeanCollectionDataSource beanCollection=new JRBeanCollectionDataSource(detalleservice.listarDetalle());
		JasperReport compilereport =   JasperCompileManager.compileReport(new FileInputStream("src/main/resources/PedidoReporteJasper.jrxml"));
	
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report=  JasperFillManager.fillReport(compilereport, map, beanCollection);
		
		
		
		
		byte [] dat = JasperExportManager.exportReportToPdf(report);
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders(); 
		
		headers.set(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,"inline;filename=Reporte.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(dat);
	
	}
	
	@GetMapping("/listarPedido")
	@ResponseBody
	public List<Pedido> listarPedido(){
	return pedidoService.listarPedido();
	}
	
	@GetMapping("listarDetallePedido")
	@ResponseBody
	public List<DetallePedido> listarDetallePedido (@RequestParam ("codpedido")int codpedido){
		//List<DetallePedido> nvalista=new ArrayList<DetallePedido>();
		return detalleservice.verDetallePedido(codpedido);
		
	}
	
	@PostMapping("/actualizarEstadoPedido")
	@ResponseBody
	public ResultadoResponse actualizarEstadoPedido(@RequestParam ("codpedido")int codpedido, @RequestParam ("estado")String estado) {
		String mensaje="Estado Actualizado CORRECTAMENTE ";
		Boolean respuesta=true;
		try {
			pedidoService.ActualizarEstadoPedido(codpedido, estado);			
		}catch(Exception ex) {
			mensaje="ESTADO NO REGISTRADO";
			respuesta=false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
		
	@GetMapping("/buscarPedidoporEstado")
	@ResponseBody
	public List<Pedido> buscarPedidoporEstado (@RequestParam ("estado")String estado){
		List<Pedido> nvalista= pedidoService.buscarPedidoporEstado(estado);
		
		return nvalista;
		
	}
	
	@GetMapping("/buscarPedidoporCodigo")
	@ResponseBody
	public List<Pedido> buscarPedidoporCodigo (@RequestParam ("codpedido")int codpedido){
		List<Pedido> nvalista= pedidoService.buscarPedidoporCódigo(codpedido);
		return nvalista;
		
	}

	@GetMapping("/buscarPedidoporCliente")
	@ResponseBody
	public List<Pedido> buscarPedidoporCliente (@RequestParam ("codcliente")int codcliente){
		List<Pedido> nvalista= pedidoService.buscarPedidoporCliente(codcliente);
		return nvalista;
		
	}
}
