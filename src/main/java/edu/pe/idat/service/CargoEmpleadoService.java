package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.CargoEmpleado;
import edu.pe.idat.repository.CargoEmpleadoRepository;

@Service
public class CargoEmpleadoService {
	
	@Autowired
	CargoEmpleadoRepository cargorepository;
	
	public List<CargoEmpleado> ListarCargoEmpleado(){
		return cargorepository.findAll();
		}

	public CargoEmpleado buscarCargo(Integer codigo) {
		return cargorepository.findById(codigo).orElse(null);
	}
	
}
