package com.example.ProyectoRestaurantev2.services;

import java.util.List;

import com.example.ProyectoRestaurantev2.model.Direccion;

public interface DireccionService {

	List<Direccion> listar ();
	
	List<Direccion> listarxCliente(int codcliente);
	
	Direccion buscar(int coddireccion);
	
	void registrar (Direccion dir);
	
	void editar (Direccion dir);
	
	void eliminar (int coddireccion);
	
}
