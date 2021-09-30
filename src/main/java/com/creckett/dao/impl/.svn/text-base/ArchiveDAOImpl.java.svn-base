package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.creckett.dao.ArchiveDAO;
import com.creckett.model.BetState;
import com.creckett.model.Market;
import com.creckett.model.MatchStatus;
import com.creckett.model.User;

public class ArchiveDAOImpl extends HibernateDaoSupport implements ArchiveDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Market> getArchivedMatchListForUser(User user) {
		Query query = this.getSession().createQuery(" from Market market where market.match.status = :status " +
						" and exists ( select 1 from market.marketUsers user where user.userId.id = :userId ) ");
		query.setParameter("status", MatchStatus.FINISHED);
		query.setParameter("userId", user.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BetState> getArchivedBetsForMarket(Long marketId) {
		Query query = this.getSession().createQuery(" from BetState bets where bets.market.id = :marketId ");
		query.setParameter("marketId", marketId);
		return query.list();
	}

}
