package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.AreaEmpleado;
import edu.pe.idat.repository.AreaEmpleadoRepository;

@Service
public class AreaEmpleadoService {
	
	@Autowired
	AreaEmpleadoRepository arearepository;
	
	public List<AreaEmpleado> ListarAreaEmpleado(){
		return arearepository.findAll();
		}
	
	public AreaEmpleado buscarArea(Integer codigo) {
		return arearepository.findById(codigo).orElse(null);
	}

}
