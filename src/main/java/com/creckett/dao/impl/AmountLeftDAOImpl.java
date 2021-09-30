package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.creckett.dao.AmountLeftDAO;
import com.creckett.model.AmountLeft;
import com.creckett.model.Market;

public class AmountLeftDAOImpl extends BaseHibernateDAO<AmountLeft> implements
		AmountLeftDAO {

	@Override
	public AmountLeft get(Long id) {
		return get(AmountLeft.class, id);
	}

	@Override
	public void update(Long id, Long amount) {
		AmountLeft amountLeft = this.get(id);
		amountLeft.setLeftAmount(amount.intValue());
		this.save(amountLeft);
	}

	@Override
	public AmountLeft getAmountByMarketMatchMaximumSessionAndMaximumOver(
			Market market, Long userId) {

		Query query = this
				.getSession().getNamedQuery("getLatestAmountLeftForUser");
				//.createQuery(
				//		"from AmountLeft amount where amount.userId.id = :userId and amount.marketId.id = :marketId and amount.matchId.id = :matchId " +
				//				"group by  amount.marketId.id, amount.matchId.id, amount.sessionId, amount.over " + 
				//				"having amount.sessionId = max(amount.sessionId) and amount.over = max(amount.over) ");
		query.setParameter("userId", userId);
		query.setParameter("marketId", market.getId());
		query.setParameter("matchId", market.getMatch().getId());
		query.setMaxResults(1);
		return (AmountLeft) query.uniqueResult();
	}

	@Override
	public int deleteUserAmountForMarket(Market market, Long userId) {
		Query query = this.getSession().createQuery("delete from AmountLeft where userId.id = :userId and marketId.id=:marketId");
		query.setParameter("userId", userId);
		query.setParameter("marketId", market.getId());
		return query.executeUpdate();
	}
	
	@Override
	public int deleteForMarket(Market market){
		Query query = this.getSession().createQuery("delete from AmountLeft where marketId = :market");
		query.setEntity("market", market);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AmountLeft> getAmountLeftForMarket(Long marketId) {
		Query query = this.getSession().createQuery("from AmountLeft amount where amount.marketId.id = :marketId");
		query.setParameter("marketId", marketId);
		return query.list();
	}
}
