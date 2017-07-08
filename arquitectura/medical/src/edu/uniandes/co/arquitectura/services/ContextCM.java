package edu.uniandes.co.arquitectura.services;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.uniandes.co.arquitectura.utils.MongoDBSingleton;

/**
 * Componente que inicializar la conexi�n con la base de datos
 * @author Oswaldo
 * @version 1.0.0
 */
public class ContextCM implements ServletContextListener {

	private static MongoDBSingleton connection;	//Conexi�n a la base de datos
	
	/**
	 * Acciones a realizar con el contexto inicializado
	 */
	public void contextInitialized(ServletContextEvent ctx) {
		String hostName = ctx.getServletContext().getInitParameter("mondodb.hostname");
		int port = Integer.parseInt(ctx.getServletContext().getInitParameter("mondodb.port"));
		String dataBase = ctx.getServletContext().getInitParameter("mondodb.database");
		int maxConnectionsPerHost = Integer.parseInt(ctx.getServletContext().getInitParameter("mondodb.maxConnectionsPerHost"));		
		int minconnectionsPerHost = Integer.parseInt(ctx.getServletContext().getInitParameter("mondodb.minconnectionsPerHost"));
		int maxConnectionIdleTime = Integer.parseInt(ctx.getServletContext().getInitParameter("mondodb.maxConnectionIdleTime"));
		connection = MongoDBSingleton.getInstance(hostName, port, dataBase, 
				maxConnectionsPerHost, minconnectionsPerHost, maxConnectionIdleTime);		
	}
	
	/**
	 * Acciones a realizar con el contexto finalizado
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		connection.getConnection().close();
	}
	
	/**
	 * Retorna la conexi�n a la base de datos
	 * @return conexi�n
	 */
	public static MongoDBSingleton getMongoClient(){
		return connection;
	}

}
