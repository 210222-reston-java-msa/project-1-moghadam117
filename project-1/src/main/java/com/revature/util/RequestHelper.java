package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.IdTemplate;
import com.revature.models.LoginTemplate;
import com.revature.models.Reimbursement;
import com.revature.models.ResolveTemplate;
import com.revature.models.Status;
import com.revature.models.SubmitTemplate;
import com.revature.models.Type;
import com.revature.models.UpdateTemplate;
import com.revature.models.User;
import com.revature.services.ManagerService;
import com.revature.services.ReimbursementService;

public class RequestHelper {

	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();

	public static void processLoginEmp(HttpServletRequest req, HttpServletResponse res) throws IOException {

		// We want to turn whatever we recieve as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		// I'm going to build a model called LoginTemplate which holds a username and
		// passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();

		log.info("User attempted to login with username: " + username);
		User u = ManagerService.confirmLoginEmp(username, password);

		if (u != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			pw.println(om.writeValueAsString(u));

			log.info(username + " has successfully logged in");
		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}

	}

	public static void processLoginMan(HttpServletRequest req, HttpServletResponse res) throws IOException {

		// We want to turn whatever we recieve as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		// I'm going to build a model called LoginTemplate which holds a username and
		// passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();

		log.info("User attempted to login with username: " + username);
		User u = ManagerService.confirmLoginMan(username, password);

		if (u != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			pw.println(om.writeValueAsString(u));

			log.info(username + " has successfully logged in");
		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}

	}

	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession session = req.getSession(false);
		// Log.info("Processing logout");

		if (session != null) {
			String username = (String) session.getAttribute("username");
			log.info(username + "has logged out");

			session.invalidate();
		}

		res.setStatus(200);
	}

	public static void processUpdate(HttpServletRequest req, HttpServletResponse res) throws IOException {

		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		// I'm going to build a model called LoginTemplate which holds a username and
		// passwrod
		UpdateTemplate updateAttempt = om.readValue(body, UpdateTemplate.class); // from JSON --> Java Object

		String firstname = updateAttempt.getFirstname();
		String lastname = updateAttempt.getLastname();
		String email = updateAttempt.getEmail();
		String username = updateAttempt.getUsername();
		String password = updateAttempt.getPassword();

		log.info("User attempted to update");
		HttpSession session = req.getSession(false);
		User temp = new User();
		temp = ManagerService.findByUsername((String) session.getAttribute("username"));
		int userId = temp.getUserId();
		User u = ManagerService.confirmUpdate(userId, username, password, firstname, lastname, email);

		if (u != null) {

			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			pw.println(om.writeValueAsString(u));

			log.info(username + " has successfully updated");
		} else {
			res.setStatus(204);
		}

	}

	public static void processFindAll(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");

		List<Reimbursement> allReim = ReimbursementService.findAll();

		String json = om.writeValueAsString(allReim);

		PrintWriter pw = res.getWriter();

		pw.println(json);
	}

	public static void processFindByUserId(HttpServletRequest req, HttpServletResponse res) throws IOException {

		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		//log.info(body);

		IdTemplate idAttempt = om.readValue(body, IdTemplate.class); // from JSON --> Java Object

		int id = idAttempt.getId();

		res.setContentType("application/json");

		List<Reimbursement> allReimById = ReimbursementService.findByUserID(id);
		System.out.println(allReimById);
		log.info("User requested reimbursments list");
		String json = om.writeValueAsString(allReimById);

		PrintWriter pw = res.getWriter();

		pw.println(json);
	}
	
	public static void processSubmitReimb(HttpServletRequest req, HttpServletResponse res) throws IOException {

		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		// I'm going to build a model called LoginTemplate which holds a username and
		// passwrod
		SubmitTemplate submitAttempt = om.readValue(body, SubmitTemplate.class); // from JSON --> Java Object

		int amount = submitAttempt.getAmount();
		String description = submitAttempt.getDescription();
		int authorId = submitAttempt.getAuthorId();
		int typeId = submitAttempt.getTypeId();
		

		log.info("User attempted to Submit");
		
		Reimbursement reimb = new Reimbursement(0, amount, description, "s", authorId, new Status (1, "ss"), new Type(typeId, "ss"));
		ReimbursementService rs = new ReimbursementService();
		rs.submitReimbEmp(reimb);
		
		
		

		if (reimb != null) {

			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			pw.println(om.writeValueAsString(reimb));

			log.info("New Reimbursement Created");
		} else {
			res.setStatus(204);
		}

	}
	public static void processFindByStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");

		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		//log.info(body);

		IdTemplate idAttempt = om.readValue(body, IdTemplate.class); // from JSON --> Java Object

		int statusId = idAttempt.getId();

		res.setContentType("application/json");

		List<Reimbursement> allReimByStatus = ReimbursementService.findByStatus(statusId);
		System.out.println(statusId);
		log.info("User requested reimbursments list");
		String json = om.writeValueAsString(allReimByStatus);

		PrintWriter pw = res.getWriter();

		pw.println(json);
	}
	public static void processResolve(HttpServletRequest req, HttpServletResponse res) throws IOException {

		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		// I'm going to build a model called LoginTemplate which holds a username and
		// passwrod
		ResolveTemplate resolveAttempt = om.readValue(body, ResolveTemplate.class); // from JSON --> Java Object

		int reimbId = resolveAttempt.getReimbId();
		
		int statusId = resolveAttempt.getStatusId();
		

		log.info("Manager attempted to Resolve");
		HttpSession session = req.getSession(false);
		User temp = new User();
		temp = ManagerService.findByUsername((String) session.getAttribute("username"));
		int resolverId = temp.getUserId();
		Reimbursement resolve = new Reimbursement(reimbId, resolverId, new Status(statusId,"xx"));
		
		try {
			ReimbursementService.resolveReimbMan(resolve);
			res.setStatus(200);
			log.info(resolverId + " has successfully resolved" + reimbId);
			
		} catch (Exception e) {
			e.printStackTrace();		}
		

		

	}
public static void processError(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		try {
			req.getRequestDispatcher("error.html").forward(req, res);
			// we do NOT create a new request
			// we also maintain the url....
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void processFindAllEmp(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		List<User> allUsers = ManagerService.findAll();
		

		String json = om.writeValueAsString(allUsers);

		PrintWriter pw = res.getWriter();

		pw.println(json);
	}


}
