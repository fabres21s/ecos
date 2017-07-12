package edu.uniandes.co.arquitectura.utils;

import org.bson.types.BasicBSONList;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mongodb.BasicDBObject;

/**
 * Componente que permite analizar los episodios de migra�a
 * @author Oswaldo
 * @version 1.0.0
 */
public class AnalisisEpisodio {
	
	/**
	 * Prean�lisis del episodio entregado al paciente 
	 * @param document Episodio de migra�a
	 * @return analisis catalizadores
	 */
	public static String preAnalisisEpisodio(BasicDBObject document){
		String analisis = "";
		analisis += analizarActividadesFisicas(document);
		analisis += analizarBebidas(document);		
		analisis += analizarAlimentos(document);						
		return analisis;
	}
	
	/**
	 * An�lisis del episodio entregado al doctor
	 * @param document Episodio de migra�a
	 * @return analisis catalizadores
	 */
	public static String analisisEpisodio(BasicDBObject document){		
		JSONObject resultado = new JSONObject();
		JSONArray actividades = new JSONArray();
		BasicBSONList lista = (BasicBSONList) document.get("actividadFisica");
		for (Object item : lista){
			JSONObject obj = new JSONObject();
			String actividad = item.toString();
			obj.put("descripcion", actividad);
			String descripcion = analizarActividadesFisicas(actividad).replace("<br/>", "");
			obj.put("observacion", descripcion);
			if (!"".equals(descripcion))
				actividades.put(obj);
		}
		resultado.put("actividadFisica", actividades);
		JSONArray alimentos = new JSONArray();
		lista = (BasicBSONList) document.get("alimentos");
		for (Object item : lista){
			JSONObject obj = new JSONObject();
			String alimento = item.toString();
			obj.put("descripcion", alimento);			
			String descripcion = analizarAlimentos(alimento).replace("<br/>", "");
			obj.put("observacion", descripcion);
			if (!"".equals(descripcion))
				alimentos.put(obj);
		}
		resultado.put("alimentos", alimentos);
		resultado.put("bebidas", actividades);
		JSONArray bebidas = new JSONArray();
		lista = (BasicBSONList) document.get("bebidas");
		for (Object item : lista){
			JSONObject obj = new JSONObject();
			String bebida = item.toString();
			obj.put("descripcion", bebida);
			String descripcion = analizarBebidas(bebida).replace("<br/>", "");
			obj.put("observacion", descripcion);
			if (!"".equals(descripcion))
				bebidas.put(obj);
		}
		resultado.put("bebidas", bebidas);				
		return resultado.toString();
	}
	
	/**
	 * Analiza las actividades f�sicas
	 * @param document Episodio de migra�a
	 * @return catalizadores
	 */
	private static String analizarActividadesFisicas(BasicDBObject document){
		String analisis = "";		
		String actividadFisica = document.get("actividadFisica").toString(); 
		if (actividadFisica.indexOf("4") != -1){
			analisis += "El sedentarismo o la falta de ejercicio aumenta el riesgo de migra�a<br/>";
		}		
		return analisis;
	}
		
	/**
	 * Analiza la actividad f�sica
	 * @param actividadFisica Actividad f�sica reportada
	 * @return catalizador
	 */
	private static String analizarActividadesFisicas(String actividadFisica){
		String analisis = "";				
		if (actividadFisica.indexOf("4") != -1){
			analisis += "El sedentarismo o la falta de ejercicio aumenta el riesgo de migra�a<br/>";
		}		
		return analisis;
	}
	
	/**
	 * Analiza los alimentos
	 * @param document Episodio de migra�a
	 * @return catalizadores
	 */
	private static String analizarAlimentos(BasicDBObject document){
		String analisis = "";		
		String alimentos = document.get("alimentos").toString(); 
		if (alimentos.indexOf("1") != -1){
			analisis += "Consumir m�s de una harina al d�a aumenta el riesgo de migra�a indirectamente<br/>";
		}
		if (alimentos.indexOf("2") != -1){
			analisis += "Consumir comidas r�pidas aumenta el riesgo de migra�a<br/>";
		}
		if (alimentos.indexOf("3") != -1){
			analisis += "Los paquetes o snacks deber�an consumirse moderadamente<br/>";
		}
		if (alimentos.indexOf("6") != -1){
			analisis += "Los dulces suben la tensi�n y por ello deber�an evitarse<br/>";
		}		
		return analisis;
	}
	
	/**
	 * Analiza el alimento reportado
	 * @param alimento alimento reportado
	 * @return catalizador
	 */
	private static String analizarAlimentos(String alimento){
		String analisis = "";				
		if (alimento.indexOf("1") != -1){
			analisis += "Consumir m�s de una harina al d�a aumenta el riesgo de migra�a indirectamente<br/>";
		} else if (alimento.indexOf("2") != -1){
			analisis += "Consumir comidas r�pidas aumenta el riesgo de migra�a<br/>";
		} else if (alimento.indexOf("3") != -1){
			analisis += "Los paquetes o snacks deber�an consumirse moderadamente<br/>";
		} else if (alimento.indexOf("6") != -1){
			analisis += "Los dulces suben la tensi�n y por ello deber�an evitarse<br/>";
		}		
		return analisis;
	}
	
	/**
	 * Analiza las bebidas
	 * @param document Episodio de migra�a
	 * @return catalizadores
	 */
	private static String analizarBebidas(BasicDBObject document){
		String analisis = "";		
		String bebidas = document.get("bebidas").toString(); 
		if (bebidas.indexOf("1") != -1){
			analisis += "Las bebidas gaseosas contienen mucha az�car y por ello deber�an ser evitadas<br/>";
		}
		if (bebidas.indexOf("2") != -1){
			analisis += "Los jugos artificiales deben consumirse con moderaci�n. Se recomienda 1 o 2 por semana<br/>";
		}
		if (bebidas.indexOf("3") != -1){
			analisis += "Utilice endulzantes naturales para los jugos y en lo posible, cons�malos sin az�car<br/>";
		}
		if (bebidas.indexOf("5") != -1){
			analisis += "El caf� puede contribuir a que su mente no descanse completamente y por ello, aumenta el riesgo de migra�a<br/>";
		}		
		return analisis;
	}
	
	/**
	 * Analiza la bebida reportada
	 * @param bebida bebida reportada
	 * @return catalizador
	 */
	private static String analizarBebidas(String bebida){
		String analisis = "";				
		if (bebida.indexOf("1") != -1){
			analisis += "Las bebidas gaseosas contienen mucha az�car y por ello deber�an ser evitadas<br/>";
		} else if (bebida.indexOf("2") != -1){
			analisis += "Los jugos artificiales deben consumirse con moderaci�n. Se recomienda 1 o 2 por semana<br/>";
		} else	if (bebida.indexOf("3") != -1){
			analisis += "Utilice endulzantes naturales para los jugos y en lo posible, cons�malos sin az�car<br/>";
		} else	if (bebida.indexOf("5") != -1){
			analisis += "El caf� puede contribuir a que su mente no descanse completamente y por ello, aumenta el riesgo de migra�a<br/>";
		}		
		return analisis;
	}
}
