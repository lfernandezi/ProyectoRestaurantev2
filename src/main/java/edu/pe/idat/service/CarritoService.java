package edu.pe.idat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Carrito;
import edu.pe.idat.model.DetallePedido;
import edu.pe.idat.model.Pedido;
import edu.pe.idat.repository.CarritoRepository;
import edu.pe.idat.repository.PedidoRepository;

@Service
public class CarritoService {

	/*@Autowired
	CarritoRepository carritorepository;*/
	
	public List<Carrito> agregarPedido(Carrito carrito, List<Carrito> antigualistapedidos) {
		boolean nuevoproducto = false;
		boolean agregarnuevo = false;
		List<Carrito> nuevalistapedidos = new ArrayList<Carrito>();
		if (antigualistapedidos.size() == 0) {
			nuevalistapedidos.add(carrito);
		} else {
			for (Carrito objantigualista : antigualistapedidos) {
				if (objantigualista.getCodproducto() == carrito.getCodproducto()) {
					nuevalistapedidos.add(new Carrito(objantigualista.getCodproducto(),
							objantigualista.getNombreproducto(), objantigualista.getDescripcion(),
							objantigualista.getCantidad() + carrito.getCantidad(), objantigualista.getPrecio(),
							objantigualista.getSubtotal() + carrito.getSubtotal()));
					nuevoproducto = true;
					agregarnuevo = false;
				} else {
					nuevalistapedidos.add(objantigualista);
					if (nuevoproducto) {
						agregarnuevo = false;
					} else {
						agregarnuevo = true;
					}
				}
			}
			if (agregarnuevo) {
				nuevalistapedidos.add(carrito);
			}
		}
		return nuevalistapedidos;
	}

	public List<Carrito> eliminarproductodepedido(Carrito carrito, List<Carrito> listapedidos) {
		for (Carrito objlista : listapedidos) {
			if (carrito.getCodproducto() == objlista.getCodproducto()) {
				int n = listapedidos.indexOf(objlista);
				listapedidos.remove(n);
				break;
			}
		}
		return listapedidos;
	}

	public List<Carrito> actualizacantidadpedido(Carrito carrito, List<Carrito> listapedidos) {
		for (Carrito objlista : listapedidos) {
			if (carrito.getCodproducto() == objlista.getCodproducto()) {
				objlista.setCantidad(carrito.getCantidad());
				objlista.setSubtotal(carrito.getSubtotal());
				break;
			}
		}
		return listapedidos;
	}

	
	
	
}
