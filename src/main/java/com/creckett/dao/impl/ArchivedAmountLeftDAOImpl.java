package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.creckett.dao.ArchivedAmountLeftDAO;
import com.creckett.model.ArchivedAmountLeft;

public class ArchivedAmountLeftDAOImpl extends BaseHibernateDAO<ArchivedAmountLeft> implements
		ArchivedAmountLeftDAO {

	@Override
	public void delete(ArchivedAmountLeft amountLeft) {
		super.delete(amountLeft);
	}

	@Override
	public ArchivedAmountLeft get(Long id) {
		return super.get(ArchivedAmountLeft.class, id);
	}

	@Override
	public ArchivedAmountLeft save(ArchivedAmountLeft amountLeft) {
		return super.save(amountLeft);
	}

	@Override
	public void update(Long id, Long amount) {
		ArchivedAmountLeft amountLeft = this.get(id);
		amountLeft.setLeftAmount(amount.intValue());
		this.save(amountLeft);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ArchivedAmountLeft> getAmountLeftForMarket(Long marketId) {
		Query query = this.getSession().createQuery("from ArchivedAmountLeft amount where amount.marketId.id = :marketId and amount.over!=:overId");
		query.setParameter("marketId", marketId);
		query.setParameter("overId", Integer.valueOf(0));
		return query.list();
	}

}
