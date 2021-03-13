package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbDAO;
import com.revature.repositories.ReimbDAOImpl;

public class ReimbursementService {
public static ReimbDAO rDao = new ReimbDAOImpl();
	
	
public void submitReimbEmp (Reimbursement reimbursement) {
	ReimbursementService.rDao.submitReimbEmp(reimbursement);
	}

public static List<Reimbursement> findAll() {
	return rDao.findAll();
}

public static List<Reimbursement> findByAuthorId(int aId)  {
	return rDao.findByAuthorId(aId);
	
}

public static List<Reimbursement> findByStatus(int statusId) {
	return rDao.findByStatus(statusId);
}

public static void resolveReimbMan (Reimbursement reimbursement) {
	rDao.resolveReimbMan(reimbursement);
}

/*
 * public static List<Reimbursement> findByUserID(int id) { List<Reimbursement>
 * all = rDao.findAll(); List<Reimbursement> filter = new
 * ArrayList<Reimbursement>();
 * 
 * for (Reimbursement r : all) { if (r.getAuthorId() == id ){ filter.add(r);
 * 
 * } return filter; }
 * 
 * return null; }
 */
}
