package edu.uniandes.edu.arquitectura.model;


/**
 * 
 * Modelo para representar un objeto de consulta al Web Service
 * 
 * @author fabiosierra
 *
 * @param <T> el tipo de dato con el que se va a recibir la petici�n (para este caso en particular, una entidad)
 */
public class CRequest<T> {

	
	private T data;
	private String id;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
