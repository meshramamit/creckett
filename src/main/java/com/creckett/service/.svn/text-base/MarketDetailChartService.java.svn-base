package com.creckett.service;

import java.util.List;
import java.util.Map;

import com.creckett.dto.MarketMatchSessionDetail;
import com.creckett.model.AmountLeft;

public interface MarketDetailChartService {

	public List<AmountLeft> getMarketMatchDetails( Long marketId, Long matchId );
	
	public Map<Integer, MarketMatchSessionDetail> getSessionWiseMarketMatchDetail(
			List<AmountLeft> amountList);

	List<AmountLeft> getMarketPlayersAmountDetails(Long marketId, Long matchId);
	
	Map<Long, List<AmountLeft>> getMarketPlayersAmountDetails(Long matchId);
	
}
