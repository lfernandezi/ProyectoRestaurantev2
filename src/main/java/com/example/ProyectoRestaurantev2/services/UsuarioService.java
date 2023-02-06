package com.example.ProyectoRestaurantev2.services;

import java.util.List;

import com.example.ProyectoRestaurantev2.model.Usuario;

public interface UsuarioService {
	
	Usuario buscarxUsuarioEmail (String email);
	
	List<Usuario> listarUsuario ();

}
