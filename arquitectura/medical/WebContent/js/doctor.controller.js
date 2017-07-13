$(document).ready(function(){
	inicializarLoading('.loadingDiv');
	$("#episodios").hide();
});

/**
 * Consulta los episodios de dolor
 * @returns
 */
function consultarEpisodios(){	
	var documento = $('#documento').val();
	var fechaInicio = $("#fechaInicio").val();
	var fechaFin = $("#fechaFin").val();
	limpiarMensajes();
	if (documento != ""){		
		var inicio = new Date().getMilliseconds();
		var url = "/medical/services/servidor/consultar/" + documento;
		if (fechaInicio != "" && fechaFin != "" && $("#filtrar:checked").length > 0){
			fechaInicio = (new Date(fechaInicio)).toISOString();
			fechaFin = (new Date(fechaFin)).toISOString();
			url = "/medical/services/servidor/consultarF/" + documento + "/" + fechaInicio + "/" + fechaFin;
		}
		$.ajax({
			type: "GET",
			url: url,
			dataType: "json",
		    contentType: "application/json"
		}).done(function( data ) {
			var fin = new Date().getMilliseconds();					
			mostrarEpisodios(data);		
			mensajeOK("Tiempo de ejecución " + (fin - inicio) + " ms");
		}).fail(function( jqxhr, textStatus, error ) {			
			mensajeError(textStatus + ", " + error);										    
		});
	}else{
		mensajeError("Debe ingresar el documento del paciente");
		$("#episodios").hide();
	}
} 

/**
 * Muestra el detalle de un episodio para realizar su análisis
 * @param obj
 * @returns
 */
function detalleEpisodio(obj){	
	var dataEpisodio = "";
	var idEpisodio = $(obj).closest("div.group-border").find("#oid").val();
	$.each(myModel.episodios, function (index, item){
		if (item._id.$oid == idEpisodio){
			dataEpisodio = JSON.parse(JSON.stringify(item));
			if (dataEpisodio.medicamentos.length == undefined){
				dataEpisodio.medicamentos = [dataEpisodio.medicamentos];
			}
			return false;
		}
	});	
	var url = "/medical/services/servidor/analisis/" + JSON.stringify(dataEpisodio);
	$.ajax({
		type: "GET",
		url: url,		
		dataType: "json",
	    contentType: "application/json"
	}).done(function( dataAnalisis ) {		
		bindCatalizadoresEpisodio(dataAnalisis);
		$('#modalDetalles').modal('show');
	}).fail(function( jqxhr, textStatus, error ) {			
		mensajeError(textStatus + ", " + error);										    
	});	
	bindDetallesEpisodio(dataEpisodio);
}


function onSignIn() {

    
    var email = $('#email').val();
	var password = $("#password").val();

    
    var xhr = new XMLHttpRequest();
    var url = "http://172.24.99.135:8080/medical/services/auth/login";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
        	
            var json = JSON.parse(xhr.responseText);
            sessionStorage.setItem("hash", json.hash);
            window.location.href = json.message;
            
        }
    };
    var data = JSON.stringify({"password": password, "email": email});
    
    xhr.send(data);
    
  };