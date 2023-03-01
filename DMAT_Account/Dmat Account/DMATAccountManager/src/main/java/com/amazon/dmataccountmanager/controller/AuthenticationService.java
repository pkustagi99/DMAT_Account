package com.amazon.dmataccountmanager.controller;

import java.util.List;

import com.amazon.dmataccountmanager.db.UserDAO;
import com.amazon.dmataccountmanager.model.User;

public class AuthenticationService {
	
	//User user = new User();
	
	private static AuthenticationService service = new AuthenticationService();
	UserDAO dao = new UserDAO();
	
	private AuthenticationService(){
		
	}
	
	public static AuthenticationService getInstance() {
		return service;
	}
	
	public boolean loginUser(User user) {
		
		String sql = "SELECT * FROM user WHERE account_number = "+user.account_number+"";
		List<User> users = dao.retrieve(sql);
		
		if(users.size() > 0) {
			User u = users.get(0);
			user.user_id = u.user_id;
			user.user_name = u.user_name;
			user.account_number = u.account_number;
			user.account_balance = u.account_balance;
			user.lastUpdatedOn = u.lastUpdatedOn;
			return true;
		}
		return false; 
	}
	
	public boolean registerUser(User user) {
		return dao.insert(user) > 0;
	}
	
	public boolean updateUser(User user) {
		return dao.update(user) > 0;
	}
	
	public boolean depositAmt(User user) {
		user.account_balance = user.transaction_amount + user.account_balance;
		//return dao.update (user) > 0;
		int result = dao.update(user);
		String message = (result > 0) ? "Deposit Sucessful" : "Deposit Failed. Try Again.."; 
		System.out.println(message);
		return result > 0;
	}
	
	public boolean withdrawAmt(User user) {
		boolean result_return = false;
		if(user.account_balance < user.transaction_amount) {
			System.out.println("Withdraw Amount is greater than the balance kindly select a lower amount");
			result_return = false;
		}
		else {
			user.account_balance =  user.account_balance - user.transaction_amount;
			//return dao.update (user) > 0;
			int result = dao.update(user);
			String message = (result > 0) ? "Withdraw Successfull" : "Withdraw Request Failed. Try Again.."; 
			System.out.println(message);
			result_return = true;
		}
		return result_return;
	}
	
}
