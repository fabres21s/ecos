package edu.uniandes.edu.arquitectura.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.edu.arquitectura.model.CRequest;
import edu.uniandes.edu.arquitectura.model.CResponse;
import edu.uniandes.edu.arquitectura.session.Session;

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
		Session.getInstance().add(cRequest.getId());
		CResponse cResponse = new CResponse();
		cResponse.setMessage("admin.html");
		return cResponse;
	}
	
	@POST
	@Path("/validate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public CResponse validate(CRequest<String> cRequest)  {
		CResponse cResponse = new CResponse();
		cResponse.setMessage(String.valueOf(Session.getInstance().get(cRequest.getId())));
		
		
		return cResponse;
	}
}
