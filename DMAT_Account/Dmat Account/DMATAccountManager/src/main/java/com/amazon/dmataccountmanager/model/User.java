package com.amazon.dmataccountmanager.model;
/*

SQL Query for Table

MySQL:
create table User(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	user_name VARCHAR(256),
	account_number INT NOT NULL UNIQUE,
	password VARCHAR(256),
	account_balance INT,
	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
);

*/
public class User {
	
	// Attributes
	public String user_name;
	public int account_number;
	public String password;
	public int account_balance;
	public String lastUpdatedOn;
	public int user_id;
	public int transaction_amount;

	
	public User() {
		
	}
	
	public User(int user_id, String user_name, int account_number, String password, int account_balance,
			String lastUpdatedOn, int transaction_amount) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.account_number = account_number;
		this.password = password;
		this.account_balance = account_balance;
		this.lastUpdatedOn = lastUpdatedOn;
		this.transaction_amount = transaction_amount;
	}
	@Override
	public String toString() {
		return "User [user_name=" + user_name + ", account_number=" + account_number + ", password=" + password
				+ ", account_balance=" + account_balance + ", lastUpdatedOn=" + lastUpdatedOn + ", user_id=" + user_id
				+ "]";
	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("User Name:\t\t"+user_name);
		System.out.println("Account_Number:\t\t"+account_number);
		System.out.println("Account Balance:\t\t"+account_balance);
		System.out.println("User_Id:\t"+user_id);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
}
