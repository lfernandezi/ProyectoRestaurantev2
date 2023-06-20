package edu.pe.idat.controller;

import java.io.FileInputStream;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.pe.idat.service.DetallaPedidoService;
import edu.pe.idat.service.PedidoService;
import edu.pe.idat.service.ReportePedidoService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReporteController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	DetallaPedidoService detalleservice;
	
	@Autowired
	ReportePedidoService reporteservice;

	@GetMapping("/obtenerPdfCocina")
	public ResponseEntity<byte []> getPDF(@RequestParam ("codpedido")int codpedido) throws Exception, JRException {
		
		JRBeanCollectionDataSource beanCollection=new JRBeanCollectionDataSource(reporteservice.buscarReporte(codpedido));
		JasperReport compilereport =   JasperCompileManager.compileReport(new FileInputStream("src/main/resources/PedidoReporteJasper.jrxml"));
	
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report=  JasperFillManager.fillReport(compilereport, map, beanCollection);
		
		
		
		
		byte [] dat = JasperExportManager.exportReportToPdf(report);
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders(); 
		
		headers.set(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,"inline;filename=Reporte.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(dat);
	
	}
	
	@GetMapping("/obtenerPdf2")
	public ResponseEntity<byte []> getPDF2(@RequestParam ("codpedido")int codpedido) throws Exception, JRException {
		
		JRBeanCollectionDataSource beanCollection=new JRBeanCollectionDataSource(reporteservice.buscarReporte(codpedido));
		JasperReport compilereport =   JasperCompileManager.compileReport(new FileInputStream("src/main/resources/PedidoReporteJasper.jrxml"));
	
		
		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report=  JasperFillManager.fillReport(compilereport, map, beanCollection);
		
		
		
		
		byte [] dat = JasperExportManager.exportReportToPdf(report);
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders(); 
		
		headers.set(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,"inline;filename=Reporte.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(dat);
	
	}
}
