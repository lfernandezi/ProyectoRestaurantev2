package edu.pe.idat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Usuario;
import edu.pe.idat.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usurepo;
	
	public Usuario buscarUsuario(String email) {
		return usurepo.buscarUsuarioxEmail(email);
	}
}
