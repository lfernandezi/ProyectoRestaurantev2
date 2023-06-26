$(document).ready(function() {
listarpedido();
});

function listarpedido() {
	$.ajax({
		type: "GET",
		url: "/buscarPedidoporCliente2",
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

					new Date(value.fechacreacion);

					$("#tblpedido > tbody").append(
						"<tr>" +
						"<td>" + value.codpedido + "</td>" +
						//"<td>" + value.fechacreacion+ "</td>" +

						"<td>" + new Date(value.fechacreacion).toLocaleString() + "</td>" +
						
						"<td>" + value.direccion + "</td>" +
						"<td>" + value.referencia + "</td>" +
						"<td>" + value.monto + "</td>" +
						"<td>" + value.estado + "</td>" +

						"<td><input type='image' align='center' src='/img/ingresar.jpg' width='60' height='40'" +
						"class='btnverpedido'data-codpedido='" + value.codpedido + "'" +
						"data-codcliente='" + value.codcliente + "'" + "></td>"

						
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

$(document).on("click", ".btnverpedido", function() {
	var codigo = $(this).attr("data-codpedido");
	VerDetallePedido(codigo);
	$("#modalpedido").modal("show");

});

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
			$("#btnimprimirdetallecocina").val(codigo);
			$("#cabecera").append(

				"<div class='form-group row'>" +
				"<label for='staticEmail' class='col-sm-2 col-form-label'>Nro Pedido</label>" +
				"<div class='col-sm-10'><input type='text' readonly class='form-control-plaintext'" +
				" value='" + codigo + "'></div></div>"

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

