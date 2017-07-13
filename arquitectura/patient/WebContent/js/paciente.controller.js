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
	var url = "/patient/services/servidor/registrar/";		
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

function onSignIn() {

    
    var email = $('#email').val();
	var password = $("#password").val();

    
    var xhr = new XMLHttpRequest();
    var url = "http://172.24.99.137:8000/patient/services/auth/login";
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