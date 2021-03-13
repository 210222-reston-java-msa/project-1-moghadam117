package com.revature.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.RequestHelper;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		final String URI = request.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
		case "login-emp":
			RequestHelper.processLoginEmp(request, response);
			break;
		case "login-man":
			RequestHelper.processLoginMan(request, response);
			break;
		case "logout":
			RequestHelper.processLogout(request, response);
			break;
		case "profile-emp":
			RequestHelper.processUpdate(request, response);
			break;
		case "expense-emp":
			RequestHelper.processFindByAuthorId(request, response);
			break;
		case "submit-emp":
			RequestHelper.processSubmitReimb(request, response);
			break;
		case "reimb-man":
			RequestHelper.processFindByAuthorId(request, response);
			break;	
		case "reimb-status":
			RequestHelper.processFindByStatus(request, response);
			break;
		case "reimb-all":
			RequestHelper.processFindAll(request, response);
			break;
		case "resolve-man":
			RequestHelper.processResolve(request, response);
			break;
		case "emp-all":
			RequestHelper.processFindAllEmp(request, response);
			break;
			
			
		case "error":
			RequestHelper.processError(request,response);
			break;
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}