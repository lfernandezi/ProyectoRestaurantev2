$(document).ready(function() {
	$("#contenedor4").hide();
	BuscarCliente();
	ListarCarrito();
	$("#btnregistrartarjeta").hide();

});

$(document).on("click", "#btnmodificardireccion", function() {
	$("#txtdireccion2").val($(this).attr("data-direccion"));
	$("#modaldireccion").modal("show");
});

$(document).on("click", "#btncambiardireccion", function() {
	var nuevadireccion = $("#txtdireccion2").val();
	if (nuevadireccion === "") {
		alert("No se puede enviar sin dirección");
	} else {
		$("#txtdireccion").val($("#txtdireccion2").val());
		$("#modaldireccion").modal("hide");
	}
});

$(document).on("change", "#cbomomento", function() {
	$("#errormomento").text("");
	$("#errorforma").text("");
	var momento = $("#cbomomento").val();

	if (momento === "2") {
		$("#lblforma").hide();
		$("#cboforma").hide();
		$("#btningresarpedido").hide();
		$("#btnregistrartarjeta").show();

	} else {
		$("#btnregistrartarjeta").hide();
		$("#lblforma").show();
		$("#cboforma").show();
		$("#btningresarpedido").show();
	}
});

$(document).on("change", "#cboforma", function() {
	$("#errorforma").text("");
	$("#errormomento").text("");

});

$(document).on("click", "#btnregistrartarjeta", function() {
	$("#txtclaveseguridad").val("");
	$("#txtnumerotarjeta").val("");
	$("#txtfechaexpiracion").val("");
	$("#txtmonto3").val($("#txtmonto").val());
	$("#modaltarjeta").modal("show");
});

$(document).on("click", "#btnvalidartarjeta", function() {

	Hoy = new Date();
	var valida = 0;
	var clave = $("#txtclaveseguridad").val();
	var tarjeta = $("#txtnumerotarjeta").val();
	fecha = new Date($("#txtfechaexpiracion").val());

	if (isNaN(fecha)) {
		$("#errorfecha").text("Ingrese fecha");
		valida = 1;
	} else {
		if (fecha < Hoy) {
			$("#errorfecha").text("Su tarjeta ha expirado");
			valida = 2;
		} else {
			$("#errorfecha").text("");
		}
	}

	if (clave.length != 3 || clave === "") {
		$("#errorclave").text("Dato incorrecto");
		valida = 3;
	} else {
		$("#errorclave").text("");
	}

	if (tarjeta.length != 16) {
		$("#errortarjeta").text("Numero de tarjeta inválido");
		valida = 4;
	}
	else {
		$("#errortarjeta").text("");
	}

	if (valida === 0) {
		IngresarPedido();
		$("#modaltarjeta").modal("hide");
		$("#contenedor1").hide();
        $("#contenedor2").hide();
        $("#contenedor3").hide();
        $("#contenedor4").show();
	} else {
		alert("Datos incorrectos");
	}
});

$(document).on("click", "#btningresarpedido", function() {

	var valida = 0;
	if ($("#cbomomento").val() === "0") {
		$("#errormomento").text("Escoja momento de pago");
		valida = 1;
	} else {
		$("#errormomento").text("");
	}
	if ($("#cboforma").val() === "0") {
		$("#errorforma").text("Escoja forma de pago");
		valida = 2;
	} else {
		$("#errorforma").text("");
	}

	if (valida > 0) {
		alert("Escoja los campos señalados");
	} else {
		IngresarPedido();
		$("#contenedor1").hide();
		$("#contenedor2").hide();
		$("#contenedor3").hide();
		$("#contenedor4").show();
	}

});

function IngresarPedido() {
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/ingresarPedido",
		data: JSON.stringify({
			codcliente: $("#txtcodigo").val(),
			direccion: $("#txtdireccion").val(),
			subtotal: $("#txtsubtotal").val(),
			igv: $("#txtigv").val(),
			monto: $("#txtmonto").val()
		}),
		success: function(resultado) {
			if (resultado.respuesta) {
				alert(resultado.mensaje);
				//listar nuevamente los productos
			} else {
				alert(resultado.mensaje);
			}

		}
	});
}

function BuscarCliente() {
	var total = 0;
	$.ajax({
		type: "GET",
		url: "/listarcliente",
		dataType: "json",
		success: function(datos) {
			$("#tblClienteFinal").html("");
			$.each(datos, function(index, value) {
				$("#tblClienteFinal").append(
					" <input type='hidden' class='form-control' id='txtcodigo' value='" +
					value.codcliente + "'>" +
					"</div>" +
					"<div class='row'>" +
					"<div class='col'>" +
					"<label for='txtnombre'>Nombres :</label>" +
					" <input type='text' class='form-control' id='txtnombre' value='" +
					value.xnombre + "' readonly>" +
					"</div>" +
					"<div class='col'>" +
					"<label for='txtapellido'>Apellidos :</label>" +
					" <input type='text' class='form-control' id='txtapellido'value='" +
					value.xapellido + "' readonly>" +
					"</div> " +
					"</div> <br />" +
					"<div class='row'>" +
					"<div class='col'>" +
					"<label for='txttelefono'>Teléfono :</label>" +
					" <input type='text' class='form-control' id='txttelefono'value='" +
					value.xtelefono + "' readonly>" +
					"</div> " +
					"</div> <br />" +
					"<div class='row'>" +
					"<div class='col'>" +
					"<label for='txtdireccion'>Dirección :</label>" +
					"</div> " +
					"</div>" +
					"<div class='row'>" +
					"<div class='col'>" +
					" <input type='text' class='form-control' id='txtdireccion'value='" +
					value.xdireccion + "' readonly> <br />" +
					" <button type='button' class='btn btn-outline-success' " +
					"id='btnmodificardireccion' " +
					" data-direccion='" + value.xdireccion + "'" +
					">Cambiar dirección</button>" +
					"</div> " +
					"</div>"
				);

			});
		}
	});
}

function ListarCarrito() {
	var total = 0;
	$.ajax({
		type: "GET",
		url: "/listarcarrito2",
		dataType: "json",
		success: function(datos) {
			//console.log(resultado);
			$("#tblcarrito > tbody").html("");
			$("#tblCompra").html("");
			$.each(datos, function(index, value) {
				total = total + value.subtotal;
				$("#tblcarrito > tbody").append(
					"<tr>" +
					"<td>" + value.codproducto + "</td>" +
					"<td>" + value.nombreproducto + "</td>" +
					"<td>" + value.descripcion + "</td>" +
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
			var igv = (total + delivery) * 0.18;
			var totaldelivery = total + igv + delivery;

			$("#tblCompra").append(
				"<h6 id='txtsubtotal' class='card-title'>SUBTOTAL .......... s/. " + total.toFixed(2) + "</h6>" +
				"<h6 id='txtentrega'class='card-title'>ENTREGA ............ s/. " + delivery.toFixed(2) + "</h6>" +
				"<h6 id='txtigv'class='card-title'>IGV ....................... s/. " + igv.toFixed(2) + "</h6><hr />" +
				"<h5 id='txtmonto'class='card-title'>TOTAL .......... s/. " + totaldelivery.toFixed(2) + "</h5>"
			);
			$("#txtsubtotal").val(total.toFixed(2) + delivery);
			$("#txtigv").val(igv.toFixed(2));
			$("#txtmonto").val(totaldelivery.toFixed(2));
		}
	});

}