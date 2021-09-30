package com.creckett.dao;

import java.util.List;

import com.creckett.model.User;
import com.creckett.model.UserBet;

public interface UserBetDAO
{
	public UserBet save( UserBet userBet );

	public UserBet get( Long id );
	
	public List< UserBet > getAllUserBetsForMarket( Long marketId, Integer sessionId, Integer over );

	public void delete( UserBet userBet );
	
	public boolean isUserBetExist( Long marketId, Long userId, int session, int over );
	
	public List<UserBet> getAllUserBetsForMarket(Long marketId, User user);
	
	public List<UserBet> getAllUserBetsForMarket(Long marketId);

	public List<UserBet> getAllUserBetsForMatch(long matchId, int sessionId,
			int matchOver);

}
