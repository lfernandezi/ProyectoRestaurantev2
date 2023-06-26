$(document).ready(function() {
	var total = 0;
	/*$.ajax({
		type: "GET",
		url: "/listarDetallePedido",
		data: {
			codpedido:$("#txtpedido").val()
			},
		success: function(datos) {
			//console.log(resultado);
			$("#tbldetallepedido > tbody").html("");
			$("#tblCompra").html("");
			$.each(datos, function(index, value) {
				total = total + value.subtotal;
				$("#tbldetallepedido > tbody").append(
					"<tr>" +
					"<td>" + value.codproducto + "</td>" +
					"<td>" + value.nombreproducto + "</td>" +
				
					"<td>" + value.precio + "</td>" +
					"<td>" + value.cantidad + "</td>" +
					"<td>" + value.subtotal + "</td>" +

					"</tr>"
				);
			});
			var delivery = 0.00;

			if (total > 0) {
				delivery = 5.00;
			}
			var igv = (total) * 0.18;
			var subtotal = total-igv;
			var totaldelivery = total + delivery;

			$("#tblCompra").append(
				"<h6 id='txtsubtotal' class='card-title'>SUBTOTAL .......................... s/. " + subtotal.toFixed(2) + "</h6>" +
				"<h6 id='txtigv'class='card-title'>IGV ....................................... s/. " + igv.toFixed(2) + "</h6><hr />" +
				"<h6 id='txttotal'class='card-title'>TOTAL .................................. s/. " + total.toFixed(2) + "</h6>" +
				"<h6 id='txtentrega'class='card-title'>ENTREGA .............................. s/. " + delivery.toFixed(2) + "</h6>" +
				"<h5 id='txtmonto'class='card-title'>TOTAL A PAGAR ... s/. " + totaldelivery.toFixed(2) + "</h5>"
			);
			$("#txtsubtotal").val(total.toFixed(2) + delivery);
			$("#txtigv").val(igv.toFixed(2));
			$("#txtmonto").val(totaldelivery.toFixed(2));
		}
	});*/
	
	$("#cardDetalle").hide();
	$("#tarjetadetalle").hide();

});

$(document).on("click", "#btnverPedidoNuevo", function() {

	ListarUltimoPedido();
	$("#tarjetadetalle").show();
	

});

$(document).on("click", "#btnverdetallepedidonuevo", function() {
	var numepedido= $("#mensaje10").val();
	alert (numepedido);
	ListarDetalle(numepedido);
	$("#cardDetalle").show();
});

function ListarUltimoPedido() {
	var codigo= 0;
	$.ajax({
		type: "GET",
		url: "/buscarUltimoPedido",
		dataType: "json",
		success: function(datos) {
			$("#cardCliente").html("");
			$.each(datos, function(index, value) {
				$("#cardCliente").append(
					"<div class='row g-3'> " +
					"<div class='col'> " +
					"<label for='txtpedido'> Numero Pedido :</label> " +
					"<input type='text'  class='form-control' value= '" + value.codpedido + "' id='txtpedido' readonly> " +
					"<label for='txtfecha'> Fecha de Creacion:</label> " +
					"<input type='text' value= '" + value.fechacreacion + "' class='form-control' id='txtfecha' readonly> " +
					"<label for='txtmonto'> Monto :</label> " +
					"<input type='number' class='form-control' id='txtmonto' value= '" + value.monto + "' readonly> " +
					"<label for='txtdireccion'>Dirección de Entrega:</label> " +
					"<input type='text' value= '" + value.direccion + "' class='form-control' id='txtdireccion' readonly> " +
					"<label for='txtreferencia'>REFERENCIA:</label> " +
					"<input type='text' class='form-control' value= '" + value.referencia + "' id='txtreferencia' readonly> " +
					"<label for='txtestado'>Estado del Pedido:</label> " +
					"<input type='text' value= '" + value.estado + "' class='form-control' id='txtestado' readonly> <br>" +
					"</div>" +
					"</div>"
				);
				
				codigo= codigo + value.codpedido;
				$("#mensaje10").val(codigo);
			});
		}
	});

}

function ListarDetalle(numepedido) {
	var total = 0;
	$.ajax({
		type: "GET",
		url: "/listarDetallePedido",
		data: {
			codpedido: numepedido 
		},
		success: function(datos) {
			//console.log(resultado);
			$("#tbldetallepedido > tbody").html("");
			$("#tblCompra").html("");
			$.each(datos, function(index, value) {
				total = total + value.subtotal;
				$("#tbldetallepedido > tbody").append(
					"<tr>" +
					"<td>" + value.codproducto + "</td>" +
					"<td>" + value.nombreproducto + "</td>" +

					"<td>" + value.precio + "</td>" +
					"<td>" + value.cantidad + "</td>" +
					"<td>" + value.subtotal + "</td>" +

					"</tr>"
				);
			});
			var delivery = 0.00;

			if (total > 0) {
				delivery = 5.00;
			}
			var igv = (total) * 0.18;
			var subtotal = total - igv;
			var totaldelivery = total + delivery;

			$("#tblCompra").append(
				"<h6 id='txtsubtotal' class='card-title'>SUBTOTAL .......................... s/. " + subtotal.toFixed(2) + "</h6>" +
				"<h6 id='txtigv'class='card-title'>IGV ....................................... s/. " + igv.toFixed(2) + "</h6><hr />" +
				"<h6 id='txttotal'class='card-title'>TOTAL .................................. s/. " + total.toFixed(2) + "</h6>" +
				"<h6 id='txtentrega'class='card-title'>ENTREGA .............................. s/. " + delivery.toFixed(2) + "</h6>" +
				"<h5 id='txtmonto'class='card-title'>TOTAL A PAGAR ... s/. " + totaldelivery.toFixed(2) + "</h5>"
			);
			$("#txtsubtotal").val(total.toFixed(2) + delivery);
			$("#txtigv").val(igv.toFixed(2));
			$("#txtmonto").val(totaldelivery.toFixed(2));
		}
	});
}
