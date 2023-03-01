package com.amazon.dmataccountmanager.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.dmataccountmanager.model.Share;

public class ShareDAO implements DAO<Share> {
	DB db = DB.getInstance();
	public int insert(Share object) {
		String sql = "INSERT INTO share (company_name, price,) VALUES ('"+object.company_name+"', "+object.price+")";
		return db.executeSQL(sql);
	}

	public int update(Share object) {
		String sql = "UPDATE share set company_name = '"+object.company_name+"', price="+object.price+"WHERE share_id = "+object.share_id;
		return db.executeSQL(sql);
	}

	public int delete(Share object) {
		String sql = "DELETE FROM share WHERE share_id = "+object.share_id+"";
		return db.executeSQL(sql);
	}

	public List<Share> retrieve() {
		String sql = "SELECT * from share";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Share> shares = new ArrayList<Share>();
		
		try {
			while(set.next()) {
				
				Share share = new Share();
				
				// Read the row from ResultSet and put the data into User Object
				share.share_id = set.getInt("share_id");
				share.company_name = set.getString("company_name");
				share.lastUpdatedOn = set.getString("lastUpdatedOn");
				share.price = set.getInt("price");
				
				shares.add(share);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return shares;
	}

	public List<Share> retrieve(String sql) {
ResultSet set = db.executeQuery(sql);
		
		ArrayList<Share> shares = new ArrayList<Share>();
		
		try {
			while(set.next()) {
				
				Share share = new Share();
				
				// Read the row from ResultSet and put the data into User Object
				share.share_id = set.getInt("share_id");
				share.company_name = set.getString("company_name");
				share.lastUpdatedOn = set.getString("lastUpdatedOn");
				share.price = set.getInt("price");
				
				shares.add(share);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}

		
		return shares;
	}

}
