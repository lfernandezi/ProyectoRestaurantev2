package edu.pe.idat.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.repository.AreaEmpleadoRepository;
import edu.pe.idat.repository.CargoEmpleadoRepository;
import edu.pe.idat.repository.EmpleadoRepository;
import edu.pe.idat.model.Empleado;
import edu.pe.idat.model.response.EmpleadoResponse;

@Service
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;

	@Autowired
	AreaEmpleadoRepository areaRepository;

	@Autowired
	CargoEmpleadoRepository cargoRepository;

	/*
	 * public List<Empleado> listarEmpleado(){ return empleadoRepository.findAll();
	 * }
	 */

	public void registrarEmpleado(Empleado empleado) {
		
		if(!empleado.getRole().equals("2")){
			empleado.setRole("ROLE_EMPLEADO");
		}else {
			empleado.setRole("ROLE_ADMIN");
		}
		
		if (empleado.getCodempleado() == 0) {
			empleadoRepository.InsertaEmpleado(empleado.getNombre(), empleado.getApellido(),empleado.getDni(),empleado.getCodcargo(),
					empleado.getContrasenia(), empleado.getFecha_ingreso(), empleado.getCodarea(),empleado.getRole());
		} else {
			empleadoRepository.saveAndFlush(empleado);
		}
	}

	public void eliminarEmpleado(Empleado Empleado) {
		empleadoRepository.deleteById(Empleado.getCodempleado());
	}

	public EmpleadoResponse buscarEmpleado(int codigo) {

		Empleado empl = empleadoRepository.findById(codigo).orElse(null);
		EmpleadoResponse e = new EmpleadoResponse();
		if (Objects.nonNull(empl)) {

			e.setCodempleado(empl.getCodempleado());
			e.setNombre(empl.getNombre());
			e.setApellido(empl.getApellido());
			e.setDni(empl.getDni());
			e.setContrasenia(empl.getContrasenia());
			e.setFecha_ingreso(empl.getFecha_ingreso());
			e.setCodarea(areaRepository.findById(empl.getCodarea()).orElse(null));
			e.setCodcargo(cargoRepository.findById(empl.getCodcargo()).orElse(null));
			e.setRol(empl.getRole());
		}
		return e;

	}

	public EmpleadoResponse buscarEmpleadoxdni(String dni) {

		Empleado empl = empleadoRepository.buscarEmpleado(dni);
		EmpleadoResponse e = new EmpleadoResponse();
		if (Objects.nonNull(empl)) {

			e.setCodempleado(empl.getCodempleado());
			e.setNombre(empl.getNombre());
			e.setApellido(empl.getApellido());
			e.setDni(empl.getDni());
			e.setContrasenia(empl.getContrasenia());
			e.setFecha_ingreso(empl.getFecha_ingreso());
			e.setCodarea(areaRepository.findById(empl.getCodarea()).orElse(null));
			e.setCodcargo(cargoRepository.findById(empl.getCodcargo()).orElse(null));
			e.setRol(empl.getRole());
		}
		return e;
	}

	public List<EmpleadoResponse> listarEmpleadoResponse() {
		List<Empleado> listaini = empleadoRepository.findAll();
		List<EmpleadoResponse> listafin = new ArrayList<EmpleadoResponse>();
		if (!listaini.isEmpty()) {

			for (Empleado empl : listaini) {
				EmpleadoResponse empresponse = new EmpleadoResponse();
				empresponse.setCodempleado(empl.getCodempleado());
				empresponse.setNombre(empl.getNombre());
				empresponse.setApellido(empl.getApellido());
				empresponse.setDni(empl.getDni());
				empresponse.setContrasenia(empl.getContrasenia());
				empresponse.setFecha_ingreso(empl.getFecha_ingreso());
				empresponse.setCodarea(areaRepository.findById(empl.getCodarea()).orElse(null));
				empresponse.setCodcargo(cargoRepository.findById(empl.getCodcargo()).orElse(null));
				empresponse.setRol(empl.getRole());

				listafin.add(empresponse);
			}
		}else {
			EmpleadoResponse empresponse = new EmpleadoResponse();
			empresponse.setCodempleado(0);
			listafin.add(empresponse);
		}
		return listafin;
	}

}
