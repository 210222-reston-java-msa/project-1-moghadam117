package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class ManagerService {
	public static UserDAO uDao = new UserDAOImpl();
	
	
	public static List<User> findAll() {
		return uDao.findAll();
	}

	
	
	public static User findByUsername(String username) {
		List<User> all = uDao.findAll();
		//List<Employee> all = findAll(); // another way to do it
		
		for (User u : all) { // filtering with an enhanced for-loop!
			if (u.getUsername().equals(username)) {
				return u; // we return the Employee object with a matching ID
			}
		}
		
		return null;
	}
	public static User findByEmail(String email) {
		List<User> all = uDao.findAll();
		//List<Employee> all = findAll(); // another way to do it
		
		for (User u : all) { // filtering with an enhanced for-loop!
			if (u.getEmail().equals(email)) {
				return u; // we return the Employee object with a matching ID
			}
		}
		
		return null;
	}
	
	// confirm login method
	public static User confirmLoginMan(String username, String password) {
		
		// we use the above method
		User u = findByUsername(username);
		
		if (u == null) {
			return null;
		}
		
		if (u.getPassword().equals(password) && u.getRole().getRoleName().equals("manager")) {
			return u;
		} else {
			return null;
		}
	}
public static User confirmLoginEmp(String username, String password) {
		
		// we use the above method
		User u = findByUsername(username);
		
		if (u == null) {
			return null;
		}
		
		if (u.getPassword().equals(password) && u.getRole().getRoleName().equals("employee")) {
			return u;
		} else {
			return null;
		}
	}
public static User confirmUpdate(int userId, String username, String password, String firstname, String lastname, String email) {
	
	// we use the above method
	User u = findByUsername(username);
	User u2 = findByEmail(email);
	if (u == null && u2 == null) {
		User u3 = new User(userId, username, password, firstname, lastname, email);
		uDao.updateUser(u3);
		return u3;
	}
	
	 else {
		return null;
	}
}
	
}
