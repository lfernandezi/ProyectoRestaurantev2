$(document).ready(function() {
	$("#txtbuscarcategoria").hide();
});

$(document).on("change", "#cbobuscarcategoria", function() {
	$("#tblCategoria > tbody").html("");
	$("#errorbuscarcategoria").text("");
	$("#txtbuscarcategoria").val("");
	var buscarcategoria = $("#cbobuscarcategoria").val();
	if (buscarcategoria === "2" || buscarcategoria === "3") {
		$("#txtbuscarcategoria").show();
		$("#errorbuscarcategoria").show();
	} else {
		$("#txtbuscarcategoria").hide();
		$("#errorbuscarcategoria").text("");
	}
});

$(document).on("click", "#btnbuscarcategoria", function() {
	$("#tblCategoria > tbody").html("");
	var buscarcategoria = $("#cbobuscarcategoria").val();
	var codigo = $("#txtbuscarcategoria").val();
	var palabra = $("#txtbuscarcategoria").val();

	if (buscarcategoria === "0") {
		alert("Seleccione una opción");
		$("#errorbuscarcategoria").text("");
	} else {
		if (buscarcategoria === "1") {
			ListarCategoria();
		} else {
			if (buscarcategoria === "2") {
				if (codigo == "") {
					$("#errorbuscarcategoria").text("Ingresar solo numero");
				} else {
					$("#errorbuscarcategoria").text("");
					BuscarcategoriaxCodigo(codigo);
				}
			} else {
				if (dniempleado.length != 8) {
					$("#errorbuscarcategoria").text("Ingresar el DNI (MAX. 8 NUMEROS)");
				} else {
					$("#errorbuscarcategoria").text("");
					BuscarEmpleadoxdni(codigo);
				}
			}
		}
	}

});

function sololetras(e) {
	key = e.keyCode || e.which;
	teclado = String.fromCharCode(key).toLowerCase();
	letras = " abcdefghijklmnñopqrstuvwxyz";
	especiales = "8-37-38-46-164";
	tecladoespecial = false;

	for (var i in especiales) {
		if (key == especiales[i]) {
			tecladoespecial = true; break;
		}
	}
	if (letras.indexOf(teclado) == -1 && !tecladoespecial) {
		return false;
	}
}


$(document).on("click", "#btnagregarcategoria", function() {

	$("#txtcategoria").val("");

	$("#modalcategoria").modal("show");
});

$(document).on("click", ".btnactualizarcategoria", function() {

	$("#hddcodcategoria").val($(this).attr("data-codcategoria"));
	$("#txtcategoria").val($(this).attr("data-categoria"));
	$("#modalcategoria").modal("show");

});

$(document).on("click", ".btneliminarcategoria", function() {
	$("#mensajeeliminar").text("Está seguro(a) de Eliminar esta categoría: " +
		$(this).attr("data-categoria") + " con Codigo :" + $(this).attr("data-codcategoria") + "?");
	$("#hddcodcategoriaeliminar").val($(this).attr("data-codcategoria"))
	$("#modaleliminarcategoria").modal("show");

});

$(document).on("click", "#btnregistarcategoria", function() {
	var codigo = 0;
	if ($("#txtcategoria").val() === "") {
		$("#errorcategoria").text("Es obligatorio ingresar el Nombre de la categoria");
		codigo = 2;
	} else {
		$("#errorcategoria").text("");
	}
	switch (codigo) {

		case 0:
			$.ajax({
				type: "Post",
				contentType: "application/json",
				url: "/registrarCategoria",
				data: JSON.stringify({
					codcategoria: $("#hddcodcategoria").val(),
					categoria: $("#txtcategoria").val(),
				}),
				success: function(resultado) {
					if (resultado.respuesta) {
						alert(resultado.mensaje);
						//listar nuevamente los productos
						ListarCategoria();
					} else {
						alert(resultado.mensaje);
					}
					$("#modalcategoria").modal("hide");
				}
			});
	}

});

$(document).on("click", "#btneliminarcategoria", function() {
	alert($("#hddcodcategoriaeliminar").val());
	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/eliminarCategoria",
		data: JSON.stringify({
			codcategoria: $("#hddcodcategoriaeliminar").val()
		}),
		success: function(resultado) {
			//var estilo = "danger";
			if (resultado.respuesta) {
				alert(resultado.mensaje);
				ListarCategoria();
			}
			//mostrarMensaje(resultado.mensaje, estilo);
			$("#modaleliminarcategoria").modal("hide");
		}
	});
});


function ListarCategoria() {
	$.ajax({
		type: "GET",
		url: "/listarCategorias",
		dataType: "json",
		success: function(resultado) {
			$("#tblCategoria > tbody").html("");
			$.each(resultado, function(index, value) {
				if (value.codcategoria == 0) {
					$("#tblCategoria > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO HA REGISTRADO NINGUNA CATEGORIA </td>" +
						"</tr>");
				} else {
					$("#tblCategoria > tbody").append(
						"<tr>" +
						"<td>" + value.codcategoria + "</td>" +
						"<td>" + value.categoria + "</td>" +
						"<td><button type='button' class='btn btn-outline-warning btnactualizarcategoria' " +
						" data-codcategoria='" + value.codcategoria + "'" +
						" data-categoria='" + value.categoria + "'" +
						">Actualizar</button></td>" +
						
						"<td><button type='button' class='btn btn-outline-danger btneliminarcategoria' " +
						" data-codcategoria='" + value.codcategoria + "'" +
						" data-categoria='" + value.categoria + "'" +
						">Eliminar</button></td>" +
						"</td>"
					);
				}
			});
		},
		error: function(xhr, status) {
			$("#tblCategoria > tbody").append(
				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");
		}
	});
}

function BuscarcategoriaxCodigo(codigo) {
	$.ajax({
		type: "GET",
		url: "buscarCategoria",
		data: {
			codcategoria: codigo
		},
		datatype: 'json',
		success: function(data) {
			$("#tblCategoria > tbody").html("");
			$.each(data, function(index, value) {
				if (value.codcategoria == 0 || value.codcategoria == null) {
					$("#tblCategoria > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> CATEGORIA NO ENCONTRADA </td>" +
						"</tr>");
				} else {
					$("#tblCategoria > tbody").append(
						"<tr>" +
						"<td>" + value.codcategoria + "</td>" +
						"<td>" + value.categoria + "</td>" +
						"<td><button type='button' class='btn btn-outline-warning btnactualizarcategoria' " +
						" data-codcategoria='" + value.codcategoria + "'" +
						" data-categoria='" + value.categoria + "'" +
						">Actualizar</button></td>" +
						
						"<td><button type='button' class='btn btn-outline-danger btneliminarcategoria' " +
						" data-codcategoria='" + value.codcategoria + "'" +
						" data-categoria='" + value.categoria + "'" +
						">Eliminar</button></td>" +
						"</td>"
					)
				};
			});
		},
		error: function(xhr, status) {
			$("#tblCategoria > tbody").append(
				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");
		}
	});
}



