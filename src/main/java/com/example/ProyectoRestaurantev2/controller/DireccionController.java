package com.example.ProyectoRestaurantev2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoRestaurantev2.model.Direccion;
import com.example.ProyectoRestaurantev2.model.Empleados;
import com.example.ProyectoRestaurantev2.services.DireccionService;


@RestController
@RequestMapping("/direccion")
public class DireccionController {

	private DireccionService dirserv;
	
	@GetMapping 
	public ResponseEntity<List<Direccion>> listar(){
		return new ResponseEntity <List<Direccion>> (dirserv.listar(), HttpStatus.OK);
	}
	
	@GetMapping ("/listarxcliente/{codcliente}")
	public ResponseEntity<List<Direccion>> listarxArea(@PathVariable ("codcliente") int codcliente){
		if (dirserv.listarxCliente(codcliente).isEmpty()) {
			return new ResponseEntity <List<Direccion>> (new ArrayList<>(), HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity <List<Direccion>> (dirserv.listarxCliente(codcliente), HttpStatus.OK);
		}	
	}
	
	
	
	
	@GetMapping("/buscar")
	public ResponseEntity<Direccion> buscar(@RequestParam("coddireccion")int coddireccion) {
		if(dirserv.buscar(coddireccion)!= null) {
			return new ResponseEntity<Direccion> (dirserv.buscar(coddireccion),HttpStatus.OK);
		}else {
			return new ResponseEntity<Direccion>(new Direccion (),HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Void> registrar(@RequestBody Direccion dir){
		dirserv.registrar(dir);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@PutMapping
	public ResponseEntity <Void> editar (@RequestBody 	Direccion dir){
		if (dirserv.buscar(dir.getCoddireccion())!=null) {
			dirserv.editar(dir);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("eliminar/{coddireccion}")
	public ResponseEntity <Void> eliminar(@PathVariable ("coddireccion")int coddireccion){
		if (dirserv.buscar(coddireccion)!=null) {
			dirserv.eliminar(coddireccion);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
	}
	
}
