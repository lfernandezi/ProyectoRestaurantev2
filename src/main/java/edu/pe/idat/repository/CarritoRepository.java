package edu.pe.idat.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.idat.model.DetallePedido;


public interface CarritoRepository extends JpaRepository<DetallePedido,Integer>{

	
}
