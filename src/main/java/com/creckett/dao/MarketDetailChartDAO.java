package com.creckett.dao;

import java.util.List;

import com.creckett.model.AmountLeft;

public interface MarketDetailChartDAO {
	
	public AmountLeft save(AmountLeft amountLeft); 

	public AmountLeft get(Long id);

	public void delete(AmountLeft amountLeft);
	
	public List<AmountLeft> getMarketMatchDetails( Long marketId, Long matchId );
	
	List<AmountLeft> getMarketMatchDetails(Long matchId );
	
}
