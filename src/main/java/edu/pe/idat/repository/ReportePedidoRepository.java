package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.pe.idat.model.ReportePedido;

@Repository
public interface ReportePedidoRepository extends JpaRepository<ReportePedido, Integer>{
	
	@Query(value = "{call sp_ListarReportePedido(:codpedido)}",
			nativeQuery = true)
			List<ReportePedido> buscarReporte(@Param("codpedido")Integer codpedido);

	
}
