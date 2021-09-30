package com.creckett.dao;

import java.util.List;

import com.creckett.model.UserMatchAmountLeft;

public interface UserMatchAmountLeftDAO {
 
	public UserMatchAmountLeft save(UserMatchAmountLeft amountLeft);
	
	public List<UserMatchAmountLeft> getMatchAmountLeft(List<Long> userIds);

}
