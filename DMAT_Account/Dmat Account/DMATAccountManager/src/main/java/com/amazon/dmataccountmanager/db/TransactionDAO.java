package com.amazon.dmataccountmanager.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.dmataccountmanager.model.Transaction;

public class TransactionDAO implements DAO<Transaction>{
	DB db = DB.getInstance();

	public int insert(Transaction object) {
		String sql = "INSERT INTO transaction (shareCount, pricePerShare, transactionCharges, sttCharges, type, share_id, user_id) VALUES ("+object.shareCount+", "+object.pricePerShare+", "+object.transactionCharges+", "+object.sttCharges+", "+object.type+", "+object.share_id+", "+object.user_id+")";
		return db.executeSQL(sql);
	}

	public int update(Transaction object) {
		return 0;
		
	}

	public int delete(Transaction object) {
		String sql = "DELETE FROM transaction WHERE transaction_id = "+object.transaction_id+"";
		return db.executeSQL(sql);
	}

	public List<Transaction> retrieve() {
		String sql = "SELECT * from transaction";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			while(set.next()) {
				
				Transaction transaction = new Transaction();
				
				// Read the row from ResultSet and put the data into User Object
				transaction.shareCount = set.getInt("shareCount");
				transaction.pricePerShare = set.getInt("pricePerShare");
				transaction.transactionCharges = set.getDouble("transactionCharges");
				transaction.transactedOn = set.getString("transactedOn");
				transaction.sttCharges = set.getDouble("sttCharges");
				transaction.type = set.getInt("type");
				transaction.share_id = set.getInt("share_id");
				transaction.transaction_id = set.getInt("transaction_id");
				transaction.user_id = set.getInt("user_id");
				transactions.add(transaction);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return transactions;
	}

	public List<Transaction> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			while(set.next()) {
				
				Transaction transaction = new Transaction();
				
				// Read the row from ResultSet and put the data into User Object
				transaction.shareCount = set.getInt("shareCount");
				transaction.pricePerShare = set.getInt("pricePerShare");
				transaction.transactionCharges = set.getDouble("transactionCharges");
				transaction.transactedOn = set.getString("transactedOn");
				transaction.sttCharges = set.getDouble("sttCharges");
				transaction.type = set.getInt("type");
				transaction.share_id = set.getInt("share_id");
				transaction.transaction_id = set.getInt("transaction_id");
				transaction.user_id = set.getInt("user_id");
				transactions.add(transaction);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return transactions;
	}

}
