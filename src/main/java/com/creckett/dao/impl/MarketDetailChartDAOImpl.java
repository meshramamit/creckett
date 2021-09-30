package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.creckett.dao.MarketDetailChartDAO;
import com.creckett.model.AmountLeft;

public class MarketDetailChartDAOImpl extends BaseHibernateDAO<AmountLeft>
		implements MarketDetailChartDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<AmountLeft> getMarketMatchDetails(Long marketId, Long matchId) {
		Query query = this.getSession().getNamedQuery("getAmountLeftByOver");
		query.setParameter("currentMarketId", marketId);
		query.setParameter("currentMatchId", matchId);
		List<AmountLeft> amountLeftForMarketUsers = query.list();
		return amountLeftForMarketUsers;
	}

	@Override
	public AmountLeft get(Long id) {
		return super.get(AmountLeft.class, id);
	}

	@Override
	public List<AmountLeft> getMarketMatchDetails(Long matchId) {
		Query query = this.getSession().getNamedQuery("getAmountLeftByMatch");
		query.setParameter("currentMatchId", matchId);
		List<AmountLeft> amountLeftForMatchUsers = query.list();
		return amountLeftForMatchUsers;
	}
}
