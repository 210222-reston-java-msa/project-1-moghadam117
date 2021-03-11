package com.revature.repositories;

import java.util.List;

import com.revature.models.Reimbursement;


public interface ReimbDAO {
	public List <Reimbursement> findAll();
	public List <Reimbursement> findByUserId();
	public List <Reimbursement> findByStatus(int statusId);
	public void updateReimbMan (Reimbursement reimbursement);
	public void submitReimbEmp (Reimbursement reimbursement);
	public void resolveReimbMan (Reimbursement reimbursement);
	
}
