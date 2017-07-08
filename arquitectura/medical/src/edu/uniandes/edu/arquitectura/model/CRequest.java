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
	
	private String startDate;
	private String endDate;
	private Integer customerId;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
