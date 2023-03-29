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

			if ($("#navbarDropdown1").text() != "No estás registrado") {
				$("#acion1").text("CERRAR SESION");
				$("#acion2").hide();
				
			}else{
			
				$("#acion3").hide();
			}
		}
	});
};

$(document).on("click", "#acion1", function() {
	var registro = $("#navbarDropdown1").text();
	if (registro == "No estás registrado") {
		window.open("http://localhost:9080/login", "_self");
	}else{
		$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/cerrarsesion",
		dataType: "json",
		success: function(resultado) {
			if(resultado){
			window.open("http://localhost:9080/login", "_self");
			}else{
				alert("Algo falló");
			}
			
		}
	});
	}

});


$(document).on("click", "#acion3", function() {
	url ="http://localhost:9080/registrarCliente";
	window.open(url, "_self");

});



$(document).on("click", "#acion4", function() {
	var registro = $("#navbarDropdown2").text();
	if (registro == "No estás registrado") {
		window.open("http://localhost:9080/login", "_self");
	}else{
		$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/cerrarsesionempl",
		dataType: "json",
		success: function(resultado) {
			if(resultado){
			window.open("http://localhost:9080/login", "_self");
			}else{
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
			$("#navbarDropdown2").text(resultado.mensaje);
			if ($("#navbarDropdown1").text() != "No estás registrado") {
				$("#acion4").text("CERRAR SESION");
				
			}
		}
	});
};