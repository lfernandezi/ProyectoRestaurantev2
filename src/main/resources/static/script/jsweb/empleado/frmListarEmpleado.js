
$(document).ready(function() {
	$("#txtbuscarempleado").hide();
});

$(document).on("change", "#cbobuscarempleado", function() {
	$("#tblEmpleado > tbody").html("");
	$("#errorbuscarempleado").text("");
	$("#txtbuscarempleado").val("");
	var buscarempleado = $("#cbobuscarempleado").val();
	if (buscarempleado === "2" || buscarempleado === "3") {
		$("#txtbuscarempleado").show();
		$("#errorbuscarempleado").show();
	} else {
		$("#txtbuscarempleado").hide();
		$("#errorbuscarempleado").text("");
	}
});

$(document).on("click", "#btnbuscarempleado", function() {
	$("#tblEmpleado > tbody").html("");
	var buscarempleado = $("#cbobuscarempleado").val();
	var codigo = $("#txtbuscarempleado").val();
	var dniempleado = $("#txtbuscarempleado").val();


	if (buscarempleado === "0") {
		alert("Seleccione una opción");
		$("#errorbuscarempleado").text("");
	} else {
		if (buscarempleado === "1") {
			ListarEmpleado();
		} else {
			if (buscarempleado === "2") {
				if (codigo == "") {
					$("#errorbuscarempleado").text("Ingresar solo numero");
				} else {
					$("#errorbuscarempleado").text("");
					BuscarempleadoxCodigo(codigo);
				}
			} else {
				if (dniempleado.length != 8) {
					$("#errorbuscarempleado").text("Ingresar el DNI (MAX. 8 NUMEROS)");
				} else {
					$("#errorbuscarempleado").text("");
					BuscarEmpleadoxdni(codigo);
				}
			}
		}
	}

});


/*$("#txtbuscarempleado").keypress(function() {
	return validarSoloNumerosEnteros(event);
});
function validarSoloNumerosEnteros(e) {
	tecla = e.keyCode;
	if (tecla == 8) return true;
	patron = /[0-9]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);
}*/


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


$(document).on("click", "#btnagregarempleado", function() {
	ListarArea(0);
	ListarCargo(0);
	$("#txtnombre").val("");
	$("#txtapellido").val("");
	$("#txtdni").val("");
	$("#cbocargo").val("");
	$("#txtcontrasena").val("");
	$("#txtfecha").val("");
	$("#cboarea").val("");
	$("#cborol").val("");
	$("#hddcodempleado").val("");
	$("#modalempleado").modal("show");
});

$(document).on("click", ".btnactualizarempleado", function() {
	var codcargo = $(this).attr("data-codcargo");
	var codarea = $(this).attr("data-codarea");
	ListarCargo(codcargo);
	ListarArea(codarea);
	$("#txtnombre").val($(this).attr("data-nombre"));
	$("#txtapellido").val($(this).attr("data-apellido"));
	$("#txtdni").val($(this).attr("data-dni"));
	$("#txtcontrasena").val($(this).attr("data-contrasenia"));
	$("#txtfecha").val($(this).attr("data-fecha_ingreso"));
	$("#cborol").val($(this).attr("data-role"));
	$("#hddcodempleado").val($(this).attr("data-codempleado"));
	$("#modalempleado").modal("show");

});

$(document).on("click", ".btneliminarempleado", function() {
	$("#mensajeeliminar").text("Esta seguro(a) de Eliminar Empleado(a) : " +
		$(this).attr("data-nombre") + " con Codigo :" + $(this).attr("data-codempleado") + "?");
	$("#hddcodempleadoeliminar").val($(this).attr("data-codempleado"))
	$("#modaleliminarempleado").modal("show");

});

$(document).on("click", "#btnregistarempleado", function() {

	var codigo = 0;
	var dni = $("#txtdni").val();

	if ($("#cboarea").val() === "" || $("#cboarea").val() === "0" || $("cboarea").val() === 0) {
		$("#errorarea").text("Es obligatorio ingresar el AREA");
		codigo = 7;
	} else {
		$("#errorarea").text("");
	}
	if ($("#txtfecha").val() === "") {
		$("#errorfecha").text("Es obligatorio ingresar la FECHA");
		codigo = 6;
	} else {
		$("#errorfecha").text("");
	}
	if ($("#txtcontrasena").val() === "") {
		$("#errorcontrasena").text("Es obligatorio ingresar una CONTRASEÑA");
		codigo = 5;
	} else {
		$("#errorcontrasena").text("");
	}
	if ($("#cbocargo").val() === "" || $("#cbocargo").val() === "0" || $("#cbocargo").val() === 0) {
		$("#errorcargo").text("Es olbigatorio ingresar el CARGO");
		codigo = 4;
	} else {
		$("#errorcargo").text("");
	}
	if (dni.length != 8) {
		$("#errordni").text("Es obligatorio Ingresar el DNI (MAX. 8 DIGITOS)");
		codigo = 3;
	} else {
		$("#errordni").text("");
	}
	if ($("#txtapellido").val() === "") {
		$("#errorapellido").text("Es obligatorio ingresar el APELLIDO");
		codigo = 2;
	} else {
		$("#errorapellido").text("");
	}
	if ($("#txtnombre").val() === "") {
		$("#errornombre").text("Es obligatorio ingresar el NOMBRE");
		codigo = 1;
	} else {
		$("#errornombre").text("");
	}


	switch (codigo) {
		//case 1: alert("No ingresó nombre"); break;
		//case 2: alert("No ingresó apellido"); break;
		//case 3: alert("DNI INCORRECTO"); break;
		//case 4: alert("No ingresó Cargo"); break;
		//case 5: alert("No ingresó contraseña"); break;
		//case 6: alert("No ingresó Fecha"); break;
		//case 7: alert("No ingreso Area"); break;
		case 0:
			cadena = $("#cborol").val();
			$.ajax({
				type: "Post",
				contentType: "application/json",
				url: "/registrarEmpleado",
				data: JSON.stringify({
					codempleado: $("#hddcodempleado").val(),
					nombre: $("#txtnombre").val(),
					apellido: $("#txtapellido").val(),
					dni: $("#txtdni").val(),
					codcargo: parseInt($("#cbocargo").val()),
					contrasenia: $("#txtcontrasena").val(),
					fecha_ingreso: $("#txtfecha").val(),
					codarea: parseInt($("#cboarea").val()),
					role: $("#cborol").val()
				}),
				success: function(resultado) {
					if (resultado.respuesta) {
						alert(resultado.mensaje);
						//listar nuevamente los productos
						ListarEmpleado();
					} else {
						alert(resultado.mensaje);
					}
					$("#modalempleado").modal("hide");
				}

			});
	}

});

$(document).on("click", "#btneliminarempleado", function() {
	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/eliminarEmpleado",
		data: JSON.stringify({
			codempleado: $("#hddcodempleadoeliminar").val()
		}),
		success: function(resultado) {
			//var estilo = "danger";
			if (resultado.respuesta) {
				alert(resultado.mensaje);
				// Listar nuevamente los cursos
				//estilo = "success";
				ListarEmpleado();
			}
			//mostrarMensaje(resultado.mensaje, estilo);
			$("#modaleliminarempleado").modal("hide");
		}
	});
});


function ListarEmpleado() {
	$.ajax({
		type: "GET",
		url: "/listarEmpleado",
		dataType: "json",
		success: function(resultado) {
			console.log(resultado);


			$("#tblEmpleado > tbody").html("");
			$.each(resultado, function(index, value) {
				var rol = "";
				if (value.rol == "ROLE_ADMIN") {
					rol = "2";
				} else {
					rol = "1"
				}
				if (value.codempleado == 0) {
					$("#tblEmpleado > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> NO HA REGISTRADO NINGÚN EMPLEADO </td>" +
						"</tr>");
				} else {
					$("#tblEmpleado > tbody").append(
						"<tr>" +
						"<td>" + value.codempleado + "</td>" +
						"<td>" + value.nombre + "</td>" +
						"<td>" + value.apellido + "</td>" +
						"<td>" + value.dni + "</td>" +
						"<td>" + value.codcargo.cargo + "</td>" +
						"<td type='password'>" + value.contrasenia + "</td>" +
						"<td>" + value.fecha_ingreso + "</td>" +
						"<td>" + value.codarea.area + "</td>" +

						"<td><button type='button' class='btn btn-outline-warning btnactualizarempleado' " +
						" data-codempleado='" + value.codempleado + "'" +
						" data-nombre='" + value.nombre + "'" +
						" data-apellido='" + value.apellido + "'" +
						" data-dni='" + value.dni + "'" +
						" data-codcargo='" + value.codcargo.codcargo + "'" +
						" data-contrasenia='" + value.contrasenia + "'" +
						" data-fecha_ingreso='" + value.fecha_ingreso + "'" +
						" data-codarea='" + value.codarea.codarea + "'" +
						" data-role='" + rol + "'" +
						">Actualizar</button></td>" +
						"<td><button type='button' class='btn btn-outline-danger btneliminarempleado' " +
						" data-codempleado='" + value.codempleado + "'" +
						" data-nombre='" + value.nombre + "'" +
						">Eliminar</button></td>" +
						"</td>"
					);
				}
			});
		},

		error: function(xhr, status) {

			$("#tblEmpleado > tbody").append(

				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");

		}
	});
}


function BuscarempleadoxCodigo(codigo) {
	$.ajax({
		type: "GET",
		url: "buscarEmpleado",
		data: {
			codempleado: codigo
		},
		datatype: 'json',
		success: function(data) {
			$("#tblEmpleado > tbody").html("");
			$.each(data, function(index, value) {
			
			var rol = "";
				if (value.rol == "ROLE_ADMIN") {
					rol = "2";
				} else {
					rol = "1"
				}
				if (value.codempleado == 0) {
					$("#tblEmpleado > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> EMPLEADO NO ENCONTRADO </td>" +
						"</tr>");
				} else {
					$("#tblEmpleado > tbody").append(
						"<tr>" +
						"<td>" + value.codempleado + "</td>" +
						"<td>" + value.nombre + "</td>" +
						"<td>" + value.apellido + "</td>" +
						"<td>" + value.dni + "</td>" +
						"<td>" + value.codcargo.cargo + "</td>" +
						"<td>" + value.contrasenia + "</td>" +
						"<td>" + value.fecha_ingreso + "</td>" +
						"<td>" + value.codarea.area + "</td>" +

						"<td><button type='button' class='btn btn-outline-warning btnactualizarempleado' " +
						" data-codempleado='" + value.codempleado + "'" +
						" data-nombre='" + value.nombre + "'" +
						" data-apellido='" + value.apellido + "'" +
						" data-dni='" + value.dni + "'" +
						" data-codcargo='" + value.codcargo.codcargo + "'" +
						" data-contrasenia='" + value.contrasenia + "'" +
						" data-fecha_ingreso='" + value.fecha_ingreso + "'" +
						" data-codarea='" + value.codarea.codarea + "'" +
						" data-role='" + rol+ "'" +
						">Actualizar</button></td>" +
						"<td><button type='button' class='btn btn-outline-danger btneliminarempleado' " +
						" data-codempleado='" + value.codempleado + "'" +
						" data-nombre='" + value.nombre + "'" +
						">Eliminar</button></td>" +
						"</td>"
					)
				};
			});
		},

		error: function(xhr, status) {

			$("#tblEmpleado > tbody").append(

				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");

		}
	});
}


function BuscarEmpleadoxdni(codigo) {
	$.ajax({
		type: "GET",
		url: "buscarEmpleadoxdni",
		data: {
			dni: codigo
		},
		datatype: 'json',
		success: function(data) {
			$("#tblEmpleado > tbody").html("");
			$.each(data, function(index, value) {
			
			var rol = "";
				if (value.rol == "ROLE_ADMIN") {
					rol = "2";
				} else {
					rol = "1"
				}
				if (value.codempleado == 0) {
					$("#tblEmpleado > tbody").append(
						"<tr>" +
						"<td colspan='10' class='text-center'> EMPLEADO NO ENCONTRADO </td>" +
						"</tr>");
				} else {
					$("#tblEmpleado > tbody").append(

						"<tr>" +
						"<td>" + value.codempleado + "</td>" +
						"<td>" + value.nombre + "</td>" +
						"<td>" + value.apellido + "</td>" +
						"<td>" + value.dni + "</td>" +
						"<td>" + value.codcargo.cargo + "</td>" +
						"<td>" + value.contrasenia + "</td>" +
						"<td>" + value.fecha_ingreso + "</td>" +
						"<td>" + value.codarea.area + "</td>" +

						"<td><button type='button' class='btn btn-outline-warning btnactualizarempleado' " +
						" data-codempleado='" + value.codempleado + "'" +
						" data-nombre='" + value.nombre + "'" +
						" data-apellido='" + value.apellido + "'" +
						" data-dni='" + value.dni + "'" +
						" data-codcargo='" + value.codcargo.codcargo + "'" +
						" data-contrasenia='" + value.contrasenia + "'" +
						" data-fecha_ingreso='" + value.fecha_ingreso + "'" +
						" data-codarea='" + value.codarea.codarea + "'" +
						" data-role='" + rol + "'" +
						">Actualizar</button></td>" +
						"<td><button type='button' class='btn btn-outline-danger btneliminarempleado' " +
						" data-codempleado='" + value.codempleado + "'" +
						" data-nombre='" + value.nombre + "'" +
						">Eliminar</button></td>" +
						"</td>"
					)
				};
			})
		},
		error: function(xhr, status) {
			$("#tblEmpleado > tbody").html("");
			$("#tblEmpleado > tbody").append(

				"<tr>" +
				"<td colspan='10' class='text-center'> OCURRIÓ UN ERROR </td>" +
				"</tr>");
		}
	});
}

function ListarArea(codigoarea) {
	$.ajax({
		type: "GET",
		url: "/listarArea",
		dataType: "json",
		success: function(resultado) {
			$("#cboarea").html("");

			$("#cboarea").append(
				"<option value='0'> Seleccionar</option>");

			$.each(resultado, function(index, value) {
				$("#cboarea").append(

					"<option value=" + value.codarea + ">" + value.area + "</option>"

				);
			});

			$("#cboarea").val(codigoarea);
		}
	});
}

function ListarCargo(codigocargo) {

	$.ajax({
		type: "GET",
		url: "/listarCargo",
		dataType: "json",
		success: function(resultado) {
			//console.log(resultado);
			$("#cbocargo").html("");
			$("#cbocargo").append(
				"<option value='0'> Seleccionar</option>");
			$.each(resultado, function(index, value) {
				$("#cbocargo").append(
					"<option value=" + value.codcargo + ">" + value.cargo + "</option>"
				);
			});
			/*$("#cbocargo").append(
				"<option value='100'> Otro </option>");*/

			$("#cbocargo").val(codigocargo);
		}
	});
}





