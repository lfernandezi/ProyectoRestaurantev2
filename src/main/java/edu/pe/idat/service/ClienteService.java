package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.repository.ClienteRepository;
import edu.pe.idat.model.Cliente;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienterepository;

	public List<Cliente> listarCliente() {

		return clienterepository.findAll();
	}

	public void RegistrarCliente(Cliente cliente) {
		clienterepository.registrarCliente(cliente.getXdni(), cliente.getXnombre(), cliente.getXapellido(),
				cliente.getXemail(), cliente.getXcontrasenia(), cliente.getXdireccion(), cliente.getXtelefono(),
				"ACTIVO");
	}
	
	public void EliminarCliente (Cliente cliente) {
		clienterepository.eliminarCliente(cliente.getCodcliente());		
		}
	
	/*public ClienteForm buscarcliente(Integer codigo) {
        return clienterepository.buscarCliente(codigo);
    }*/
	
	public Cliente buscarcliente(Integer codigo) {
        return clienterepository.findById(codigo).orElse(null);
    }
	
	public List<Cliente> buscarclientexdni(String dni) {
		return clienterepository.buscarporDni(dni);
		}
	
	public void ActualizarCliente(Cliente cliente) {
		clienterepository.registrarCliente(cliente.getXdni(), cliente.getXnombre(), cliente.getXapellido(),
				cliente.getXemail(), cliente.getXcontrasenia(), cliente.getXdireccion(), cliente.getXtelefono(),
				"ACTIVO");
	}

}