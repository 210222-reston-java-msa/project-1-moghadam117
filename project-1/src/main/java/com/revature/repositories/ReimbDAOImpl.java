package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import jdk.internal.org.jline.utils.Log;

public class ReimbDAOImpl implements ReimbDAO {
	private static Logger log = Logger.getLogger(UserDAOImpl.class);
	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		try {
					
					Connection conn = ConnectionUtil.getConnection();
					
					String sql = "select reimb_id, reimb_amount , reimb_description , reimb_submitted , reimb_resolved , reimb_author, reimb_resolver, reimb_status , reimb_type from ers_reimbursement er inner join ers_reimbursement_status ers on er.reimb_status_id = ers .reimb_status_id inner join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id order by reimb_id asc";
					
					PreparedStatement stmt = conn.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						int reimbId = rs.getInt("REIMB_ID");
						int amount = rs.getInt("REIMB_AMOUNT");
						String description = rs.getString("REIMB_DESCRIPTION");
						String submitDate = rs.getString("REIMB_SUBMITTED");
						String resolveDate = rs.getString("REIMB_RESOLVED");
						int authorId = rs.getInt("REIMB_AUTHOR");
						int resolverId = rs.getInt("REIMB_RESOLVER");
						String statusName = rs.getString("reimb_status");
						String typeName = rs.getString("reimb_type");
						
						
						Reimbursement r = new Reimbursement(reimbId, amount, description, submitDate, resolveDate, authorId, resolverId, new Status(1, statusName), new Type (1, typeName));
						
						list.add(r);
						
					}

				} catch (SQLException ex) {
					log.warn("Unable to retrieve all users", ex);

				}

				return list;
	}



	@Override
	public List<Reimbursement> findByStatus(int statusId) {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		try {
					
					Connection conn = ConnectionUtil.getConnection();
					
					String sql = "select reimb_id, reimb_amount , reimb_description , reimb_submitted , reimb_resolved , reimb_author, reimb_resolver, reimb_status , reimb_type from ers_reimbursement er inner join ers_reimbursement_status ers on er.reimb_status_id = ers .reimb_status_id inner join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id where ers.reimb_status_id = ?";
					
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, statusId);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						int reimbId = rs.getInt("REIMB_ID");
						int amount = rs.getInt("REIMB_AMOUNT");
						String description = rs.getString("REIMB_DESCRIPTION");
						String submitDate = rs.getString("REIMB_SUBMITTED");
						String resolveDate = rs.getString("REIMB_RESOLVED");
						int authorId = rs.getInt("REIMB_AUTHOR");
						int resolverId = rs.getInt("REIMB_RESOLVER");
						String statusName = rs.getString("reimb_status");
						String typeName = rs.getString("reimb_type");
						
						
						Reimbursement r = new Reimbursement(reimbId, amount, description, submitDate, resolveDate, authorId, resolverId, new Status(100, statusName), new Type (1, typeName));
						
						list.add(r);
						
					}

				} catch (SQLException ex) {
					log.warn("Unable to retrieve all users", ex);

				}

				return list;
	}
	public List<Reimbursement> findByAuthorId(int aId) {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		try {
					
					Connection conn = ConnectionUtil.getConnection();
					
					String sql = "select reimb_id, reimb_amount , reimb_description , reimb_submitted , reimb_resolved , reimb_author, reimb_resolver, reimb_status , reimb_type from ers_reimbursement er inner join ers_reimbursement_status ers on er.reimb_status_id = ers .reimb_status_id inner join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id where er.reimb_author = ?";
					
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, aId);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						int reimbId = rs.getInt("REIMB_ID");
						int amount = rs.getInt("REIMB_AMOUNT");
						String description = rs.getString("REIMB_DESCRIPTION");
						String submitDate = rs.getString("REIMB_SUBMITTED");
						String resolveDate = rs.getString("REIMB_RESOLVED");
						int authorId = rs.getInt("REIMB_AUTHOR");
						int resolverId = rs.getInt("REIMB_RESOLVER");
						String statusName = rs.getString("reimb_status");
						String typeName = rs.getString("reimb_type");
						
						
						Reimbursement r = new Reimbursement(reimbId, amount, description, submitDate, resolveDate, authorId, resolverId, new Status(100, statusName), new Type (1, typeName));
						
						list.add(r);
						
					}

				} catch (SQLException ex) {
					log.warn("Unable to retrieve all users", ex);

				}

				return list;
	}
	@Override
	public void updateReimbMan(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitReimbEmp(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "insert into ers_reimbursement (reimb_amount, reimb_description, reimb_submitted, reimb_author, reimb_status_id, reimb_type_id) values (?,?, current_timestamp, ?, 1 ,?) ";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setInt(1, reimbursement.getAmount());
			stmt.setString(2, reimbursement.getDescription());
			stmt.setInt(3, reimbursement.getAuthorId());
			stmt.setInt(4, reimbursement.getType().getTypeId());
			

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		
	}
	}

	@Override
	public void resolveReimbMan(Reimbursement reimbursement) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update ers_reimbursement set reimb_resolved = current_timestamp , reimb_resolver=?, reimb_status_id=? where reimb_id=?;";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			
			stmt.setInt(1, reimbursement.getResolverId());
			stmt.setInt(2, reimbursement.getStatus().getStatusId());
			stmt.setInt(3, reimbursement.getReimbId());
			
			

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		
	}
		
	}
	/*
	 * @Override public List<Reimbursement> findByUserId() { // TODO Auto-generated
	 * method stub return null; }
	 */
}
