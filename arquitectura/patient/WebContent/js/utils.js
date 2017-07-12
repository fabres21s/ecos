/**
 * Inicializa el div para el estado de carga
 * @param idDiv
 * @returns
 */
function inicializarLoading(idDiv){
	$('.loadingDiv').remove();
	$('body').append("<div class='loadingDiv custom-overlay'></div>");
	$('body').append("<div class='loadingDiv ui-loader ui-corner-all ui-body-c ui-loader-verbose'>" +		
						"<span class='ui-icon ui-icon-loading'></span>" + 						
						"<h1>Cargando...</h1>" +
					"</div>");
	var $loading = $(idDiv).hide();	
	$(document).ajaxStart(function () {
		$loading.show();		
	}).ajaxStop(function () {
		$loading.hide();		
	});
}

/**
 * Muestra un mensaje de error
 * @param mensaje
 * @returns
 */
function mensajeError(mensaje){
	//limpiarMensajes();	
	$('.mensajeError').show().html(mensaje);	
	console.log(mensaje);
}

/**
 * Muestra un mensaje satisfactorio
 * @param mensaje
 * @returns
 */
function mensajeOK(mensaje){
	//limpiarMensajes();	
	$('.mensajeOK').show().html(mensaje);	
	console.log(mensaje);
}

/**
 * Limpia todos los mensajes
 * @returns
 */
function limpiarMensajes(){
	var div = $('.mensajeOK');
	$(div).html("");
	$(div).hide();
	div = $('.mensajeError');
	$(div).html("");
	$(div).hide();	
}

/**
 * Agrega una opción a un elemento select
 * @param select
 * @param value
 * @param text
 * @param selected
 */
function addSelectOption(select, value, text, selected ){
	$(select).append($('<option>', {
		value: value,
		text: text,
		selected: selected
	}));
}

/**
 * Permite agregar una opción a un elemento select
 * @param select
 * @param value
 * @param text
 * @param selected
 * @param disabled
 */
function addSelectOption(select, value, text, selected, disabled ){
	$(select).append($('<option>', {
		value: value,
		text: text,
		selected: selected,
		disabled: disabled
	}));
}

/**
 * Mock data para los niveles de dolor
 * @returns
 */
function consultarNivelesDolores(){
	var data = 	[ 
			{codigo: 1, descripcion: "Leve"},
			{codigo: 2,	descripcion: "Tolerable"},
			{codigo: 3,	descripcion: "Fuerte"},
			{codigo: 4,	descripcion: "Muy fuerte"},
			{codigo: 5, descripcion: "Insoportable"}			
		];	
	return data;
} 

/**
 * Mock data para las actividades físicas
 * @returns
 */
function consultarActividadesFisicas(){
	var data = 	[ 			
			{codigo: 1,	descripcion: "Deportes"},
			{codigo: 2,	descripcion: "Salir a caminar"},			
			{codigo: 3, descripcion: "Labores del hogar"},
			{codigo: 4, descripcion: "No realiza ninguna actividad física"}			
		];	
	return data;
}

/**
 * Mock data para las bebidas
 * @returns
 */
function consultarBebidas(){
	var data = 	[ 
			{codigo: 1, descripcion: "Gaseosas"},
			{codigo: 2,	descripcion: "Jugos artificiales"},
			{codigo: 3,	descripcion: "Jugos naturales con azúcar"},
			{codigo: 4,	descripcion: "Jugos naturales sin azúcar"},
			{codigo: 5, descripcion: "Café"},
			{codigo: 6,	descripcion: "Té"},
		];	
	return data;
} 

/**
 * Mock data para los alimentos
 * @returns
 */
function consultarAlimentos(){
	var data = 	[ 
			{codigo: 1, descripcion: "Harinas"},
			{codigo: 2,	descripcion: "Comidas rápidas"},
			{codigo: 3,	descripcion: "Paquetes o snacks"},
			{codigo: 4,	descripcion: "Vegetales"},
			{codigo: 5, descripcion: "Frutas"},
			{codigo: 6,	descripcion: "Dulces"},
		];	
	return data;
} 