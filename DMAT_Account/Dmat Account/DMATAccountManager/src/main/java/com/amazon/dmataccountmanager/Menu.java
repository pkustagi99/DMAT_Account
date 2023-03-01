package com.amazon.dmataccountmanager;

import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import com.amazon.dmataccountmanager.model.User;
//import com.amazon.dmataccountmanager.model.UserShares;
import com.amazon.dmataccountmanager.controller.AuthenticationService;
import com.amazon.dmataccountmanager.controller.ShareService;
import com.amazon.dmataccountmanager.controller.TransactionService;
import com.amazon.dmataccountmanager.db.DB;


public class Menu {
	AuthenticationService auth = AuthenticationService.getInstance();
	ShareService shareService = ShareService.getInstance();
	TransactionService tranService = TransactionService.getInstance();
	boolean quit = false;
	public void showMenu() {

		Scanner scanner = new Scanner(System.in);
		DB db = DB.getInstance();
		
		while(true) {
		System.out.println("0: Quit");
		System.out.println("1: Create a DMAT account");
		System.out.println("2: Login");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		System.out.println("Enter Your Choice: ");
    	int choice = Integer.parseInt(scanner.nextLine());
    	
    	boolean result = false;
    	User user = new User();
    	//UserShares userShare = new UserShares();
    	
    	if (choice == 0) {
    			System.out.println("Thank You For using DMAT Account Manager App");
    			scanner.close();
        		db.closeConnection();
        		scanner.close();
    			break;
    	}
    	 else if (choice == 1){
    		 System.out.println("Enter the Following Details to Create the Account");
    		 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    		 System.out.println("Enter Your User name:");
    		 user.user_name = scanner.nextLine();
    		 System.out.println("Enter Your Account number:");
    		 user.account_number = Integer.parseInt(scanner.nextLine());
    		 System.out.println("Enter Your password:");
    		 user.password = scanner.nextLine();
    		 try {
 				// Hash the Password of User :)
 				MessageDigest digest = MessageDigest.getInstance("SHA-256");
 				byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
 				user.password = Base64.getEncoder().encodeToString(hash);
 			}catch (Exception e) {
 				System.err.println("Something Went Wrong: "+e);
 			}
    		 System.out.println("Enter Your Account Balance:");
    		 user.account_balance = Integer.parseInt(scanner.nextLine());
    		 user.prettyPrint();
    		result = auth.registerUser(user);
    	 	}
    	 else if (choice == 2) {
    		 System.out.println("Enter the following detials to login");
    		 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    		 System.out.println("Enter Your Account Number");
    		 user.account_number = Integer.parseInt(scanner.nextLine());
    		 result = auth.loginUser(user);
    		 if (result) {
    			 System.out.println("Welcome to DMAT Account Manager");
    			 DMATSession.user = user;
    		 while(true) {
    			    System.out.println("~~~~~~~~~~~~~~~~~~~~~~DMAT Account Manager~~~~~~~~~~~~~~~~~~~~~");
    			 	System.out.println("0 - Quit");
    				System.out.println("1 – View account details");
    				System.out.println("2 – Deposit Money");
    				System.out.println("3 – Withdraw Money");
    				System.out.println("4 – Buy shares");
    				System.out.println("5 – Sell shares");
    				System.out.println("6 – View transaction report");
    				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    				
    				System.out.println("Enter Your Choice: ");
    				int mainchoice = Integer.parseInt(scanner.nextLine());
    		    	

    				if (mainchoice == 0) {
    	    			System.out.println("Thank You For using DMAT Account Manager App");
    	    			quit = true;
    	        		db.closeConnection();
    	        		scanner.close();
    	    			break;
    	    	}
    				else if(mainchoice == 1) {
    					System.out.println("Printing Account Details");
    					user.prettyPrint();
    					shareService.shareDetail();
    				}
    				else if(mainchoice == 2) {
    					System.out.println("Enter the amount to be deposited");
    					user.transaction_amount = Integer.parseInt(scanner.nextLine());
    					try {
    					result = auth.depositAmt(user);
    					System.out.println("Your updated balance is "+user.account_balance);
    					}
    					catch (Exception e) {
    						System.err.println("Something Went Wrong: "+e);
    					}
    				}
    				else if(mainchoice == 3) {
    					System.out.println("Enter the amount to withdraw");
    					user.transaction_amount = Integer.parseInt(scanner.nextLine());
    					result = auth.withdrawAmt(user);
    					if(result) {
    						System.out.println("Your updated balance is "+user.account_balance);
    					}
    				}
    				else if(mainchoice == 4) {
    					System.out.println("~~~~~~~~~~~~~~~~~Share Purchase Menu~~~~~~~~~~~~~~~~~~~~~~");
    					tranService.buyShares();
    				}
    				else if(mainchoice == 5) {
    					System.out.println("~~~~~~~~~~~~~~~~~Share Sale Menu~~~~~~~~~~~~~~~~~~~~~~");
    					tranService.sellShares();
    				}
    				else if(mainchoice == 6) {
    					tranService.viewtransaction();
    				}
    	    		 else {
    	        		 System.out.println("Invalid Choice...");
    	        		 break;
    	        	 }
    		 }
    			if(quit = true) {
    				break;
    			}
    	 }
    	 		else {
    				System.out.println("Authentication Failed");
    			}
    	 }
    	}

	}
}
