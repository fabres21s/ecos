$(document).ready(function(){
	inicializarLoading('.loadingDiv');	
	inicializarListados();
	bindEpisodios();		
	$("#formPaciente").validator().on("submit", function (event) {				
		if (event.isDefaultPrevented()) {
			
		} else {
			event.preventDefault();	
			grabarEpisodio();		  		
		}
	   
	});
});

/**
 * Envíar el formulario
 * @returns
 */
function enviarFormulario(){
	$(formPaciente).submit();
}

/**
 * Grabar el episodio de migraña
 * @returns
 */
function grabarEpisodio(){
	var myData = $form.my("data");
	myData.fecha = (new Date(myData.fecha)).toISOString();	
	var datos = JSON.stringify(myData);	
	limpiarMensajes();
	var url = "/medical/services/servidor/registrar/";		
	var inicio = new Date().getMilliseconds();	
	$.ajax({
		type: "POST",
		url: url,	        
		data: datos,
		contentType: "application/json"
	}).done(function( data ) {
		var fin = new Date().getMilliseconds();						
		mensajeOK(data + "Tiempo de ejecución " + (fin - inicio) + " ms");
	}).fail(function( jqxhr, textStatus, error ) {			
		mensajeError(textStatus + ", " + error);										    
	});
} 