$(document).ready(function() {
	$("#txtbuscar").hide();
});

$(document).on("change", "#cbobuscar", function() {
	$("#errorbuscar").text("");
	$("#txtbuscar").val("");
	var buscar = $("#cbobuscar").val();
	//alert(idespecialidad);
	if (buscar === "2" || buscar === "3") {
		$("#txtbuscar").show();
		//$("#tblcategoria").html("");
		$("#errorbuscar").show();
	} else {
		$("#txtbuscar").hide();
		$("#errorbuscar").text("");
	}
});

$(document).on("click", "#btnbuscar", function() {
	var buscar = $("#cbobuscar").val();
	var codigo = $("#txtbuscar").val();
	var dnicliente = $("#txtbuscar").val();

	if (buscar === "0") {
		alert("Seleccione una opción");
		$("#errorbuscar").text("");
	} else {
		if (buscar === "1") {
			ListarCliente();
		} else {
			if (buscar === "2") {
				if (codigo == "") {
					$("#errorbuscar").text("Ingresar solo numero");
				} else {
					$("#errorbuscar").text("");
					BuscarCodigo(codigo);
				}
			} else {
				if (dnicliente.length != 8) {
					$("#errorbuscar").text("Ingresar el DNI (MAX. 8 NUMEROS)");
				} else {
					$("#errorbuscar").text("");
					BuscarClientexdni(codigo);
				}
			}
		}
	}

});


$(document).on("click", ".btneliminarcliente", function() {
	$("#mensajeeliminar").text("Está Seguro(a) de deshabilitar el cliente: " +
		$(this).attr("data-xnombre") + " con Codigo :" + $(this).attr("data-codcliente") + "?");
	$("#hddcodclienteeliminar").val($(this).attr("data-xemail"))
	$("#modaleliminarcliente").modal("show");
});

$(document).on("click", "#btneliminarcliente", function() {


	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/eliminarCliente",
		data: JSON.stringify({
			xemail: $("#hddcodclienteeliminar").val(),
			
		}),
		success: function(resultado) {
			if (resultado.respuesta) {
				alert(resultado.mensaje);
				ListarCliente();
			}
			$("#modaleliminarcliente").modal("hide");
		}
	});
});


function ListarCliente() {
	$.ajax({
		type: "GET",
		url: "/listarCliente",
		dataType: "json",
		success: function(data) {
			$("#tblCliente > tbody").html("");
			$.each(data, function(index, value) {
			if (value.codcliente == 0 || value.codcliente == null) {
					$("#tblCliente > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO SE ENCONTRARON CLIENTES </td>" +
						"</tr>");
				} else {
			
				$("#tblCliente > tbody").append("<tr>" +
					"<td>" + value.codcliente + "</td>" +
					"<td class='text-center'>" + value.xnombre + "</td>" +
					"<td class='text-center'>" + value.xapellido + "</td>" +
					"<td class='text-center'>" + value.xdni + "</td>" +
					"<td class='text-center'>" + value.xtelefono + "</td>" +
					"<td class='text-center'>" + value.xdireccion + "</td>" +
					"<td class='text-center'>" + value.xemail + "</td>" +
					"<td class='text-center'>" + value.xestado + "</td>" +
					"<td><button type='button' class='btn btn-outline-warning btnactualizarcliente' " +
					" data-codcliente='" + value.codcliente + "'" +
					
					" data-email='" + value.email + "'" +
					" data-xestado='" + value.xestado + "'" +
					">Inhabilitar</button></td>" +
					"<td><button type='button' class='btn btn-outline-danger btneliminarcliente' " +
					" data-codcliente='" + value.codcliente + "'" +
					
					" data-email='" + value.email + "'" +
					" data-xnombre='" + value.xnombre + "'" +
					" data-xemail='" + value.xemail + "'" +
					">Eliminar</button></td>" +
					"</tr>"
				);
				}
			});
		},
		error: function(xhr, status) {

			$("#tblCliente > tbody").append(

				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");

		}
	});
}


function BuscarCodigo(codigo) {
	$.ajax({
		type: "GET",
		url: "buscarCliente",
		data: {
			codcliente: codigo
		},
		datatype: 'json',
		success: function(data) {
			$("#tblCliente > tbody").html("");
			$.each(data, function(index, value) {
			
			if (value.codcliente == 0 || value.codcliente == null) {
					$("#tblCliente > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO SE ENCONTRARON CLIENTES </td>" +
						"</tr>");
				} else {
				$("#tblCliente > tbody").append("<tr>" +
					"<td>" + value.codcliente + "</td>" +
					"<td class='text-center'>" + value.xnombre + "</td>" +
					"<td class='text-center'>" + value.xapellido + "</td>" +
					"<td class='text-center'>" + value.xdni + "</td>" +
					"<td class='text-center'>" + value.xtelefono + "</td>" +
					"<td class='text-center'>" + value.xdireccion + "</td>" +
					"<td class='text-center'>" + value.xemail + "</td>" +
					"<td class='text-center'>" + value.xestado + "</td>" +
					"<td><button type='button' class='btn btn-outline-warning btnactualizarcliente' " +
					" data-codcliente='" + value.codempleado + "'" +
					" data-xestado='" + value.xestado + "'" +
					">Inhabilitar</button></td>" +
					"<td><button type='button' class='btn btn-outline-danger btneliminarcliente' " +
					" data-codcliente='" + value.codcliente + "'" +
					" data-xnombre='" + value.xnombre + "'" +
					" data-xemail='" + value.xemail + "'" +
					">Eliminar</button></td>" +
					"</tr>"
				);
				}
			})
		},
		error: function(xhr, status) {

			$("#tblCliente > tbody").append(

				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");

		}
	});
}


function BuscarClientexdni(codigo) {
	$.ajax({
		type: "GET",
		url: "buscarClientexdni",
		data: {
			dni: codigo
		},
		datatype: 'json',
		success: function(data) {
			$("#tblCliente > tbody").html("");
			$.each(data, function(index, value) {
				if (value.codcliente == 0 || value.codcliente == null) {
					$("#tblCliente > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO SE ENCONTRARON CLIENTES </td>" +
						"</tr>");
				} else {
					$("#tblCliente > tbody").append("<tr>" +
						"<td>" + value.codcliente + "</td>" +
						"<td class='text-center'>" + value.xnombre + "</td>" +
						"<td class='text-center'>" + value.xapellido + "</td>" +
						"<td class='text-center'>" + value.xdni + "</td>" +
						"<td class='text-center'>" + value.xtelefono + "</td>" +
						"<td class='text-center'>" + value.xdireccion + "</td>" +
						"<td class='text-center'>" + value.xemail + "</td>" +
						"<td class='text-center'>" + value.xestado + "</td>" +
						"<td><button type='button' class='btn btn-outline-warning btnactualizarcliente' " +
						" data-codcliente='" + value.codempleado + "'" +
						" data-xestado='" + value.xestado + "'" +
						">Inhabilitar</button></td>" +
						"<td><button type='button' class='btn btn-outline-danger btneliminarcliente' " +
						" data-codcliente='" + value.codcliente + "'" +
						" data-xnombre='" + value.xnombre + "'" +
						" data-xemail='" + value.xemail + "'" +
						">Eliminar</button></td>" +
						"</tr>"
					);
				}
			})
		},

		error: function(xhr, status) {

			$("#tblCliente > tbody").append(

				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");

		}
	});
}


$(document).on("click", ".btnactualizarestado", function() {
	$("#txtnombre").val($(this).attr("data-xnombre"));
	$("#txtapellido").val($(this).attr("data-xapellido"));
	$("#modalcambiarestado").modal("show");

});


