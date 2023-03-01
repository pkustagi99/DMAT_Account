package com.amazon.dmataccountmanager.model;
/*

MySQL:
create table transaction(
	transaction_id INT PRIMARY KEY AUTO_INCREMENT,
	shareCount INT,
	pricePerShare FLOAT,
	transactionCharges FLOAT,
	sttCharges FLOAT,
	type INT,
	share_id INT,
	transactedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (share_id) REFERENCES Share(share_id)
);

*/
public class Transaction {

	//Attributes
	public int share_id;
	public int shareCount;
	public float pricePerShare;
	public double transactionCharges;
	public double sttCharges;
	public int type;
	public String transactedOn;
	public int transaction_id;
	public int user_id;
	
	public Transaction() {
		
	}
	
	@Override
	public String toString() {
		return "UserShares [share_id=" + share_id + ", shareCount=" + shareCount + ", pricePerShare="
				+ pricePerShare + ", transactionCharges=" + transactionCharges + ", sttCharges=" + sttCharges
				+ ", type=" + type + ", transactedOn=" + transactedOn + ", transaction_id=" + transaction_id + ",user_id=" + user_id+"]";
	}

	public Transaction(int share_id, int shareCount, float pricePerShare, double transactionCharges,
			double sttCharges, int type, String transactedOn, int transaction_id, int user_id) {

		this.share_id = share_id;
		this.shareCount = shareCount;
		this.pricePerShare = pricePerShare;
		this.transactionCharges = transactionCharges;
		this.sttCharges = sttCharges;
		this.type = type;
		this.transactedOn = transactedOn;
		this.transaction_id = transaction_id;
		this.user_id = user_id;
	}
	
	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Transaction Id:\t\t"+transaction_id);
		System.out.println("Share_id:\t\t"+share_id);
		System.out.println("Share_count:\t\t"+shareCount);
		System.out.println("Price Per Share:\t\t"+pricePerShare);
		System.out.println("Transaction Charges:\t\t"+transactionCharges);
		System.out.println("Stt Charges::\t\t"+sttCharges);
		String status =  (type == 1) ? "Buy" : "Sell";
		System.out.println("Transaction Type:\t\t"+status);
		System.out.println("User Id:\t\t"+user_id);
		System.out.println("Transacted On:\t\t"+transactedOn);
	}

}
