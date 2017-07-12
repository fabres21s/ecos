package edu.uniandes.co.arquitectura.model;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.util.JSONParseException;

import edu.uniandes.co.arquitectura.services.ContextCM;

/**
 * Servidor para las peticiones REST 
 * @author Oswaldo
 * @version 1.0.0
 */
public class DAOEpisodios {
			
	private static final String TABLE_EPISODIOS = "episodios";
	private static final String FILTER = "documento";
	private static final String FILTER_ANS = "episodios";
	private static final InsertOneOptions ioo = new InsertOneOptions().bypassDocumentValidation(true);	
	
	/**
	 * Consulta episodios de migraña del paciente 
	 * @param documento Documento del paciente
	 * @return Episodios de migraña
	 */
	public static BasicDBObject consultarEpisodiosPaciente(String documento){		
		MongoDatabase db = ContextCM.getMongoClient().getTestdb();
		MongoCollection<Document> collection = db.getCollection(TABLE_EPISODIOS);			
		Document query = new Document(FILTER, documento);		
		ArrayList<Document> docs = new ArrayList<>();
		collection.find(query).into(docs);
		BasicDBObject json = new BasicDBObject();
		json.append(FILTER_ANS, docs);
		return json;
	}
	
	/**
	 * Consulta episodios de migraña del paciente
	 * según las fechas de inicio y fin
	 * @param documento	Documento del paciente
	 * @param fechaInicio Fecha inicial 
	 * @param fechaFin Fecha final
	 * @return
	 */
	public static BasicDBObject consultarEpisodiosPacienteF(String documento, String fechaInicio, String fechaFin){		
		MongoDatabase db = ContextCM.getMongoClient().getTestdb();
		MongoCollection<Document> collection = db.getCollection(TABLE_EPISODIOS);		
		Document query = new Document(FILTER, documento);		
		query.put("fecha", BasicDBObjectBuilder.start("$gte", fechaInicio).add("$lte", fechaFin).get());		
		ArrayList<Document> docs = new ArrayList<>();
		collection.find(query).into(docs);
		BasicDBObject json = new BasicDBObject();
		json.append(FILTER_ANS, docs);
		return json;
	}
	
	/**
	 * Registra un episodio de migraña
	 * @param documento Episodio de migraña
	 * @return Nulo si el registro ha sido satisfactorio
	 */
	public static String registrarEpisodio(Document documento){			
		try{
						
			MongoDatabase db = ContextCM.getMongoClient().getTestdb();
			MongoCollection<Document> collection = db.getCollection(TABLE_EPISODIOS);
			System.out.println(documento.toString());
	    	collection.insertOne(documento, ioo);	    	
	    	return null;
		}catch(JSONParseException jpe){
			return "El JSON no es válido";
		}catch(Exception e){
			e.printStackTrace();
			return "Error";
		}
    }		
	

}