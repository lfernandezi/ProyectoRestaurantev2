package edu.pe.idat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Producto;
import edu.pe.idat.model.ReportePedido;
import edu.pe.idat.model.response.ProductoResponse;
import edu.pe.idat.repository.ReportePedidoRepository;

@Service
public class ReportePedidoService {

	@Autowired
	ReportePedidoRepository repedidorepo;

	public List<ReportePedido> listarreporte() {

		List<ReportePedido> lisreporte = repedidorepo.findAll();
		// List<ReportePedido> nvalistaprod=new ArrayList<ReportePedido>();
		if (!lisreporte.isEmpty()) {
			return lisreporte;
			/*
			 * for (ReportePedido r:lisprod) { ProductoResponse p=new ProductoResponse();
			 * p.setCodproducto(producto.getCodproducto());
			 * p.setNombre(producto.getNombre());
			 * p.setDescripcion(producto.getDescripcion());
			 * p.setPrecio(producto.getPrecio());
			 * p.setCodcategoria(cateRepository.findById(producto.getCodcategoria()).orElse(
			 * null));
			 * 
			 * nvalistaprod.add(p); }
			 */
		} else {
			ReportePedido p = new ReportePedido();
			p.setCodproducto(0);
			lisreporte.add(p);
			return lisreporte;
		}

	}
	
	public List<ReportePedido> buscarReporte(Integer codpedido){
		return repedidorepo.buscarReporte(codpedido);
	}

}
