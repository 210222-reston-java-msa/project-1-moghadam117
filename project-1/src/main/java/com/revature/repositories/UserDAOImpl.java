package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO{
	
	private static Logger log = Logger.getLogger(UserDAOImpl.class);
	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();
try {
			
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM ers_users inner join ers_users_role on ers_users.user_role_id = ers_users_role.ers_users_role_id order by ers_users_id";
			
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("ERS_USERS_ID");
				String first_name = rs.getString("USER_FIRST_NAME");
				String last_name = rs.getString("USER_LAST_NAME");
				String username = rs.getString("ERS_USERNAME");
				String password = rs.getString("ERS_PASSWORD");
				String email = rs.getString("USER_EMAIL");
				int role_id = rs.getInt("USER_ROLE_ID");
				String role_name = rs.getString("USER_ROLE");
				
				User u = new User(id,username, password, first_name, last_name, email, new Role(role_id, role_name));
				list.add(u);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);

		}

		return list;
	}
		
	

	//@Override
//	public User findByUserID(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}



	@Override
	public void updateUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String SQL = "update ers_users set ERS_USERNAME=?, ERS_PASSWORD=?, USER_FIRST_NAME=?, USER_LAST_NAME=?, USER_EMAIL=? where ERS_USERS_ID=?";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(SQL);

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getUserId());

			stmt.execute();
			log.info("New reimbursement created successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn("Unable to create new reimbursement", e);

		
	}

}
}
