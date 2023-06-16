package edu.pe.idat.model.response;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import edu.pe.idat.model.Pedido;

@Component("/listarpedido")
public class PdfListaPedidos extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Pedido> listapedido= (List<Pedido>) model.get("listarpedido");
		
		PdfPTable tabla = new PdfPTable(3);
		
		listapedido.forEach(pedido ->{
			tabla.addCell(pedido.getDireccion());
			tabla.addCell(pedido.getEstado());
			tabla.addCell(pedido.getMotivo());
			//tabla.addCell(pedido.getCodpedido());
		});
		
		document.add(tabla);
		// TODO Auto-generated method stub
		
	}

}
