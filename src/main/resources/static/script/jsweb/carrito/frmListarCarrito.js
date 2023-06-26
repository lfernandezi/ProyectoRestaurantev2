$(document).ready(function() {
	verUsuario();
	ListarCarrito();
});

$(document).on("click", ".btnactualizarcarrito", function() {
	$("#txtnombre").val($(this).attr("data-nombreproducto"));
	$("#txtdescripcion").val($(this).attr("data-descripcion"));
	$("#txtprecio").val($(this).attr("data-precio"));
	$("#txtcantidad").val($(this).attr("data-cantidad"));
	$("#hddcodproducto").val($(this).attr("data-codproducto"));
	$("#modalproducto").modal("show");
});

$(document).on("click", ".btneliminarcarrito", function() {
	$("#mensajeeliminar").text("Esta Seguro(a) de Eliminar el Producto : " +
		$(this).attr("data-nombreproducto") + " con Codigo:" + $(this).attr("data-codproducto") + "?");
	$("#hddcodproductoeliminar").val($(this).attr("data-codproducto"))
	$("#modaleliminarcarrito").modal("show");

});

$(document).on("click", "#acion1", function() {
	var registro = $("#navbarDropdown1").text();
	if (registro == "No ha iniciado sesión") {
		window.open("http://localhost:9080/login", "_self");
	} else {
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/cerrarsesion",
			dataType: "json",
			success: function(resultado) {
				if (resultado) {
					window.open("http://localhost:9080/login", "_self");
				} else {
					alert("Algo falló");
				}

			}
		});
	}

});

$(document).on("click", "#btnagregarcarrito", function() {
	var uno = $("#txtprecio").val();
	var dos = $("#txtcantidad").val();
	var subtotal = uno * dos;

	if ($("#txtcantidad").val() <= 0) {
		$("#errorcantidad").text("Ingresar Valor Mayor a CERO(SOLO ACEPTA NUMEROS)");
	} else {
		$("#errorcantidad").text("");

		$.ajax({
			type: "Post",
			contentType: "application/json",
			url: "/actualizacarrito",
			data: JSON.stringify({
				codproducto: $("#hddcodproducto").val(),
				nombreproducto: $("#txtnombre").val(),
				descripcion: $("#txtdescripcion").val(),
				precio: $("#txtprecio").val(),
				cantidad: $("#txtcantidad").val(),
				subtotal: subtotal

			}),
			success: function(resultado) {
				if (resultado.respuesta) {
					alert(resultado.mensaje);
					//listar nuevamente los productos
					ListarCarrito();
				} else {
					alert(resultado.mensaje);
				}
				$("#modalproducto").modal("hide");
			}

		});
	}
});


$(document).on("click", "#btneliminarcarrito", function() {
	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/eliminardecarrito",
		data: JSON.stringify({
			codproducto: $("#hddcodproductoeliminar").val()
		}),
		success: function(resultado) {
			if (resultado.respuesta) {
				alert(resultado.mensaje);
				ListarCarrito();
			}
			$("#modaleliminarcarrito").modal("hide");
		}
	});
});




$(document).on("click", "#btnirdespacho", function() {
	if ($("#navbarDropdown1").text() == "No ha iniciado sesión") {
		alert("No ha iniciado sesión");
	} else {

		abrirdespacho();
	}

});

function abrirdespacho() {
	window.open("http://localhost:9080/despacho", "_self");
}

function ListarCarrito() {
	var total = 0;
	$.ajax({
		type: "GET",
		url: "/listarcarrito",
		dataType: "json",
		success: function(datos) {
			//console.log(resultado);
			$("#tblcarrito > tbody").html("");
			$("#tblCompra").html("");
			$.each(datos, function(index, value) {
				if (value.codproducto == null || value.codproducto == 0) {
					$("#tblcarrito > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO SELECCIONASTE NINGÚN PRODUCTO </td>" +
						"</tr>"
					);
				} else {
					total = total + value.subtotal;
					$("#tblcarrito > tbody").append(
						"<tr>" +
						"<td>" + value.codproducto + "</td>" +
						"<td>" + value.nombreproducto + "</td>" +
						"<td>" + value.descripcion + "</td>" +
						"<td>" + value.precio + "</td>" +
						"<td>" + value.cantidad + "</td>" +
						"<td>" + value.subtotal + "</td>" +

						"<td><button type='button' class='btn btn-outline-warning btnactualizarcarrito' " +
						" data-codproducto='" + value.codproducto + "'" +
						" data-nombreproducto='" + value.nombreproducto + "'" +
						" data-descripcion='" + value.descripcion + "'" +
						" data-precio='" + value.precio + "'" +
						" data-cantidad='" + value.cantidad + "'" +
						" data-subtotal='" + value.subtotal + "'" +
						">Actualizar</button></td>" +

						"<td><button type='button' class='btn btn-outline-danger btneliminarcarrito' " +
						" data-codproducto='" + value.codproducto + "'" +
						" data-nombreproducto='" + value.nombreproducto + "'" +
						">Eliminar</button></td>" +
						"</tr>"
					);
				}
			});
			var delivery = 0.00;

			if (total > 0) {
				delivery = 5.00;
			}

			var igv = (total) * 0.18;
			var subtotal = total - igv;
			var totaldelivery = total + delivery;

			$("#tblCompra").append(
				"<h6 class='card-title'>SUBTOTAL ..................... s/. " + subtotal.toFixed(2) + "</h6>" +
				"<h6 class='card-title'>IGV .................................. s/. " + igv.toFixed(2) + "</h6><hr />" +
				"<h6 class='card-title'>TOTAL ............................. s/. " + total.toFixed(2) + "</h5>" +
				"<h6 class='card-title'>ENTREGA ......................... s/.  " + delivery.toFixed(2) + "</h6>" +
				"<h5 class='card-title'>TOTAL A PAGAR . s/. " + totaldelivery.toFixed(2) + "</h6>" +

				"<br><div class='center'> <button type='button' class='btn btn-outline-info col-12'	id='btnirdespacho' >FINALIZAR COMPRA</button> </div> " +

				"<br> <a href='/Menus/menu'class='btn btn-outline-success btn-block '>" +
				"Agregar más productos</a>"
			);
		},
		error: function(xhr, status) {

			$("#tblcarrito > tbody").append(

				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");

		}
	});
}

/**
 *
 */