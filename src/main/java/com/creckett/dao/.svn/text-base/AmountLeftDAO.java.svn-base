package com.creckett.dao;

import java.util.List;

import com.creckett.model.AmountLeft;
import com.creckett.model.Market;

public interface AmountLeftDAO
{

	public AmountLeft save(AmountLeft amountLeft);
	
	public void saveOrUpdateAll(List<AmountLeft> amountLefts);

	public AmountLeft get(Long id);

	public void delete(AmountLeft amountLeft);
	
	public void update(Long id, Long amount);
	
	public int deleteUserAmountForMarket( Market market, Long userId );
	
	public AmountLeft getAmountByMarketMatchMaximumSessionAndMaximumOver(Market market, Long userId);
	
	public List<AmountLeft> getAmountLeftForMarket( Long marketId );

	int deleteForMarket(Market market);
	
}
