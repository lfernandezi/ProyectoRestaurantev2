package com.example.ProyectoRestaurantev2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ProyectoRestaurantev2.model.Usuario;

import com.example.ProyectoRestaurantev2.services.UsuarioService;

//http://localhost:8087/proyrestaurante/usuario
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService useserv;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		return new ResponseEntity<List<Usuario>>(useserv.listarUsuario(), HttpStatus.OK);
	}

}
