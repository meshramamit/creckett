package com.creckett.dao;

import com.creckett.model.MatchMaster;
import com.creckett.model.TeamMaster;
import com.creckett.model.UserMatchVote;

public interface UserMatchVoteDAO {
	public UserMatchVote save(UserMatchVote umv);
	
	public UserMatchVote update(UserMatchVote umv);
	
	public UserMatchVote findByUserAndMatch(long userId, long matchId);

	int getGlobalTrendForMatchAndTeam(MatchMaster matchMaster,
			TeamMaster teamMaster);
}
