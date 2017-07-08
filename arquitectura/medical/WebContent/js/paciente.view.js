var myModel = $.parseJSON('{"documento": "","fecha": "","nivelDolor": "","localizacionDolor": "","patronSueno": "","actividadFisica": "","alimentos": "","bebidas":"","medicamentos": [{"nombre": "",	"dosis": ""} 	]}');
var $form;		//Formulario con binding

/**
 * Inicializa los listados
 * @returns
 */
function inicializarListados(){
	$(".selectpicker").selectpicker({
		noneSelectedText: 'Seleccione una opci&oacute;n',
		dropupAuto: false,
		title: 'Seleccione uno de los siguientes'
	});	
	//Nivel de dolor	
	var select = $('#nivelDolor');	
	var data = consultarNivelesDolores();	
	$.each(data, function (index, item){		
		addSelectOption(select, item.codigo,item.descripcion, false);
    });
	$(select).selectpicker('refresh');
	
	//Actividades físicas
	select = $('#actividadFisica');	
	data = consultarActividadesFisicas();	
	$.each(data, function (index, item){		
		addSelectOption(select, item.codigo,item.descripcion, false);
    });
	$(select).selectpicker('refresh');	
	
	//Alimentos
	select = $('#alimentos');	
	data = consultarAlimentos();	
	$.each(data, function (index, item){		
		addSelectOption(select, item.codigo,item.descripcion, false);
    });
	$(select).selectpicker('refresh');
	//Bebidas
	select = $('#bebidas');	
	data = consultarBebidas();	
	$.each(data, function (index, item){		
		addSelectOption(select, item.codigo,item.descripcion, false);
    });
	$(select).selectpicker('refresh');
}

/**
 * Realiza el binding del html con el modelo de datos
 * @returns
 */
function bindEpisodios(){	
	//Binding del modelo con la vista
	$form = $(formPaciente).my({
		ui:{									
			"#documento" : "documento",
			"#fecha" : "fecha",
			"#nivelDolor" : "nivelDolor",
			"#localizacionDolor" : "localizacionDolor",
			"#patronSueno" : "patronSueno",
			"#actividadFisica" : "actividadFisica",
			"#alimentos" : "alimentos",
			"#bebidas" : "bebidas",
			".addClasifValue": {
				bind: function (data, val) {																														
					if (null != val){												
						this.my.insert("#medicamentos", {});	
						$("#formPaciente").validator('update');
					}							
				},
				events:"click.my" 										
			},
			"#medicamentos" : {
				bind: "medicamentos",
				manifest:{
					ui:{												
						"#nombre": "nombre",
						"#dosis": "dosis",						
						".delClasifValue":{										
							bind: function (data, val) {											
								if (null != val){									
									this.my.remove();
									$("#formPaciente").validator('update');
								}
							},
							events:"click.my"
						}
					}
				}
			}			
		}
	}, myModel);	
}