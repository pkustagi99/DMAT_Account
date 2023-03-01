package com.amazon.dmataccountmanager.model;

/*

MySQL:
create table UserShares(
	userShare_id INT PRIMARY KEY AUTO_INCREMENT,
	shareCount INT,
    share_id INT,
    user_id INT,
	FOREIGN KEY (share_id) REFERENCES Share(share_id),
	FOREIGN KEY (user_id) REFERENCES User(user_id),
	company_name VARCHAR(256)
);

*/

public class UserShares {

	//Attributes
	public int share_id;
	public int user_id;
	public int shareCount;
	public String company_name;
	public int userShare_id;

	
	public UserShares() {
		
	}
	

	
	public UserShares(int share_id, int user_id, int shareCount, String company_name, int userShare_id) {
		this.share_id = share_id;
		this.user_id = user_id;
		this.shareCount = shareCount;
		this.company_name = company_name;
		this.userShare_id = userShare_id;
	}

	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("User Share Id:\t\t"+userShare_id);
		System.out.println("Share_id:\t\t"+share_id);
		System.out.println("Share_count:\t\t"+shareCount);
		System.out.println("User ID:\t\t"+user_id);
		System.out.println("Company Name:\t\t"+company_name);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}



	@Override
	public String toString() {
		return "UserShares [share_id=" + share_id + ", user_id=" + user_id + ", shareCount=" + shareCount
				+ ", company_name=" + company_name + ", userShare_id=" + userShare_id + "]";
	}
	
}
