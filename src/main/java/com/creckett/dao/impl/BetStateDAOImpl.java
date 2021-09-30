package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.creckett.dao.BetStateDAO;
import com.creckett.model.BetState;

@SuppressWarnings("unchecked")
public class BetStateDAOImpl extends BaseHibernateDAO<BetState> implements
		BetStateDAO {

	@Override
	public BetState get(Long id) {
		return this.get(BetState.class, id);
	}

	public List<BetState> retrieveBetStatusForOvers(Long marketId,
			Integer matchOver, Integer session) {
		Query query = this.getSession().getNamedQuery(
				"getUserBetForMarketMatchOver");
		query.setParameter("over", matchOver.intValue());
		query.setParameter("session", session.intValue());
		query.setParameter("marketId", marketId);
		return query.list();
	}

	@Override
	public List<BetState> retrieveBetStatusForOvers(Long marketId) {
		Query query = this.getSession().getNamedQuery("getUserBetForMarket");
		query.setParameter("marketId", marketId);
		return query.list();
	}

	@Override
	public BetState retrieveBetStatusForUserMarketSessionOver(Integer over,
			Integer session, Long userId, Long marketId) {
		Query query = this.getSession().getNamedQuery(
				"getUserBetForMarketUserMatchOver");
		// .createQuery("from BetState where matchOver = :over and session = :session and "
		// +
		// "user.id = :userId and market.id = :marketId");
		query.setParameter("over", over);
		query.setParameter("session", session);
		query.setParameter("userId", userId);
		query.setParameter("marketId", marketId);
		query.setMaxResults(1);
		return (BetState) query.uniqueResult();
	}

	@Override
	public List<BetState> retrieveBetStatesForMatch(Long matchId,
			Integer matchOver, Integer sessionId) {
		Query query = this.getSession().getNamedQuery(
		"getUserBetForMatchOver");
		query.setParameter("over", matchOver);
		query.setParameter("session", sessionId);
		query.setParameter("matchId", matchId);
		return query.list();
	}
}
