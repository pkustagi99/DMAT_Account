package com.amazon.dmataccountmanager.controller;

import java.util.List;

import com.amazon.dmataccountmanager.DMATSession;
import com.amazon.dmataccountmanager.db.ShareDAO;
import com.amazon.dmataccountmanager.db.UserSharesDAO;
import com.amazon.dmataccountmanager.model.Share;
import com.amazon.dmataccountmanager.model.UserShares;

public class ShareService {
	
	private static ShareService service = new ShareService();
	
	UserSharesDAO dao = new UserSharesDAO();
	ShareDAO sharedao = new ShareDAO();
	
	private ShareService(){
		
	}
	
	public static ShareService getInstance() {
		return service;
	}
//	public boolean shareDetail(UserShares userShare) {
//		
//		String sql = "SELECT * FROM usershares WHERE user_id = "+DMATSession.user.user_id+"";
//		List<UserShares> usershares = dao.retrieve(sql);
//		
//		if(usershares.size() > 0) {
//			UserShares u = usershares.get(0);
//			userShare.user_id = u.user_id;
//			userShare.share_id = u.share_id;
//			userShare.shareCount = u.shareCount;
//			userShare.userShare_id = u.userShare_id;
//			userShare.company_name = u.company_name;
//			return true;
//		}
//		return false; 
//	}
	
	public void shareDetail() {
		
		String sql = "SELECT * FROM usershares WHERE user_id = "+DMATSession.user.user_id+"";
		List<UserShares> objects = dao.retrieve(sql);
		for(UserShares object : objects) {
			object.prettyPrint();
			String sqls = "SELECT * from share where share_id = "+object.share_id+"";
			List<Share> shares = sharedao.retrieve(sqls);
			
			Share share = new Share();
			
			if(shares.size() > 0) {
				Share s = shares.get(0);
				share.price = s.price;
				System.out.println("Each Share Price = "+share.price);
			}
		}
	}
}
