//$(document).ready(function() {
//listarpedido();
//});

$(document).ready(function() {
	$("#txtbuscarpedido").hide();
	$("#cbobuscarporestado").hide();
});


$(document).on("change", "#cbobuscarpedido", function() {
	$("#errorbuscar").text("");
	$("#txtbuscarpedido").val("");
	var buscar = $("#cbobuscarpedido").val();
	if (buscar === "3" || buscar === "4") {
		$("#cbobuscarporestado").hide();
		$("#txtbuscarpedido").show();
		$("#errorbuscar").show();
	} else {
		if (buscar === "2") {
			$("#cbobuscarporestado").show();
			$("#errorbuscar").show();
			$("#txtbuscarpedido").hide();
		}else{
		$("#cbobuscarporestado").hide();
			$("#errorbuscar").show();
			$("#txtbuscarpedido").hide();
			}
	}
});

$(document).on("click", "#btnbuscarpedido", function() {
	var buscar = $("#cbobuscarpedido").val();
	var codigo = $("#txtbuscarpedido").val();
	var estado = $("#cbobuscarporestado").val();


	if (buscar === "0") {
		alert("Seleccione una opción");
		$("#errorbuscar").text("");
	} else {
		if (buscar === "1") {
		
		$("#txtbuscarpedido").hide();
			$("#errorbuscarpedido").text("");
			listarpedido();
		} else {
			if (buscar === "2") {
			$("#txtbuscarpedido").hide();
				if (estado == "0") {
					alert("Seleccione una opción");
				} else {
					$("#errorbuscarpedido").text("");
					buscarPedidoporEstado(estado);
				}
			} else {
				if (codigo == "") {
						$("#errorbuscarpedido").text("Ingresar datos de búsqueda");
					
				} else {
					if (buscar === "3") {
					$("#errorbuscarpedido").text("");
						alert("Buscando pedido n. " + codigo);
						buscarPedidoporCodigo(codigo);

					} else {
						$("#errorbuscarpedido").text("");
						alert("Buscando por código de cliente n. " + codigo);
						buscarPedidoporCliente(codigo);
						
					}
				}
			}
		}
	}

});


$(document).on("click", ".btnverpedido", function() {
	var codigo = $(this).attr("data-codpedido");
	VerDetallePedido(codigo);
	$("#modalpedido").modal("show");

});

$(document).on("click", ".btnactualizarpedido", function() {
	var codigo = $(this).attr("data-codpedido");
	$("#hddcodpedidoactualizar").val($(this).attr("data-codpedido"));
	$("#modalactualizarpedido").modal("show");


});

$(document).on("click", "#btnActualizarEstadoPedido", function() {
	var estado = "";
	var codigo = $("#hddcodpedidoactualizar").val();
	var est = $("#cboEstadoPedido").val();
	if (est == "0") {
		alert("Escoja una opción");
	} else {
		estado = $("#cboEstadoPedido").val();

		ActualizarEstadoPedido(codigo, estado);
		listarpedido();
	}

});


function listarpedido() {
	$.ajax({
		type: "GET",
		url: "/listarPedido",
		dataType: "json",
		success: function(resultado) {
			
			$("#tblpedido > tbody").html("");
			$.each(resultado, function(index, value) {
			
			if (value.codpedido == 0 || value.codpedido == null) {
					$("#tblpedido > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO SE ENCONTRARON PEDIDOS </td>" +
						"</tr>");
				} else {
				
				new Date (value.fechacreacion);
				
				$("#tblpedido > tbody").append(
					"<tr>" +
					"<td>" + value.codpedido + "</td>" +
					//"<td>" + value.fechacreacion+ "</td>" +
					
					"<td>" + new Date (value.fechacreacion).toLocaleString()+ "</td>" +
					"<td>" + value.codcliente + "</td>" +
					"<td>" + value.direccion + "</td>" +
					"<td>" + value.monto + "</td>" +
					"<td>" + value.estado + "</td>" +

					"<td><input type='image' align='center' src='/img/ingresar.jpg' width='60' height='40'" +
					"class='btnverpedido'data-codpedido='" + value.codpedido + "'" +
					"data-codcliente='" + value.codcliente + "'" + "></td>" +

					"<td><input type='image' align='center' src='/img/actualizar.jpg' width='60' height='40'" +
					"class='btnactualizarpedido' data-codpedido='" + value.codpedido + "'" +
					"data-codcliente='" + value.codcliente + "'" + "></td>" +
					"</tr>");
					}
			});
		},
		error: function(xhr, status) {
			$("#tblpedido > tbody").append(
				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");
		}
	});
}

function buscarPedidoporEstado(_estado) {

	$.ajax({
		type: "GET",
		url: "/buscarPedidoporEstado",
		data: {
			estado: _estado
		},
		dataType: "json",
		success: function(resultado) {
			$("#tblpedido > tbody").html("");
			$.each(resultado, function(index, value) {
				if (value.codpedido == 0 || value.codpedido == null) {
					$("#tblpedido > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO SE ENCONTRARON PEDIDOS </td>" +
						"</tr>");
				} else {
					$("#tblpedido > tbody").append(
						"<tr>" +
						"<td>" + value.codpedido + "</td>" +
						"<td>" + value.fechacreacion + "</td>" +
						"<td>" + value.codcliente + "</td>" +
						"<td>" + value.direccion + "</td>" +
						"<td>" + value.monto + "</td>" +
						"<td>" + value.estado + "</td>" +

						"<td><input type='image' align='center' src='/img/ingresar.jpg' width='60' height='40'" +
						"class='btnverpedido'data-codpedido='" + value.codpedido + "'" +
						"data-codcliente='" + value.codcliente + "'" + "></td>" +

						"<td><input type='image' align='center' src='/img/actualizar.jpg' width='60' height='40'" +
						"class='btnactualizarpedido' data-codpedido='" + value.codpedido + "'" +
						"data-codcliente='" + value.codcliente + "'" + "></td>" +
						"</tr>"

					);
				}
			});
		},
		error: function(xhr, status) {
			$("#tblpedido > tbody").append(
				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");
		}
	});
}

function buscarPedidoporCodigo(codigopedido) {

	$.ajax({
		type: "GET",
		url: "/buscarPedidoporCodigo",
		data: {
			codpedido: codigopedido
		},
		dataType: "json",
		success: function(resultado) {
			$("#tblpedido > tbody").html("");
			$.each(resultado, function(index, value) {
				if (value.codpedido == 0 || value.codpedido == null) {
					$("#tblpedido > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO SE ENCONTRARON PEDIDOS </td>" +
						"</tr>");
				} else {
					$("#tblpedido > tbody").append(
						"<tr>" +
						"<td>" + value.codpedido + "</td>" +
						"<td>" + value.fechacreacion + "</td>" +
						"<td>" + value.codcliente + "</td>" +
						"<td>" + value.direccion + "</td>" +
						"<td>" + value.monto + "</td>" +
						"<td>" + value.estado + "</td>" +

						"<td><input type='image' align='center' src='/img/ingresar.jpg' width='60' height='40'" +
						"class='btnverpedido'data-codpedido='" + value.codpedido + "'" +
						"data-codcliente='" + value.codcliente + "'" + "></td>" +

						"<td><input type='image' align='center' src='/img/actualizar.jpg' width='60' height='40'" +
						"class='btnactualizarpedido' data-codpedido='" + value.codpedido + "'" +
						"data-codcliente='" + value.codcliente + "'" + "></td>" +
						"</tr>"

					);
				}
			});
		},
		error: function(xhr, status) {
			$("#tblpedido > tbody").append(
				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");
		}
	});
}

function buscarPedidoporCliente(codigocliente) {

	$.ajax({
		type: "GET",
		url: "/buscarPedidoporCliente",
		data: {
			codcliente: codigocliente
		},
		dataType: "json",
		success: function(resultado) {
			$("#tblpedido > tbody").html("");
			$.each(resultado, function(index, value) {
				if (value.codpedido == 0 || value.codpedido == null) {
					$("#tblpedido > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO SE ENCONTRARON PEDIDOS </td>" +
						"</tr>");
				} else {
					$("#tblpedido > tbody").append(
						"<tr>" +
						"<td>" + value.codpedido + "</td>" +
						"<td>" + value.fechacreacion + "</td>" +
						"<td>" + value.codcliente + "</td>" +
						"<td>" + value.direccion + "</td>" +
						"<td>" + value.monto + "</td>" +
						"<td>" + value.estado + "</td>" +

						"<td><input type='image' align='center' src='/img/ingresar.jpg' width='60' height='40'" +
						"class='btnverpedido'data-codpedido='" + value.codpedido + "'" +
						"data-codcliente='" + value.codcliente + "'" + "></td>" +

						"<td><input type='image' align='center' src='/img/actualizar.jpg' width='60' height='40'" +
						"class='btnactualizarpedido' data-codpedido='" + value.codpedido + "'" +
						"data-codcliente='" + value.codcliente + "'" + "></td>" +
						"</tr>"

					);
				}
			});
		},
		error: function(xhr, status) {
			$("#tblpedido > tbody").append(
				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");
		}
	});
}



function VerDetallePedido(codigo) {

	$.ajax({
		type: "GET",
		url: "/listarDetallePedido",
		data: {
			codpedido: codigo
		},
		datatype: 'json',
		success: function(data) {
		$("#cabecera").html("");
		$("#cabecera").append(
		
		"<div class='form-group row'>"+
		"<label for='staticEmail' class='col-sm-2 col-form-label'>Nro Pedido</label>"+
		"<div class='col-sm-10'><input type='text' readonly class='form-control-plaintext'"+
		" value='"+codigo+"'></div></div>"
		
		);
		
			$("#tbldetallepedido > tbody").html("");
			$.each(data, function(index, valu) {

				$("#tbldetallepedido > tbody").append("<tr>" +
					"<td>" + valu.codpedido + "</td>" +
					"<td class='text-center'>" + valu.codproducto + "</td>" +
					"<td class='text-center'>" + valu.nombreproducto + "</td>" +
					//"<td class='text-center'>" + valu.descripcion + "</td>" +
					"<td class='text-center'>" + valu.cantidad + "</td>" +
					"<td class='text-center'>" + valu.precio + "</td>" +
					"<td class='text-center'>" + valu.subtotal + "</td>" +
					"</tr>"

				);
			});
		}
	});
}

function ActualizarEstadoPedido(codigo, estado) {
	$.ajax({
		type: "POST",
		url: "actualizarEstadoPedido",
		data: {
			codpedido: codigo,
			estado: estado
		},
		datatype: 'jsonp',
		success: function(data) {
			if (data.respuesta) {
				alert(data.mensaje);
			}
			$("#modalactualizarpedido").modal("hide");
			listarpedido();
		}
	});
}