package edu.uniandes.co.arquitectura.services;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import edu.uniandes.co.arquitectura.model.CRequest;
import edu.uniandes.co.arquitectura.model.CResponse;
import edu.uniandes.co.arquitectura.model.DAOUsuarios;
import edu.uniandes.co.arquitectura.session.Session;
import edu.uniandes.co.arquitectura.utils.Utils;

@Path("auth")
public class AuthenticationService {
	
	@GET
	@Path("test")
	@Produces({ MediaType.APPLICATION_JSON })
	public CResponse test() {
		CResponse cResponse = new CResponse();
		cResponse.setMessage("Está funcionando el WS "+(new Date()));
		return cResponse;
	}
	
	
	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public CResponse login(CRequest<String> cRequest)  {
		
		
		String result ="";
		if (DAOUsuarios.autenticarUsuario(cRequest.getEmail(), cRequest.getPassword()))
			result = "Usuario autenticado correctamente";
		else
			result = "Las credenciales no son correctas";
		
		
		String hash = Utils.getIntance().sha256(cRequest.getPassword() + ":::"+ System.currentTimeMillis());
		Session.getInstance().add(hash);
		CResponse cResponse = new CResponse();
		cResponse.setMessage("admin.html");
		cResponse.setHash(hash);
//		cResponse.setMessage(result); TODO
		return cResponse;
	}
	
	@POST
	@Path("/validate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public CResponse validate(CRequest<String> cRequest)  {
		CResponse cResponse = new CResponse();
		cResponse.setMessage(String.valueOf(Session.getInstance().get(cRequest.getHash())));
		
		
		return cResponse;
	}
	
	@POST
	@Path("/remove")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public CResponse remove(CRequest<String> cRequest)  {
		CResponse cResponse = new CResponse();
		Session.getInstance().remove(cRequest.getHash());
		
		return cResponse;
	}
}
