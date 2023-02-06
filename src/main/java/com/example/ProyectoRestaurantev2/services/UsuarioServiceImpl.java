package com.example.ProyectoRestaurantev2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoRestaurantev2.model.Usuario;
import com.example.ProyectoRestaurantev2.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private  UsuarioRepository usurepo;

	@Override
	public Usuario buscarxUsuarioEmail(String email) {
		
		return usurepo.buscarxEmail(email);
	}

	@Override
	public List<Usuario> listarUsuario() {
		// TODO Auto-generated method stub
		return usurepo.findAll();
	}

}
