package com.amazon.dmataccountmanager.controller;

import java.util.List;
import java.util.Scanner;
import com.amazon.dmataccountmanager.db.TransactionDAO;
import com.amazon.dmataccountmanager.db.UserDAO;
import com.amazon.dmataccountmanager.Config;
import com.amazon.dmataccountmanager.DMATSession;
import com.amazon.dmataccountmanager.db.ShareDAO;
import com.amazon.dmataccountmanager.db.UserSharesDAO;
import com.amazon.dmataccountmanager.model.Share;
import com.amazon.dmataccountmanager.model.UserShares;
import com.amazon.dmataccountmanager.model.Transaction;
import com.amazon.dmataccountmanager.model.User;


public class TransactionService {
	private static TransactionService transervice = new TransactionService();
	ShareService shareService = ShareService.getInstance();
	
	
	Scanner scanner = new Scanner(System.in);
	TransactionDAO dao = new TransactionDAO();
	ShareDAO sharedao = new ShareDAO();
	UserSharesDAO userSharesdao = new UserSharesDAO();
	UserDAO userDAO = new UserDAO();
	
	Transaction tran = new Transaction();
	User user = new User();
	UserShares uShare = new UserShares();
	
	private TransactionService(){
		
	}
	
	public static TransactionService getInstance() {
		return transervice;
	}
	
	// Buy transaction
	public void buyShares() {
		System.out.println("Enter 0 to view all shares available or Share_id to be purchased");
		int id = scanner.nextInt();
		List <Share> objects = null;
		if(id == 0) {
			objects = sharedao.retrieve();
			for (Share object : objects) {
				object.prettyPrint();
			}
			System.out.println("Enter Share_id to be purchased");
			id = scanner.nextInt();
		}else {
			String sql = "Select * from share where share_id ="+id;
			objects = sharedao.retrieve(sql);
			for (Share object : objects) {
				object.prettyPrint();
			}
		}
		System.out.println("Enter The Quantity to be purchased");
		int qty = scanner.nextInt();
		Share share = null;
		for (Share object : objects) {
			if(object.share_id == id) {
				share = object;
			}
		}
		if(id != 0 && share != null && qty !=0) {
			double total = share.price*qty;
			double SttCharges = (total*Config.securities_Transfer_Tax)/100;
			double tranCharge = (total*Config.transaction_Charge)/100;
			if(tranCharge<100) {
				tranCharge = 100;
			}
			System.out.println("Share Value = "+total);
			double tamt = total+SttCharges+tranCharge;
			System.out.println("Stt Charges = "+SttCharges);
			System.out.println("Transaction Charges ="+tranCharge);
			System.out.println("The total amount of the transaction is : "+tamt+"\n Enter 1 to proceed or 0 to cancel");
			int confirm = scanner.nextInt();
			if(confirm == 1) {
				user = DMATSession.user;
				user.prettyPrint();
				//deduct money from account 
				double deductamt = tamt;
				if(deductamt < user.account_balance) {
					user.account_balance-=deductamt;
					int result = userDAO.update(user);
					String message = (result > 0) ? "Money Deducted from your Account" : "Purchase Failed. Try Again..";
					System.out.println(message);
					if(result>0) {
						int check = 0;
						//boolean controls = false;
						String sql = "SELECT * FROM usershares WHERE user_id = "+DMATSession.user.user_id+"";
						List<UserShares> userShares = userSharesdao.retrieve(sql);
						for (UserShares userShare : userShares) {
							if(userShare.share_id == id) {
								//update the entry to userShares
								userShare.shareCount+=qty;
								 result = userSharesdao.update(userShare);
								 message = (result > 0) ? "Shares updated into your Account" : "Shares Purchase Failed. Try Again.."; 
								System.out.println(message);
								check++;
							}
						}
						//insert entry into userShares
						if(check == 0) {
							uShare.company_name = share.company_name;
							uShare.shareCount = qty;
							uShare.share_id = id;
							uShare.user_id = DMATSession.user.user_id;
							result = userSharesdao.insert(uShare);
							 message = (result > 0) ? "Shares added into your Account" : "Shares Purchase Failed. Try Again.."; 
							System.out.println(message);
						}
						//create a new entry in transaction table.
						tran.share_id = id;
						tran.pricePerShare = share.price;
						tran.shareCount = qty;
						tran.sttCharges = Config.securities_Transfer_Tax;
						tran.transactionCharges = Config.transaction_Charge;
						tran.type = 1;
						tran.user_id = DMATSession.user.user_id;
						 result = dao.insert(tran);
						 message = (result > 0) ? "Transaction completed Sucessfully" : "Transaction Failed. Try Again.."; 
						System.out.println(message);
					}
				}else {
					System.out.println("Low Balance try again....");
				}
			}
		}
	}
	
	// Sell transaction
	public void sellShares() {
		System.out.println("Enter 0 to view all shares available or Share_id to be sold");
		int id = scanner.nextInt();
		List<UserShares> objects = null;
		if(id == 0) {
			String sql = "Select * from usershares where user_id ="+DMATSession.user.user_id;
			objects = userSharesdao.retrieve(sql);
			for (UserShares object : objects) {
				object.prettyPrint();
			}
			System.out.println("Enter Share_id to be Sold");
			id = scanner.nextInt();
		}else {
			String sql = "Select * from usershares where share_id ="+id;
			objects = userSharesdao.retrieve(sql);
			for (UserShares object : objects) {
				object.prettyPrint();
			}
		}
		System.out.println("Enter The Quantity to be sold");
		int qty = scanner.nextInt();
		UserShares ushare = null;
		for (UserShares object : objects) {
			if(object.share_id == id) {
				ushare = object;
			}
		}
		if(id != 0 && ushare != null && qty !=0 && ushare.shareCount>=qty ) {
			Share currentshare = null;
			String sql = "Select * from share where share_id ="+id;
			List<Share> shares = sharedao.retrieve(sql);
			for (Share share : shares) {
				currentshare = share;
			}
			double total = currentshare.price*qty;
			double SttCharges = total*Config.securities_Transfer_Tax;
			double tranCharge = total*Config.transaction_Charge;
			if(tranCharge<100) {
				tranCharge = 100;
			}
			System.out.println("Share Value = "+total);
			double tamt = total-SttCharges-tranCharge;
			System.out.println("Stt Charges = "+SttCharges);
			System.out.println("Transaction Charges ="+tranCharge);
			System.out.println("The total amount of the transaction is : "+tamt+"\n Enter 1 to proceed or 0 to cancel");
			int confirm = scanner.nextInt();
			if(confirm == 1) {
				user = DMATSession.user;
				user.prettyPrint();
				//add money to account 
				double deductamt = tamt;
					user.account_balance+=deductamt;
					int result = userDAO.update(user);
					String message = (result > 0) ? "Money Added from your Account" : "Shares sale Failed Account problem. Try Again.."; 
					System.out.println(message);
					if(result>0) {
						int check = 0;
						//boolean controls = false;
						sql = "SELECT * FROM usershares WHERE user_id = "+DMATSession.user.user_id+"";
						List<UserShares> userShares = userSharesdao.retrieve(sql);
						for (UserShares userShare : userShares) {
							if(userShare.share_id == id) {
								//update the entry to userShares
								userShare.shareCount-=qty;
								 result = userSharesdao.update(userShare);
								 message = (result > 0) ? "Shares updated into your Account" : "Shares sale Failed. Try Again.."; 
								System.out.println(message);
								check++;
							}
						}
						//insert entry into userShares
						if(check == 0) {
							uShare.company_name = currentshare.company_name;
							uShare.shareCount = qty;
							uShare.share_id = id;
							uShare.user_id = DMATSession.user.user_id;
							result = userSharesdao.insert(uShare);
							 message = (result > 0) ? "Shares deducted from your Account" : "Shares sale Failed. Try Again.."; 
							System.out.println(message);
						}
						//create a new entry in transaction table.
						tran.share_id = id;
						tran.pricePerShare = currentshare.price;
						tran.shareCount = qty;
						tran.sttCharges = Config.securities_Transfer_Tax;
						tran.transactionCharges = Config.transaction_Charge;
						tran.type = 2;
						tran.user_id = DMATSession.user.user_id;
						 result = dao.insert(tran);
						 message = (result > 0) ? "Transaction completed Sucessfully" : "Transaction Failed. Try Again.."; 
						System.out.println(message);
					}
			}
		}else {
			System.out.println("Insufficient Shares try again....");
		}	
	}

	//View Transaction
	public void viewtransaction() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~Transaction View Panel~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~Transaction Menu~~~~~~~~~");
		System.out.println("1: Transactions between Date Range");
		System.out.println("2: Transactions on a Share");
		int choice = Integer.parseInt(scanner.nextLine());
		List<Transaction> objects = null;
		String sql;
		if(choice ==1) {
			System.out.println("Enter the Date in YYYY-MM-DD Format Ex- 2023-02-28");
			System.out.println("Enter the From Date : ");
			String from = scanner.nextLine();
			System.out.println("Enter the To Date : ");
			String to = scanner.nextLine();
			//Select between date range
			sql = "SELECT * from transaction where user_id = "+DMATSession.user.user_id+" and transactedOn between '"+from+"' and '"+to+"'";
			objects = dao.retrieve(sql);
			for (Transaction object:objects) {
				object.prettyPrint();
			}
		}
		else if(choice ==2) {
			System.out.println("Select \n1: To search by share id\n 2 To search by Company Name");
			choice = Integer.parseInt(scanner.nextLine());
			if(choice == 1) {
				//Select using Share id
				System.out.println("Enter Share Id");
				int shareid = Integer.parseInt(scanner.nextLine());
				sql = "SELECT * from transaction where user_id = "+DMATSession.user.user_id+" and share_id = "+shareid;
				objects = dao.retrieve(sql);
				for (Transaction object:objects) {
					object.prettyPrint();
				}
			}else if (choice == 2) {
				//Select using Company name
				System.out.println("Enter Company Name");
				String companyname = scanner.nextLine();
				List<Share> shares = null;
				sql = "Select * from share where company_name = '"+companyname+"'";
				shares = sharedao.retrieve(sql);
				choice = shares.get(0).share_id;
				sql = "SELECT * from transaction where user_id = "+DMATSession.user.user_id+" and share_id = "+choice;
				objects = dao.retrieve(sql);
				for (Transaction object:objects) {
					object.prettyPrint();
				}
			}
		}else {
			System.out.println("Invalid Choice");
		}
	}
}
