package com.creckett.service.impl;

import com.creckett.dao.UserMatchVoteDAO;
import com.creckett.model.MatchMaster;
import com.creckett.model.TeamMaster;
import com.creckett.model.UserMatchVote;
import com.creckett.service.UserMatchVoteService;

public class UserMatchVoteServiceImpl implements UserMatchVoteService {

	private UserMatchVoteDAO userMatchVoteDAO;
	
	
	public UserMatchVoteDAO getUserMatchVoteDAO() {
		return userMatchVoteDAO;
	}

	public void setUserMatchVoteDAO(UserMatchVoteDAO userMatchVoteDAO) {
		this.userMatchVoteDAO = userMatchVoteDAO;
	}

	@Override
	public UserMatchVote findByUserAndMatch(long userId, long matchId) {
		return userMatchVoteDAO.findByUserAndMatch(userId, matchId);
	}

	@Override
	public int getGlobalTrendForMatchAndTeam(MatchMaster matchMaster,
			TeamMaster teamMaster) {
		return userMatchVoteDAO.getGlobalTrendForMatchAndTeam(matchMaster, teamMaster);
	}

}
