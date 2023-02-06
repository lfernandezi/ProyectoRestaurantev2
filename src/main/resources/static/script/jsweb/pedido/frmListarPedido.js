$(document).ready(function() {
	listarpedido();
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
	} else if (est == "1") {
		var estado = "En Preparación";
		ActualizarEstadoPedido(codigo, estado);
		listarpedido();
	} else {
		var estado = "En Ruta";
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
			//console.log(resultado);
			$("#tblpedido > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#tblpedido > tbody").append(
					"<tr>" +
					"<td>" + value.codpedido + "</td>" +
					"<td>" + value.fechacreacion + "</td>" +
					"<td>" + value.codcliente + "</td>" +
					"<td>" + value.direccion + "</td>" +
					"<td>" + value.subtotal + "</td>" +
					"<td>" + value.igv + "</td>" +
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

			});
		}
	});
}




function VerDetallePedido(codigo) {
	$.ajax({
		type: "GET",
		url: "listarDetallePedido",
		data: {
			codpedido: codigo
		},
		datatype: 'jsonp',
		success: function(data) {
			$("#tbldetallepedido > tbody").html("");
			$.each(data, function(index, valu) {
				$("#tbldetallepedido > tbody").append("<tr>" +
					"<td>" + valu.codpedido + "</td>" +
					"<td class='text-center'>" + valu.codproducto + "</td>" +
					"<td class='text-center'>" + valu.nombreproducto + "</td>" +
					"<td class='text-center'>" + valu.descripcion + "</td>" +
					"<td class='text-center'>" + valu.cantidad + "</td>" +
					"<td class='text-center'>" + valu.precio + "</td>" +
					"<td class='text-center'>" + valu.subtotal + "</td>" +

					"</tr>"
				);
			})
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