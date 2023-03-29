package edu.pe.idat.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.repository.ClienteRepository;
import edu.pe.idat.model.Cliente;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienterepository;

	public List<Cliente> listarCliente() {
		List<Cliente> lsct=clienterepository.findAll();
		
		if(lsct.isEmpty()) {
			Cliente c=new Cliente();
			c.setCodcliente(0);
			
			lsct.add(c);
		}
		return lsct;
	}

	public void RegistrarCliente(Cliente cliente) {
		clienterepository.registrarCliente(cliente.getXdni(), cliente.getXnombre(), cliente.getXapellido(),
				cliente.getXemail(), cliente.getXcontrasenia(), cliente.getXtelefono(), cliente.getXdireccion(),
				"ACTIVO");
	}

	public void EliminarCliente(Cliente cliente) {
		clienterepository.eliminarCliente(cliente.getXemail());
	}

	public Cliente buscarcliente(int codigo) {
		Cliente cbd = clienterepository.findById(codigo).orElse(null);
		if (Objects.isNull(cbd)) {
			Cliente c=new Cliente();
			c.setCodcliente(0);
			return c;
		} else {
			return cbd;
		}
	}

	public List<Cliente> buscarclientexdni(String dni) {
		List<Cliente> liscbd = clienterepository.buscarporDni(dni);
		if (liscbd.isEmpty()) {
			Cliente c = new Cliente();
			c.setCodcliente(0);

			liscbd.add(c);
		}

		return liscbd;
	}
	
	public Cliente buscarclientexEmail(String email) {
		Cliente cbd = clienterepository.buscarClienteEmail(email);
		if (Objects.isNull(cbd)) {
			Cliente c=new Cliente();
			c.setCodcliente(0);
			return c;
		} else {
			return cbd;
		}

		
	}

	public void ActualizarCliente(Cliente cliente) {

		clienterepository.actualizarCliente(cliente.getCodcliente(), cliente.getXdni(), cliente.getXnombre(),
				cliente.getXapellido(), cliente.getXemail(), cliente.getXcontrasenia(), cliente.getXtelefono(),
				cliente.getXdireccion(), "ACTIVO");
	}

}