package com.amazon.dmataccountmanager.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.dmataccountmanager.model.User;

public class UserDAO implements DAO<User>{

	DB db = DB.getInstance();
	
	//@Override
	public int insert(User object) {
		String sql = "INSERT INTO user (user_name, account_number, password, account_balance) VALUES ('"+object.user_name+"', "+object.account_number+", '"+object.password+"', "+object.account_balance+")";
		return db.executeSQL(sql);
	}

	//@Override
	public int update(User object) {
		String sql = "UPDATE user set user_name = '"+object.user_name+"', account_number = "+object.account_number+", account_balance = "+object.account_balance+" WHERE user_id = "+object.user_id;
		return db.executeSQL(sql);
	}

	//@Override
	public int delete(User object) {
		String sql = "DELETE FROM user WHERE account_number = "+object.account_number+"";
		return db.executeSQL(sql);
	}

	//@Override
	public List<User> retrieve() {
		
		String sql = "SELECT * from user";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.user_id = set.getInt("user_id");
				user.user_name = set.getString("user_name");
				user.account_number = set.getInt("account_number");
				user.password = set.getString("password");
				user.account_balance = set.getInt("account_balance");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return users;
	}
	
	//@Override
	public List<User> retrieve(String sql) {
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			while(set.next()) {
				
				User user = new User();
				
				// Read the row from ResultSet and put the data into User Object
				user.user_id = set.getInt("user_id");
				user.user_name = set.getString("user_name");
				user.account_number = set.getInt("account_number");
				user.password = set.getString("password");
				user.account_balance = set.getInt("account_balance");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return users;
	}
	
}
