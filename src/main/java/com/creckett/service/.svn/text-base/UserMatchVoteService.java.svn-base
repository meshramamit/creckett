package com.creckett.service;

import com.creckett.model.MatchMaster;
import com.creckett.model.TeamMaster;
import com.creckett.model.UserMatchVote;

public interface UserMatchVoteService {
	public UserMatchVote findByUserAndMatch(long userId, long matchId);

	int getGlobalTrendForMatchAndTeam(MatchMaster matchMaster,
			TeamMaster teamMaster);
}
