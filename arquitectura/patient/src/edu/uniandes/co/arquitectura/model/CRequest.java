package edu.uniandes.co.arquitectura.model;


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
	private String password;
	private String email;
	private String hash;
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
