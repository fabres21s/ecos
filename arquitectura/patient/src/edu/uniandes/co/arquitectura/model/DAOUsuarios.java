package edu.uniandes.co.arquitectura.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneOptions;

import edu.uniandes.co.arquitectura.services.ContextCM;
import edu.uniandes.co.arquitectura.utils.PasswordHash;

/**
 * Servidor para las peticiones REST 
 * @author Oswaldo
 * @version 1.0.0
 */
public class DAOUsuarios {
			
	private static final String TABLE_USUARIOS = "usuarios";
	private static final String FILTER = "usuario";
	private static final String ROL_DOCTOR = "Doctor";
	private static final String ROL_PACIENTE = "Paciente";
	private static final InsertOneOptions ioo = new InsertOneOptions().bypassDocumentValidation(true);	
	
	/**
	 * Crea un usuario del rol Doctor con el id
	 * y la clave especificados
	 * @param usuario
	 * @param clave
	 * @return
	 */
	public static boolean crearUsuarioDoctor(String usuario, String clave){		
		return crearUsuario(usuario, clave, ROL_DOCTOR);
	}
	
	/**
	 * Crea un usuario del rol Paciente con el id
	 * y la clave especificados
	 * @param usuario
	 * @param clave
	 * @return
	 */
	public static boolean crearUsuarioPaciente(String usuario, String clave){		
		return crearUsuario(usuario, clave, ROL_PACIENTE);
	}
	
	/**
	 * Crea un usuario con el rol especificado con el id
	 * y la clave especificados
	 * @param usuario
	 * @param clave
	 * @return
	 */
	public static boolean crearUsuario(String usuario, String clave, String rol){		
		try{	
			MongoDatabase db = ContextCM.getMongoClient().getTestdb();
			MongoCollection<Document> collection = db.getCollection(TABLE_USUARIOS);
			Document documento = new Document();
			documento.append("usuario", usuario);
			String hash = PasswordHash.createHash(clave);
			documento.append("clave", hash);
			documento.append("rol", rol);
	    	collection.insertOne(documento, ioo);
	    	return true;		
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Auntentica el usuario con las credenciales especificadas
	 * @param usuario
	 * @param clave
	 * @return
	 */
	public static boolean autenticarUsuario(String usuario, String clave){
		MongoDatabase db = ContextCM.getMongoClient().getTestdb();
		MongoCollection<Document> collection = db.getCollection(TABLE_USUARIOS);			
		Document query = new Document(FILTER, usuario);		
		ArrayList<Document> docs = new ArrayList<>();
		collection.find(query).into(docs);
		boolean autenticacionOK = false;
		for (Document doc : docs){
			try {				
				String goodHash = doc.getString("clave");
				autenticacionOK = PasswordHash.validatePassword(clave, goodHash);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {				
			}			
		}		
		return autenticacionOK;
	}
	

}