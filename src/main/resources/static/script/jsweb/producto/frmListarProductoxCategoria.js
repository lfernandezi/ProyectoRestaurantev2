$(document).ready(function() {
	$("#tblcategoria").hide();
});

$(document).on("change", "#cbocategoria", function() {
	var categoria = $("#cbocategoria").val();
	//alert(idespecialidad);
	if (categoria === "0"){
		$("#tblcategoria").hide();
		$("#tblcategoria").html("");
		alert("Seleccione una Categoria");
	} else{
		$.ajax({
			type: "GET",
			url: "/Categorias/listarProductoxCategorias",
			data: {
				codcategoria: categoria
			},
			success: function(data) {
				$("#tblcategoria").html("");
				if (data.length > 0) {
					$.each(data, function(index, value) {
						$("#tblcategoria").append(
							"<div class='col mb-4'>" +
							"<div class='card border-warning h-100'>" +
							"<img src='/img/" + value.nombre + ".jpg' class='card-img-top' alt='...'>" +
							"<div class='card-body'>" +
							"<h5 class='card-title'><b>" + value.nombre + "</b></h5>" +
							"<p class='card-text'>" + value.descripcion + "</p>" +
							"<p class='card-text'>S/." + value.precio + "</p>"
							+ "<button data-codproducto='" + value.codproducto +
							"' data-nombre='" + value.nombre +
							"' data-descripcion='" + value.descripcion +
							"' data-precio='" + value.precio +
							"' type='button' class='btn btn-outline-info btncomprar'>Comprar</button>"
							+ "</p>" +
							"</div></div></div>"
						)
					});
					$("#tblcategoria").show()
				} else {
					$("#tblcategoria").hide();
					$("#tblcategoria").html("");
				}
			}
		});
	}
});

$(document).on("click", ".btncomprar", function() {
	$("#txtnombre").val($(this).attr("data-nombre"));
	$("#txtdescripcion").val($(this).attr("data-descripcion"));
	$("#txtprecio").val($(this).attr("data-precio"));
	$("#txtcantidad").val("");
	$("#hddcodproducto").val($(this).attr("data-codproducto"));
	$("#modalproducto").modal("show");
});

$(document).on("click", "#btnagregarcarrito", function() {
	var uno = $("#txtprecio").val();
	var dos = $("#txtcantidad").val();
	var subtotal = uno * dos;
	if (dos <= 0) {
		alert("ELIJA CANTIDAD(MINIMO 1)")
	} else {
		$.ajax({
			type: "Post",
			contentType: "application/json",
			url: "/registrarpedido",
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
					//ListarProductos();
				} else {
					alert(resultado.mensaje);
				}
				$("#modalproducto").modal("hide");
			}



		});

	}

});





