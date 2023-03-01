package com.amazon.dmataccountmanager.model;
/*

SQL Query for Table

MySQL:
create table Share(
	share_id INT PRIMARY KEY AUTO_INCREMENT,
	company_name VARCHAR(256),
	price INT,
	lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP
);

*/
public class Share {
	// Attributes
	public String company_name;
	public int price;
	public String lastUpdatedOn;
	public int share_id;
	
	public Share(){
		
	}

	public Share(String company_name, int price, String lastUpdatedOn, int share_id) {

		this.company_name = company_name;
		this.price = price;
		this.lastUpdatedOn = lastUpdatedOn;
		this.share_id = share_id;
	}

	@Override
	public String toString() {
		return "Share [Company_name=" + company_name + ", price=" + price + ", lastUpdatedOn=" + lastUpdatedOn
				+ ", share_id=" + share_id + "]";
	}
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Company Name:\t\t"+company_name);
		System.out.println("Share Price:\t\t"+price);
		System.out.println("Share ID:\t\t"+share_id);
		System.out.println("Last Update Date:\t"+lastUpdatedOn);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
}
