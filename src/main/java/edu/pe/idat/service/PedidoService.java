package edu.pe.idat.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.pe.idat.model.Pedido;
import edu.pe.idat.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidorepository;
	

	
	public void IngresarPedido(Pedido pedido) {
		
	pedidorepository.ingresarPedido(pedido.getDireccion(),
			 "-12.15670555303427, -76.97280153131642", pedido.getMonto(),"Recibido", "-",pedido.getCodcliente());
			}
	
	public List<Pedido> listarPedido(){
		List<Pedido> list=pedidorepository.findAll();
		
		
		if(list.isEmpty()) {
			Pedido p=new Pedido();
			p.setCodpedido(0);
			
			list.add(p);
		}
		
	return list;
	}
	
	public void ActualizarEstadoPedido (int codpedido, String estado) {
		pedidorepository.actualizarEstadoPedido(codpedido, estado);
	}
	
	public	List<Pedido> buscarPedidoporEstado(String estado){
		
		List<Pedido> nvalista= pedidorepository.buscarPedidoporEstado(estado);
		if(nvalista.isEmpty()) {
			Pedido p=new Pedido();
			p.setCodpedido(0);
			
			nvalista.add(p);
		}
		
		return nvalista;
	}
	
public	List<Pedido> buscarPedidoporCódigo(int codpedido){
		Pedido p=new Pedido();
		List<Pedido> nvalista= new ArrayList<Pedido>();
		p=pedidorepository.findById(codpedido).orElse(null);
		if(Objects.isNull(p)) {
			Pedido perror=new Pedido();
			perror.setCodpedido(0);
			nvalista.add(perror);
			return nvalista;
		}else {
		nvalista.add(p);
		return nvalista;
		}
	}

public	List<Pedido> buscarPedidoporCliente(int codcliente){
	List<Pedido> nvalista= pedidorepository.buscarPedidoporCliente(codcliente);
	if(nvalista.isEmpty()) {
		Pedido p=new Pedido();
		p.setCodpedido(0);
		
		nvalista.add(p);
	}
	
	return nvalista;
}
}
