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

		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); 

		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();

		log.info("User attempted to login with username: " + username);
		User u = ManagerService.confirmLoginEmp(username, password);

		if (u != null) {
		
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			pw.println(om.writeValueAsString(u));

			log.info(username + " has successfully logged in");
		} else {
			res.setStatus(204); 
		}

	}

	public static void processLoginMan(HttpServletRequest req, HttpServletResponse res) throws IOException {

	
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); 

		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();

		log.info("User attempted to login with username: " + username);
		User u = ManagerService.confirmLoginMan(username, password);

		if (u != null) {
			
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			pw.println(om.writeValueAsString(u));

			log.info(username + " has successfully logged in");
		} else {
			res.setStatus(204);
		}

	}

	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession session = req.getSession(false);
		

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

		
		UpdateTemplate updateAttempt = om.readValue(body, UpdateTemplate.class);

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
		log.info("Manager attempted to get all the reimbursements ");
	}

	public static void processFindByAuthorId(HttpServletRequest req, HttpServletResponse res) throws IOException {

		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		

		IdTemplate idAttempt = om.readValue(body, IdTemplate.class); 

		int aId = idAttempt.getId();

		res.setContentType("application/json");

		List<Reimbursement> allReimById = ReimbursementService.findByAuthorId(aId);
		System.out.println(allReimById);
		log.info("User requested reimbursment by ID");
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

		
		SubmitTemplate submitAttempt = om.readValue(body, SubmitTemplate.class); 

		int amount = submitAttempt.getAmount();
		String description = submitAttempt.getDescription();
		int authorId = submitAttempt.getAuthorId();
		int typeId = submitAttempt.getTypeId();
		

		log.info("User attempted to Submit Reimbursement");
		
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
		

		IdTemplate idAttempt = om.readValue(body, IdTemplate.class);

		int statusId = idAttempt.getId();

		res.setContentType("application/json");

		List<Reimbursement> allReimByStatus = ReimbursementService.findByStatus(statusId);
		System.out.println(statusId);
		log.info("User requested reimbursments list by status");
		String json = om.writeValueAsString(allReimByStatus);

		PrintWriter pw = res.getWriter();

		pw.println(json);
	}

	/*
	 * public static void processFindByUserId(HttpServletRequest req,
	 * HttpServletResponse res) throws IOException {
	 * 
	 * BufferedReader reader = req.getReader(); StringBuilder s = new
	 * StringBuilder();
	 * 
	 * String line = reader.readLine(); while (line != null) { s.append(line); line
	 * = reader.readLine(); }
	 * 
	 * String body = s.toString();
	 * 
	 * 
	 * IdTemplate idAttempt = om.readValue(body, IdTemplate.class);
	 * 
	 * int id = idAttempt.getId();
	 * 
	 * res.setContentType("application/json");
	 * 
	 * List<Reimbursement> allReimById = ReimbursementService.findByUserID(id);
	 * System.out.println(allReimById);
	 * log.info("User requested reimbursment by ID"); String json =
	 * om.writeValueAsString(allReimById);
	 * 
	 * PrintWriter pw = res.getWriter();
	 * 
	 * pw.println(json); }
	 */
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

		
		ResolveTemplate resolveAttempt = om.readValue(body, ResolveTemplate.class);

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
		log.info("Manager attempted find All employees");
	}


}
