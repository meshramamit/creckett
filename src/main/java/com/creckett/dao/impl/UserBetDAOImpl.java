package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.creckett.dao.UserBetDAO;
import com.creckett.model.User;
import com.creckett.model.UserBet;

@SuppressWarnings("unchecked")
public class UserBetDAOImpl extends BaseHibernateDAO<UserBet> implements
		UserBetDAO {

	@Override
	public UserBet get(Long id) {
		return this.get(UserBet.class, id);
	}

	@Override
	public List<UserBet> getAllUserBetsForMarket(Long marketId,
			Integer sessionId, Integer over) {
		Query query = this
				.getSession()
				.createQuery(
						" from UserBet bet where bet.market.id = :marketId and "
								+ "bet.matchSession = :sessionId and bet.matchOver = :over");
		query.setParameter("marketId", marketId);
		query.setParameter("sessionId", sessionId);
		query.setParameter("over", over);
		return query.list();
	}

	@Override
	public List<UserBet> getAllUserBetsForMarket(Long marketId) {
		Query query = this.getSession().createQuery(
				" from UserBet bet where bet.market.id = :marketId");
		query.setParameter("marketId", marketId);
		return query.list();
	}

	@Override
	public List<UserBet> getAllUserBetsForMarket(Long marketId, User user) {
		Query query = this
				.getSession()
				.createQuery(
						" from UserBet bet where bet.market.id = :marketId and bet.user.id = :userId ");
		query.setParameter("marketId", marketId);
		query.setParameter("userId", user.getId());
		return query.list();
	}

	@Override
	public boolean isUserBetExist(Long marketId, Long userId, int session,
			int over) {

		Query query = this.getSession().getNamedQuery("getUserBetStatus");
		// .createQuery(
		// "select 1 from UserBet bet where bet.market.id = :marketId and bet.user.id = :userId and "
		// + "bet.matchSession = :sessionId and bet.matchOver = :overId");
		query.setParameter("marketId", marketId);
		query.setParameter("userId", userId);
		query.setParameter("sessionId", session);
		query.setParameter("overId", over);
		return (query.uniqueResult() != null);
	}

	@Override
	public List<UserBet> getAllUserBetsForMatch(long matchId, int sessionId,
			int matchOver) {
		Query query = this
				.getSession()
				.createQuery(
						" from UserBet bet where "
								+ "bet.matchSession = :sessionId and bet.matchOver = :over and bet.market.match.id = :matchId");
		query.setParameter("matchId", matchId);
		query.setParameter("sessionId", sessionId);
		query.setParameter("over", matchOver);
		return query.list();
	}

}
