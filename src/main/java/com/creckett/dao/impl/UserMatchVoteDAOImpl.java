package com.creckett.dao.impl;

import org.hibernate.Query;

import com.creckett.dao.UserMatchVoteDAO;
import com.creckett.model.MatchMaster;
import com.creckett.model.TeamMaster;
import com.creckett.model.UserMatchVote;

public class UserMatchVoteDAOImpl extends BaseHibernateDAO<UserMatchVote> implements
		UserMatchVoteDAO {

	@Override
	public UserMatchVote findByUserAndMatch(long userId, long matchId) {
		Query query = this.getSession().createQuery(" from UserMatchVote where " +
				"user.id = :userId and matchMaster.id = :matchId " );
		query.setParameter("userId", userId);
		query.setParameter("matchId", matchId);
		return (UserMatchVote) query.uniqueResult();
	}
	
	@Override
	public int getGlobalTrendForMatchAndTeam(MatchMaster matchMaster,
			TeamMaster teamMaster) {
		Query query = this.getSession().createQuery(" select count(*) from UserMatchVote where " +
		"matchMaster = :matchMaster and userVote = :teamMaster " );
		query.setParameter("matchMaster", matchMaster);
		query.setParameter("teamMaster", teamMaster);
		return ((Long) query.uniqueResult()).intValue();
	}

}
