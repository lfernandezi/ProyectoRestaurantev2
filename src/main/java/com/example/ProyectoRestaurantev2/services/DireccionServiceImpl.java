package com.example.ProyectoRestaurantev2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoRestaurantev2.model.Direccion;
import com.example.ProyectoRestaurantev2.repository.DireccionRepository;

@Service
public class DireccionServiceImpl implements DireccionService {
	
	@Autowired
	private DireccionRepository dirrepo;
	

	@Override
	public List<Direccion> listar() {
		// TODO Auto-generated method stub
		return dirrepo.findAll();
	}

	@Override
	public List<Direccion> listarxCliente(int codcliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Direccion buscar(int coddireccion) {
		// TODO Auto-generated method stub
		return dirrepo.findById(coddireccion).orElse(null);
	}

	@Override
	public void registrar(Direccion dir) {
		dirrepo.save(dir);
		
	}

	@Override
	public void editar(Direccion dir) {
		dirrepo.saveAndFlush(dir);
		
	}

	@Override
	public void eliminar(int coddireccion) {
		dirrepo.deleteById(coddireccion);
		
	}

}
