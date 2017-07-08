package edu.uniandes.edu.arquitectura.session;

import java.util.HashMap;
import java.util.Map;

import edu.uniandes.edu.arquitectura.model.CRequest;

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
			if (System.currentTimeMillis() - mapSession.get(id) > 15000) {
				return false;
			}
			return true;
		}
		return false;
	}
}
