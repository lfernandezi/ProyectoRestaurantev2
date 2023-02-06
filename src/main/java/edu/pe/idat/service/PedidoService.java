package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Pedido;
import edu.pe.idat.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidorepository;
	
	/*public void IngresarPedido(Pedido pedido) {
	pedidorepository.ingresarPedido(pedido.getCodcliente(),pedido.getDireccion(),
	pedido.getSubtotal(), pedido.getIgv(), pedido.getMonto(),"Recibido");
	}*/
	
	public void IngresarPedido(Pedido pedido) {
		
		
		/*pedidorepository.ingresarPedido(pedido.getCodcliente(),pedido.getDireccion(),
		pedido.getSubtotal(), pedido.getIgv(), pedido.getMonto(),"Recibido");
		}*/
	
	pedidorepository.ingresarPedido(pedido.getCodcliente(),pedido.getDireccion(),
			 pedido.getMonto(),"Recibido");
			}
	
	public List<Pedido> listarPedido(){
	return pedidorepository.findAll();
	}
	
	public void ActualizarEstadoPedido (int codpedido, String estado) {
		pedidorepository.actualizarEstadoPedido(codpedido, estado);
	}
}
