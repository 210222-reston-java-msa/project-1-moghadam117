package com.revature;

import java.sql.Timestamp;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.services.ManagerService;
import com.revature.services.ReimbursementService;

public class Driver {

	public static void main(String[] args) {
		
		ManagerService mg = new ManagerService();
		
		ReimbursementService rs = new ReimbursementService();
		System.out.println(rs.findAll());
		Reimbursement r = new Reimbursement(0, 400, "xxx", "s", 2, new Status(1,"ss") ,new Type(2, "vv"));
		//rs.submitReimbEmp(r);
	//	Reimbursement reimb = new Reimbursement(0, 200, "Xyz", "s", 2, new Status (1, "ss"), new Type(1, "ss"));
		//rs.submitReimbEmp(reimb);
	//	System.out.println(rs.findByUserID(2));
//		mg.confirmUpdate(2, "ardy1234", "1234", "ardy", "moghi", "ardy@yahoo.com");
//System.out.println(mg.findAll());
		//System.out.println(mg.findByUsername("ardy123"));
		//.out.println(mg.confirmLoginMan("saman123", "12345"));
		//System.out.println(mg.confirmLoginEmp("ardy123", "12345"));
		//System.out.println(mg.confirmUpdate(2, "ardy1234", "1234", "ardy", "moghi", "ardy@yahoo.com"));
		
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println(timestamp);
		Reimbursement z = new Reimbursement(8, 1,  new Status(3, "xx"));
		rs.resolveReimbMan(z);
		//System.out.println(rs.findByStatus(3));
	}
}
