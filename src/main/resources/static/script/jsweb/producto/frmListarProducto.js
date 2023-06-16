$(document).ready(function() {
	$("#btncambiarImagen").hide();
});

$(document).on("click", "#btnagregarproducto", function() {
	ListarCategoria(0);
	$("#txtnombre").val("");
	$("#txtdescripcion").val("");
	$("#cbocategoria").val("");
	$("#txtprecio").val("");
	$("#hddcodproducto").val("");
	$("#modalproducto").modal("show");
});

$(document).on("change", "#controlimagen", function() {
	console.log(this.files);
	var files = this.files;
	var element;
	for (var i = 0; i < files.length; i++) {
		element = files[i];

		var imgcodificada = URL.createObjectURL(element);
		var img = $(
			"<div class='card'>" +
			" <img  src='" + imgcodificada + "' id='img-preview'>" +
			"<button type='button' class='btn btn-outline-info'" +
			"	id='btncambiarImagen'>Ocultar Vista Previa</button>" +
			"</div>"
		);
		
		$(img).insertBefore("#img-preview");
		$("#controlimagen").hide();
		$("#btncambiarImagen").show();
	}
});

$(document).on("click", "#btncambiarImagen", function() {
	$(this).parent().remove();

	$("#controlimagen").show();
});


$(document).on("click", ".btnactualizarproducto", function() {
	var codcategoria = $(this).attr("data-codcategoria");

	ListarCategoria(codcategoria);
	$("#txtnombre").val($(this).attr("data-nombre"));
	$("#txtdescripcion").val($(this).attr("data-descripcion"));
	//$("#cbocategoria").val($(this).attr("data-codcategoria"));
	$("#txtprecio").val($(this).attr("data-precio"));
	$("#hddcodproducto").val($(this).attr("data-codproducto"));
	$("#modalproducto").modal("show");
	alert(codcategoria);
});

$(document).on("click", ".btneliminarproducto", function() {
	$("#mensajeeliminar").text("Esta Seguro(a) de Eliminar el Producto : " +
		$(this).attr("data-nombre") + " con Codigo:" + $(this).attr("data-codproducto") + "?");
	$("#hddcodproductoeliminar").val($(this).attr("data-codproducto"))
	$("#modaleliminarproducto").modal("show");

});

$(document).on("click", "#btnregistarproducto", function() {
		
    if ($("#controlimagen").val()== ""){
    	alert("No eligió imagen");
    	}
    	codigo = 5;
	if ($("#txtprecio").val() <= 0) {
		$("#errorprecio").text("Ingresar Valor Mayor a CERO(SOLO ACEPTA NUMEROS)");
		codigo = 4;
	} else {
		$("#errorprecio").text("");
	}
	if ($("#cbocategoria").val() === "" || $("#cbocategoria").val() === 0 || $("#cbocategoria").val() === "0") {
		$("#errorcategoria").text("Ingresar una CATEGORIA");
		codigo = 3;
	} else {
		$("#errorcategoria").text("");
	}
	if ($("#txtdescripcion").val() === "") {
		$("#errordescripcion").text("Ingresar la DESCRIPCION DEL PRODUCTO");
		codigo = 2;
	} else {
		$("#errordescripcion").text("");
	}
	if ($("#txtnombre").val() === "") {
		$("#errornombre").text("Ingresar el NOMBRE COMBO");
		codigo = 1;
	} else {
		$("#errornombre").text("");
	}
	
	alert($("#controlimagen").val());

	switch (codigo) {
		//case 1: alert("INGRESAR NOMBRE COMBO"); break;
		//case 2: alert("INGRESAR DESCRIPCION COMIDA "); break;
		//case 3: alert("INGRESAR CATEGORIA COMIDA"); break;
		//case 4: alert("INGRESAR PRECIO COMIDA"); break;
		case 0:
		
		
		
		
			$.ajax({
				type: "Post",
				contentType: "application/json",
				url: "/registrarProducto",
				data: JSON.stringify({
					codproducto: $("#hddcodproducto").val(),
					nombre: $("#txtnombre").val(),
					descripcion: $("#txtdescripcion").val(),
					codcategoria: $("#cbocategoria").val(),
					precio: $("#txtprecio").val(),
					imagen: $("#controlimagen").val()
				}),
				success: function(resultado) {
					if (resultado.respuesta) {
						alert(resultado.mensaje);
						//listar nuevamente los productos
						ListarProductos();
					} else {
						alert(resultado.mensaje);
					}
					$("#modalproducto").modal("hide");
				}
			});
	}

});

$(document).on("click", "#btneliminarproducto", function() {
	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/eliminarProducto",
		data: JSON.stringify({
			codproducto: $("#hddcodproductoeliminar").val()
		}),
		success: function(resultado) {
			if (resultado.respuesta) {
				alert(resultado.mensaje);
				// Listar nuevamente los cursos
				ListarProductos();

			}
			$("#modaleliminarproducto").modal("hide");
		}
	});
});


function ListarProductos() {
	$.ajax({
		type: "GET",
		url: "/listarProducto",
		dataType: "json",
		success: function(resultado) {
			//console.log(resultado);
			$("#tblproducto > tbody").html("");
			$.each(resultado, function(index, value) {

				if (value.codproducto == 0) {
					$("#tblproducto > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> Aún no ha registrado ningún producto</td>" +
						"</tr>");
				} else {

					$("#tblproducto > tbody").append(
						"<tr>" +
						"<td>" + value.codproducto + "</td>" +
						"<td>" + value.nombre + "</td>" +
						"<td>" + value.descripcion + "</td>" +
						"<td>" + value.codcategoria.categoria + "</td>" +
						"<td> S/.  " + value.precio + "</td>" +
						"<td><button type='button' class='btn btn-outline-warning btnactualizarproducto' " +
						" data-codproducto='" + value.codproducto + "'" +
						" data-nombre='" + value.nombre + "'" +
						" data-descripcion='" + value.descripcion + "'" +
						" data-codcategoria='" + value.codcategoria.codcategoria + "'" +
						" data-precio='" + value.precio + "'" +

						">Actualizar</button></td>" +
						"<td><button type='button' class='btn btn-outline-danger btneliminarproducto' " +
						" data-codproducto='" + value.codproducto + "'" +
						" data-nombre='" + value.nombre + "'" +
						">Eliminar</button></td>" +
						"</td>");
				}
			});
		}
	});
}


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


$(document).ready(function() {
	//ListarProductos();
	$("#txtbuscarproducto").hide();
});

$(document).on("change", "#cbobuscarproducto", function() {
	$("#errorbuscarproducto").text("");
	$("#txtbuscarproducto").val("");
	var buscarproducto = $("#cbobuscarproducto").val();
	//alert(idespecialidad);
	if (buscarproducto === "2") {
		$("#txtbuscarproducto").show();
		//$("#tblcategoria").html("");
		$("#errorbuscarproducto").show();
	} else {
		$("#txtbuscarproducto").hide();
		$("#errorbuscarproducto").hide();
	}
});


$(document).on("click", "#btnbuscarproducto", function() {
	var buscarproducto = $("#cbobuscarproducto").val();
	var codigo = $("#txtbuscarproducto").val();
	if ($("#txtbuscarproducto").val() == "") {
		$("#errorbuscarproducto").text("INGRESAR SOLO NUMEROS");
	} else {
		$("#errorbuscarproducto").text("");
	}
	if (buscarproducto === "0") {
		alert("Seleccione una opción");
	} else {
		if (buscarproducto === "1") {
			ListarProductos();
		} else {
			if (buscarproducto === "2") {
				BuscarproductoxCodigo(codigo);
			}
		}
	}
});


function BuscarproductoxCodigo(codigo) {
	$.ajax({
		type: "GET",
		url: "buscarProducto",
		data: {
			codproducto: codigo
		},
		datatype: 'json',
		success: function(data) {
			$("#tblproducto > tbody").html("");
			$.each(data, function(index, value) {

				if (value.codproducto == 0) {
					$("#tblproducto > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> PRODUCTO NO ENCONTRADO </td>" +
						"</tr>");
				} else {
					$("#tblproducto > tbody").append("<tr>" +
						"<td>" + value.codproducto + "</td>" +
						"<td class='text-center'>" + value.nombre + "</td>" +
						"<td class='text-center'>" + value.descripcion + "</td>" +
						"<td class='text-center'>" + value.codcategoria.categoria + "</td>" +
						"<td class='text-center'> S/.  " + value.precio + "</td>" +
						"<td><button type='button' class='btn btn-outline-warning btnactualizarproducto' " +
						" data-codproducto='" + value.codproducto + "'" +
						" data-nombre='" + value.nombre + "'" +
						" data-descripcion='" + value.descripcion + "'" +
						" data-codcategoria='" + value.codcategoria.codcategoria + "'" +
						" data-precio='" + value.precio + "'" +
						">Actualizar</button></td>" +
						"<td><button type='button' class='btn btn-outline-danger btneliminarempleado' " +
						" data-codproducto='" + value.codproducto + "'" +
						" data-nombre='" + value.nombre + "'" +
						">Eliminar</button></td>" +
						"</tr>"
					)
				};
			})
		}
	});
}

function ListarCategoria() {
	$.ajax({
		type: "GET",
		url: "/listarCategorias",
		dataType: "json",
		success: function(resultado) {
			//console.log(resultado);
			$("#cbocategoria").html("");
			$("#cbocategoria").append(
				"<option value='0'> Seleccionar</option>");
			$.each(resultado, function(index, value) {
				$("#cbocategoria").append(
					"<option value='" + value.codcategoria + "'>" + value.categoria + "</option>"
				);
			});

		}
	});
}

