package edu.uniandes.edu.arquitectura.services;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.edu.arquitectura.model.CResponse;

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
}
