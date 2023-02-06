package edu.pe.idat.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pe.idat.service.AreaEmpleadoService;
import edu.pe.idat.service.CargoEmpleadoService;
import edu.pe.idat.service.EmpleadoService;
import edu.pe.idat.model.AreaEmpleado;
import edu.pe.idat.model.CargoEmpleado;
import edu.pe.idat.model.Cliente;
import edu.pe.idat.model.Empleado;
import edu.pe.idat.model.EmpleadoResponse;
import edu.pe.idat.model.response.ResultadoResponse;

@Controller
public class EmpleadoController {
	
	@Autowired
	EmpleadoService empleadoService;
	
	@Autowired
	CargoEmpleadoService cargoService;
	
	@Autowired
	AreaEmpleadoService areaService;	
	
	
	@GetMapping("/listarempleado")	
	public String listarempleado (Model model) {
		model.addAttribute("listadoempleado",null);
		return "listarempleado";
	}
	
	@GetMapping("/listarEmpleado")
	@ResponseBody
	public List<EmpleadoResponse> listarEmpleado(){
		return empleadoService.listarEmpleadoResponse();
	
	}
	
	@PostMapping("/registrarEmpleado")
	@ResponseBody
	public ResultadoResponse registrarEmpleado(@RequestBody Empleado objEmpleado) {
		String mensaje="EMPLEADO REGISTRADO CORRECTAMENTE ";
		Boolean respuesta=true;
		try {
			empleadoService.registrarEmpleado(objEmpleado);			
		}catch(Exception ex) {
			mensaje="EMPLEADO NO REGISTRADO";
			respuesta=false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@DeleteMapping("/eliminarEmpleado")
	@ResponseBody
	public ResultadoResponse eliminarEmpleado(@RequestBody Empleado objEmpleados) {
		String mensaje="EMPLEADO ELIMINADO CORRECTAMENTE";
		Boolean respuesta=true;
		try {
			empleadoService.eliminarEmpleado(objEmpleados);
		}catch (Exception e) {
			mensaje="EMPLEADO NO ELIMINADO";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	
	@GetMapping("/buscarEmpleado")
	@ResponseBody
	public List<EmpleadoResponse> buscarEmpleado(@RequestParam("codempleado") Integer codigo) {
		List <EmpleadoResponse> listempleado = new ArrayList<EmpleadoResponse>();
		listempleado.add(empleadoService.buscarEmpleado(codigo)); 
		return listempleado;
	}
	
	@GetMapping("/buscarEmpleadoxdni")
	@ResponseBody
	public List<EmpleadoResponse> buscarEmpleadoxdni(@RequestParam("dni") String dni) {
		List <EmpleadoResponse> listempleado = new ArrayList<EmpleadoResponse>();
		listempleado.add(empleadoService.buscarEmpleadoxdni(dni)); 
		return listempleado;
	}
	
	@GetMapping("/listarArea")
	@ResponseBody
	public List<AreaEmpleado> listarArea(){
	return areaService.ListarAreaEmpleado();
	}
	
	
	@GetMapping("/listarCargo")
	@ResponseBody
	public List<CargoEmpleado> listarCargo(){
	return cargoService.ListarCargoEmpleado();
	}
	
	
	
}