package edu.uniandes.co.arquitectura.session;

import java.util.HashMap;
import java.util.Map;

import edu.uniandes.co.arquitectura.model.CRequest;

public class Session {

	private static Session instance = new Session();
	
	public static Session getInstance() {
		return instance;
	}
	
	private Map<String, Long> mapSession;
	
	private Session() {
		mapSession = new HashMap<String, Long>();
	}
	
	public void add(String id) {
		mapSession.put(id, System.currentTimeMillis());
	}
	
	public boolean get(String id) {
		if (mapSession.get(id) != null) {
			if (System.currentTimeMillis() - mapSession.get(id) > 150000) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public void remove(String hash) {
		mapSession.remove(hash);
	}
}
