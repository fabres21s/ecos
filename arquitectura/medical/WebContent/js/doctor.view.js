
var myModel;	//Modelo del binding. Contiene los detalles de la meta
var $form;			//Formulario con binding a los episodios
var $formDetalles;	//Formulario con binding a los datalles del episodio
var $formCatalizadores;	//Formulario con binding a los catalizadores de episodio

/**
 * Muestra los episodios realizando el binding con el modelo
 * o actualizando los datos si ya se hizo el binding
 * @param datos Modelo de datos
 * @returns
 */
function mostrarEpisodios(datos){	
	if ($form == undefined){
		myModel = datos;
		bindEpisodios();		
	}else{
		$form.my("data", datos);	//Realiza merge de los datos y actualiza vista/modelo
	}	
	limpiarMensajes();	
	if (myModel.episodios.length > 0){
		$("#episodios").show();
		$("#grafica").removeClass("ocultoInicial");
	}else{
		$("#grafica").addClass("ocultoInicial");
		mensajeError("No se encontraron registros");
	}
}

/**
 * Realiza el binding del html con el modelo de datos
 * @returns
 */
function bindEpisodios(){	
	//Binding del modelo con la vista
	$form = $("#formDoctor").my({
		ui:{						
			"#episodios" : {
				bind: "episodios",
				manifest:{								
					ui:{
						"#idEpisodio": {
							bind: "_id",
							manifest:{
								ui:{
									"#oid" : "$oid"
								}
							}
							
						},
						"#fecha" : {
							bind: function (data, value, $control) {																																						
								if (data.fecha != undefined && value == undefined){						
									var date = data.fecha.substring(0,10)									
									return date;
								}else{
									return value;
								}
							}
						},
						"#nivelDolor" : {
							bind: function(data, value, $control){	
								$control.empty();							
								if (data.nivelDolor != undefined){									
									$control.addClass("selectpicker");
									$control.selectpicker({
										noneSelectedText: 'Seleccione una opci&oacute;n',
										dropupAuto: false,
										title: 'Seleccione uno de los siguientes'
									});	
									var datos = consultarNivelesDolores();	
									$.each(datos, function (index, item){		
										if (data.nivelDolor == item.codigo)
											addSelectOption($control, item.codigo,item.descripcion, true, false);
										else
											addSelectOption($control, item.codigo,item.descripcion, false, true);
									});
									$control.selectpicker('refresh');
								}else{
									return "";
								}
							}
						},
						"#localizacionDolor" : "localizacionDolor",
						"#patronSueno" : "patronSueno",
						"#actividadFisica" : {
							bind: function(data, value, $control){								
								$control.empty();
								if (data.actividadFisica != undefined){									
									$control.addClass("selectpicker");
									$control.selectpicker({
										noneSelectedText: 'Seleccione una opci&oacute;n',
										dropupAuto: false,
										title: 'Seleccione uno de los siguientes'
									});	
									var datos = consultarActividadesFisicas();										
									$.each(datos, function (index, item){
										var seleccionar = false;
										$.each(data.actividadFisica, function (sIndex, sItem){
											if (sItem == item.codigo)											
												seleccionar = true;
										});	
										addSelectOption($control, item.codigo,item.descripcion, seleccionar, !seleccionar);
									});
									$control.selectpicker('refresh');
								}else{
									return "";
								}
							}
						},						
						"#alimentos" : {
							bind: function(data, value, $control){								
								$control.empty();
								if (data.alimentos != undefined){									
									$control.addClass("selectpicker");
									$control.selectpicker({
										noneSelectedText: 'Seleccione una opci&oacute;n',
										dropupAuto: false,
										title: 'Seleccione uno de los siguientes'
									});	
									var datos = consultarAlimentos();										
									$.each(datos, function (index, item){
										var seleccionar = false;
										$.each(data.alimentos, function (sIndex, sItem){
											if (sItem == item.codigo)											
												seleccionar = true;
										});	
										addSelectOption($control, item.codigo,item.descripcion, seleccionar, !seleccionar);
									});
									$control.selectpicker('refresh');
								}else{
									return "";
								}
							}
						},	
						"#bebidas" : {
							bind: function(data, value, $control){								
								$control.empty();
								if (data.bebidas != undefined){									
									$control.addClass("selectpicker");
									$control.selectpicker({
										noneSelectedText: 'Seleccione una opci&oacute;n',
										dropupAuto: false,
										title: 'Seleccione uno de los siguientes'
									});	
									var datos = consultarBebidas();										
									$.each(datos, function (index, item){
										var seleccionar = false;
										$.each(data.bebidas, function (sIndex, sItem){
											if (sItem == item.codigo)											
												seleccionar = true;
										});	
										addSelectOption($control, item.codigo,item.descripcion, seleccionar, !seleccionar);
									});
									$control.selectpicker('refresh');
								}else{
									return "";
								}
							}
						}						
					}
				}
			}
		}
	}, myModel);	
}

/**
 * Realiza el bind del html con el modelo de datos
 * para los detalles del episodio de migraña
 * @param model
 * @returns
 */
function bindDetallesEpisodio(model){
	if ($formDetalles == undefined){			
		$formDetalles = $("#datosEpisodio").my({
			ui:{			
				"#nivelDolor" : {
					bind: function(data, value, $control){	
						$control.empty();
						if (data.nivelDolor != undefined){									
							$control.addClass("selectpicker");
							$control.selectpicker({
								noneSelectedText: 'Seleccione una opci&oacute;n',
								dropupAuto: false,
								title: 'Seleccione uno de los siguientes'
							});	
							var datos = consultarNivelesDolores();	
							$.each(datos, function (index, item){		
								if (data.nivelDolor == item.codigo)
									addSelectOption($control, item.codigo,item.descripcion, true, false);
								else
									addSelectOption($control, item.codigo,item.descripcion, false, true);
							});
							$control.selectpicker('refresh');
						}else{
							return data.nivelDolor;
						}
					}
				},
				"#localizacionDolor" : "localizacionDolor",		
				"#medicamentos" : {
					bind: "medicamentos",
					manifest:{
						ui:{									
							"#nombre": "nombre",
							"#dosis": "dosis"				
						}
					}
				}
			}
		}, model);
	}else{
		$formDetalles.my("data", model);	//Realiza merge de los datos y actualiza vista/modelo				
	}	
}

/**
 * Realiza el bind del html con el modelo de datos
 * para los catalizadores del episodio de migraña
 * @param model
 * @returns
 */
function bindCatalizadoresEpisodio(model){	
	if ($formCatalizadores == undefined){			
		$formCatalizadores = $("#catalizadores").my({
			ui:{									
				"#actividadFisica" : {				
					bind: "actividadFisica",
					manifest:{
						ui:{
							"#catalizador": {
								bind: function(data, value, $control){
									var dato = data.descripcion;
									var opciones = consultarActividadesFisicas();
									$.each(opciones, function(index,item){
										if (item.codigo == data.descripcion){
											dato = item.descripcion;
											return false;
										}
									});					
									return dato;
								}
							},
							"#observacion": "observacion"
						}
					}
				},
				"#alimentos" : {				
					bind: "alimentos",
					manifest:{
						ui:{
							"#catalizador": {
								bind: function(data, value, $control){
									var dato = data.descripcion;
									var opciones = consultarAlimentos();
									$.each(opciones, function(index,item){
										if (item.codigo == data.descripcion){
											dato = item.descripcion;
											return false;
										}
									});					
									return dato;
								}
							},
							"#observacion": "observacion"
						}
					}
				},
				"#bebidas" : {				
					bind: "bebidas",
					manifest:{
						ui:{
							"#catalizador": {
								bind: function(data, value, $control){
									var dato = data.descripcion;
									var opciones = consultarBebidas();
									$.each(opciones, function(index,item){
										if (item.codigo == data.descripcion){
											dato = item.descripcion;
											return false;
										}
									});					
									return dato;
								}
							},
							"#observacion": "observacion"
						}
					}
				}
			}
		}, model);
	}else{
		$formCatalizadores.my("data", model);	//Realiza merge de los datos y actualiza vista/modelo
		
	}
}

/**
 * Genera la gráfica para el análisis de los episodios
 * según el nivel de dolor y los medicamentos asociados
 * @returns
 */
function mostrarGrafica(){
	$('#modalGrafica').modal('show'); 	
	$("#modalGrafica").on('shown.bs.modal', function (e) {
		var data = ['Muestras'];
		$.each(myModel.episodios, function (index, item){
			data.push(parseInt(item.nivelDolor));
		});		
		var width = $('#modalGrafica').find(".modal-body").width();		
		var chart = c3.generate({
			bindto: '#chart',
			size: {
				height: 400,
				width: width				
			},
			data: {
				columns: [			
					data
				],
				types: {
					Muestras: 'bar'
				}
			},	
			axis: {
				y: {
					label: {
						text: 'Nivel de dolor',
						position: 'outer-middle'
					},
					tick: {
						values: [1, 2, 3, 4 , 5]
					}					
				}				
			},
			tooltip: {
				contents: function (d, defaultTitleFormat, defaultValueFormat, color) {					
					var html = "<div class='tooltip-grafica'><table class='table table-striped table-sm'><thead>";
					html += "<tr><th>#</th><th>Medicamento</th><th>Dosis</th></tr>";
					html += "</thead><tbody>";
					if (myModel.episodios[d[0].index].medicamentos.length != undefined){
						$.each(myModel.episodios[d[0].index].medicamentos, function(index, item){
							html += "<tr><th scope='row'>"+(index+1)+"</th><td>"+item.nombre+"</td><td>"+item.dosis+"</td></tr>";
						});					
					}else{
						html += "<tr><th scope='row'>1</th><td>"
						+myModel.episodios[d[0].index].medicamentos.nombre+"</td><td>"
						+myModel.episodios[d[0].index].medicamentos.dosis+"</td></tr>";
					}					
					html += "</tbody></table><div>";				
					return html;
				}
			}
		});	
	});	
}

/**
 * Muestra/oculta las fechas para el filtrado de
 * los episodios
 * @returns
 */
function conmutarFiltros(){	
	if ($("#filtrar:checked").length > 0){
		$(".ocultoInicial").show();
	}else{
		$(".ocultoInicial").hide();
	}
}