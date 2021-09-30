package com.creckett.dao;

import java.util.List;

import com.creckett.model.ArchivedAmountLeft;


public interface ArchivedAmountLeftDAO {

	public ArchivedAmountLeft save(ArchivedAmountLeft amountLeft);

	public void saveOrUpdateAll (List<ArchivedAmountLeft> archivedAmountLefts);
	
	public ArchivedAmountLeft get(Long id);

	public void delete(ArchivedAmountLeft amountLeft);
	
	public void update(Long id, Long amount);
	
	public List<ArchivedAmountLeft> getAmountLeftForMarket(Long marketId);
	
}
