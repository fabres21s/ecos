package edu.uniandes.co.arquitectura.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.Document;
import org.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import edu.uniandes.co.arquitectura.model.DAOEpisodios;
import edu.uniandes.co.arquitectura.model.DAOUsuarios;
import edu.uniandes.co.arquitectura.utils.AnalisisEpisodio;

/**
 * Servidor para las peticiones REST 
 * @author Oswaldo
 * @version 1.0.0
 */
@Path("/servidor")
public class ServidorCM {
										
	@POST
	@Path("/registrar")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registrar(String json) throws JSONException {		
		BasicDBObject  document = (BasicDBObject ) JSON.parse(json);		
		String result = DAOEpisodios.registrarEpisodio(new Document(document));
		if (result == null){
			result = AnalisisEpisodio.preAnalisisEpisodio(document);	
		}
		return Response.status(Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/consultar/{documento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultar(@PathParam("documento") String documento) throws JSONException {						
		BasicDBObject result = DAOEpisodios.consultarEpisodiosPaciente(documento);	
		return Response.status(Status.OK).entity(result.toString()).build();		
	}
	
	@GET
	@Path("/consultarF/{documento}/{fechaInicio}/{fechafin}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarFiltros(@PathParam("documento") String documento, @PathParam("fechaInicio") String fechaInicio, @PathParam("fechafin") String fechaFin) throws JSONException {								
		BasicDBObject result = DAOEpisodios.consultarEpisodiosPacienteF(documento, fechaInicio, fechaFin);	
		return Response.status(Status.OK).entity(result.toString()).build();		
	}
	
	@GET
	@Path("/analisis/{episodio}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response analisis(@PathParam("episodio") String episodio) throws JSONException {		
		String analisis = AnalisisEpisodio.analisisEpisodio((BasicDBObject) JSON.parse(episodio));	
		return Response.status(Status.OK).entity(analisis).build();		
	}
	
	@POST
	@Path("/crearUsuario")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response crearUsuario(String json) throws JSONException {		
		String result ="";
		BasicDBObject  document = (BasicDBObject ) JSON.parse(json);				
		if (DAOUsuarios.crearUsuario(document.getString("usuario"), document.getString("clave"), document.getString("rol")))
			result = "El usuario se ha creado exitosamente";
		else
			result = "No se ha podido crear el usuario";
		return Response.status(Status.OK).entity(result).build();
	}
	
	@POST
	@Path("/autenticarUsuario")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response autenticarUsuario(String json) throws JSONException {		
		String result ="";
		BasicDBObject  document = (BasicDBObject ) JSON.parse(json);				
		if (DAOUsuarios.autenticarUsuario(document.getString("usuario"), document.getString("clave")))
			result = "Usuario autenticado correctamente";
		else
			result = "Las credenciales no son correctas";
		return Response.status(Status.OK).entity(result).build();
	}
	
}