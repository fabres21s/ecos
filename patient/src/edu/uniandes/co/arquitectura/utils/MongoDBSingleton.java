package edu.uniandes.co.arquitectura.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;

/**
 * Servidor para las peticiones REST 
 * @author Oswaldo
 * @version 1.0.0
 */
public class MongoDBSingleton{
		
	private static MongoDBSingleton mDbSingleton;
	private static MongoClient mongoClient;
	private static MongoDatabase db;
	
	private MongoDBSingleton() {		
	};

	/**
	 * Crea la conexi�n con la base de datos MongoDB
	 * @param dbHost IP o hostname del servidor
	 * @param dbPort Puerto de conexi�n
	 * @param dbName Nombre de la base de datos
	 * @param maxConnectionsPerHost M�ximo de conexiones por host
	 * @param minconnectionsPerHost M�nimo de conexiones por host
	 * @param maxConnectionIdleTime M�ximo tiempo de conexi�n sin realizar peticiones
	 * @return Conexi�n a la base de datos inicializada
	 */
	public static MongoDBSingleton getInstance(String dbHost, int dbPort, String dbName, 
					int maxConnectionsPerHost, int minconnectionsPerHost, int maxConnectionIdleTime) {
		try {
			if (mDbSingleton == null) {
				mDbSingleton = new MongoDBSingleton();
				MongoClientOptions options = new MongoClientOptions.Builder().connectionsPerHost(maxConnectionsPerHost)
											.minConnectionsPerHost(minconnectionsPerHost)
											.maxConnectionIdleTime(maxConnectionIdleTime)											
											.build();				
				mongoClient = new MongoClient(dbHost + ":" + dbPort, options);		        
				System.out.println("MongoDB initialized");				
				System.out.println("MaxConnectionsPerHost: " + mongoClient.getMongoClientOptions().getConnectionsPerHost());
				System.out.println("MaxConnectionIdleTime: " + mongoClient.getMongoClientOptions().getMaxConnectionIdleTime());
				System.out.println("MinConnectionsPerHost: " + mongoClient.getMongoClientOptions().getMinConnectionsPerHost());				
				db = mongoClient.getDatabase(dbName);			
			}
		} catch (Exception e) {}
		return mDbSingleton;
	}

	/**
	 * Retorna la base de datos 
	 * @return Base de datos MongoDB
	 */
	public MongoDatabase getTestdb() {		
		return db;
	}
	
	/**
	 * Retorna la conexi�n de la base de datos
	 * @return Conexi�n a base de datos MongoDB
	 */
	public MongoClient getConnection(){
		return mongoClient;
	}
}