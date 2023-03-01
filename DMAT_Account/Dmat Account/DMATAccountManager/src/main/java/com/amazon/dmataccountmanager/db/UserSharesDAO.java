package com.amazon.dmataccountmanager.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.dmataccountmanager.model.UserShares;

public class UserSharesDAO implements DAO<UserShares>{
	
	DB db = DB.getInstance();

	public int insert(UserShares object) {
		String sql = "INSERT INTO usershares (shareCount, share_id, user_id, company_name) VALUES ("+object.shareCount+", "+object.share_id+", "+object.user_id+", '"+object.company_name+"')";
		return db.executeSQL(sql);
	}

	public int update(UserShares object) {
		String sql = "UPDATE usershares set shareCount = "+object.shareCount+", share_id="+object.share_id+", user_id="+object.user_id+", company_name='"+object.company_name+"' WHERE userShare_id = "+object.userShare_id;
		return db.executeSQL(sql);
	}

	public int delete(UserShares object) {
		String sql = "DELETE FROM usershares WHERE userShare_id = "+object.userShare_id+"";
		return db.executeSQL(sql);
	}

	public List<UserShares> retrieve() {
		
		String sql = "SELECT * from usershares";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<UserShares> usershares = new ArrayList<UserShares>();
		
		try {
			while(set.next()) {
				
				UserShares usershare = new UserShares();
				
				// Read the row from ResultSet and put the data into User Object
				usershare.userShare_id = set.getInt("userShare_id");
				usershare.shareCount = set.getInt("shareCount");
				usershare.share_id = set.getInt("share_id");
				usershare.user_id = set.getInt("user_id");
				usershare.company_name = set.getString("company_name");
				
				usershares.add(usershare);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		return usershares;
	}

	
	public List<UserShares> retrieve(String sql) {
ResultSet set = db.executeQuery(sql);
		
		ArrayList<UserShares> usershares = new ArrayList<UserShares>();
		
		try {
			while(set.next()) {
				
				UserShares usershare = new UserShares();
				
				// Read the row from ResultSet and put the data into User Object
				usershare.userShare_id = set.getInt("userShare_id");
				usershare.shareCount = set.getInt("shareCount");
				usershare.share_id = set.getInt("share_id");
				usershare.user_id = set.getInt("user_id");
				usershare.company_name = set.getString("company_name");
				
				usershares.add(usershare);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		return usershares;
	}
	
	

}
