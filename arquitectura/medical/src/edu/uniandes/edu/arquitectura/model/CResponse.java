package edu.uniandes.edu.arquitectura.model;

/**
 * Modelo para representar una respuesta de parte del Web Service
 * @author fabiosierra
 *
 */
public class CResponse {

	private Object response;
	private String message;
	
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
