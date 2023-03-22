package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.DetallePedido;
import edu.pe.idat.repository.DetallePedidoRepositry;

@Service
public class DetallaPedidoService {
	
	@Autowired
	private DetallePedidoRepositry detarepo;
	
	public void IngresarDetallePedido(List<DetallePedido> listapedido) {
		for (DetallePedido detalle:listapedido) {
			detarepo.ingresarDetallePedido(detalle.getCodproducto(),
					detalle.getCantidad(), detalle.getNombreproducto(),  detalle.getPrecio(), detalle.getSubtotal());
		}
		
	}
	
	
	public List<DetallePedido> verDetallePedido (int codpedido){
		return detarepo.verDetallePedido(codpedido);
	}
		

}
