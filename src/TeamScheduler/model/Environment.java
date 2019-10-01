/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamScheduler.model;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 *
 * @author james.clair
 */
public class Environment {
	private final User currentUser;
	private final String password;
	private static Environment instance;

	private Environment(User currentUser, String password) {
		this.password = password;
		this.currentUser = currentUser;
	}

	public static Environment getInstance(User currentUser, String password) {
		if(instance == null) {
			instance = new Environment(currentUser, password);
		}
		return instance;
	}

	public static Environment getInstance() {
		return instance;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public String getPassword() {
		return password;
	}


}
