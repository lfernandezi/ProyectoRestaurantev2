$(document).ready(function() {
	verUsuario();
	verUsuario2();
});

function verUsuario() {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/verusuario",
		dataType: "json",
		success: function(resultado) {
			$("#navbarDropdown1").text(resultado.mensaje);
			if ($("#navbarDropdown1").text() != "No ha iniciado sesión") {
				$("#acion1").text("CERRAR SESION");
				$("#acion2").hide();
				

			} else {
				$("#acion5").hide();
				$("#acion3").hide();
			}
		}
	});
};

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


$(document).on("click", "#acion3", function() {
	url = "http://localhost:9080/registrarCliente";
	window.open(url, "_self");

});



$(document).on("click", "#acion4", function() {
	var registro = $("#navbarDropdown2").text();
	if (registro == "No ha iniciado sesión") {
		window.open("http://localhost:9080/login", "_self");
	} else {
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/cerrarsesionempl",
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

function verUsuario2() {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/verusuarioempl",
		dataType: "json",
		success: function(resultado) {
			if (resultado.codusuario != 0) {
				$("#navbarDropdown2").text(resultado.email);
				if (resultado.rol == "ROLE_EMPLEADO") {
					$("#roles").text("EMPLEADO");
					$("#empleados").hide();
					$("#categorias").hide();
					$(".btneliminarcliente").hide();
				} else {
					$("#roles").text("ADMINISTRADOR");
					$("#empleados").show();
					$("#categorias").show();
					
				}
			}else{
				
			 window.open("http://localhost:9080/login", "_self");
			}


		}
	});
};

