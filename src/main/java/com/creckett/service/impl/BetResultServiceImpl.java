package com.creckett.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.creckett.dto.BetResultResponse;
import com.creckett.dto.UserSessionBets;
import com.creckett.logger.CreckettLogger;
import com.creckett.messaging.application.MessagingAdaptor;
import com.creckett.model.AmountLeft;
import com.creckett.model.BetState;
import com.creckett.model.MatchScore;
import com.creckett.model.UserBet;
import com.creckett.service.BetResultService;
import com.creckett.service.MarketDetailChartService;
import com.creckett.service.MatchService;

/**
 * 
 * @author vkarmani
 * 
 */
public class BetResultServiceImpl implements BetResultService {
	private MatchService matchService;

	private MarketDetailChartService marketDetailChartService;

	private MessagingAdaptor messagingAdaptor;
	
	private CreckettLogger creckettLogger = CreckettLogger.getInstance();

	public MessagingAdaptor getMessagingAdaptor() {
		return messagingAdaptor;
	}

	public void setMessagingAdaptor(MessagingAdaptor messagingAdaptor) {
		this.messagingAdaptor = messagingAdaptor;
	}

	public MatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	public MarketDetailChartService getMarketDetailChartService() {
		return marketDetailChartService;
	}

	public void setMarketDetailChartService(
			MarketDetailChartService marketDetailChartService) {
		this.marketDetailChartService = marketDetailChartService;
	}

	@Override
	public BetResultResponse getBetResult(Long matchId, Long marketId,
			boolean isHistoryRequired) {
		MatchScore latestScore = getLatestScore(matchId);
		BetResultResponse betResultResponse = getBetResult(matchId, marketId, latestScore, isHistoryRequired);
		betResultResponse.setAmountLeft(marketDetailChartService
				.getMarketPlayersAmountDetails(marketId, matchId));
		return betResultResponse;
	}

	private BetResultResponse getBetResult(Long matchId, Long marketId,
			MatchScore latestScore, boolean isHistoryRequired) {
		List<BetState> betStates = new ArrayList<BetState>();
		// if history is required then get bets for all completed overs else
		// only last completed over
		if (isHistoryRequired) {
			betStates = matchService.getBetStatus(marketId);
			/*matchService.calculateBetStatesWithOutBetAmount(marketId,betStates);*/
		} else {
			betStates = matchService.getBetStatus(marketId,
					latestScore.getMatchOver(), latestScore.getSessionId());
			/*matchService.calculateBetStatesWithoutBetAmountForLatestOver(marketId,latestScore,betStates);*/
		}
		// parsing datastucture in simple form to support ftls
		Collection<UserSessionBets> sessionWiseBets = matchService
				.getSessionWiseMarketBets(betStates).values();

		BetResultResponse betResultResponse = new BetResultResponse();
		betResultResponse.setSessionWiseBets(sessionWiseBets);
		betResultResponse.setMatchId(matchId);
		betResultResponse.setMarketId(marketId);
		return betResultResponse;
	}
	
	private List<BetResultResponse> getBetResultForAllMarkets(Long matchId, 
			MatchScore latestScore) {
		List<BetResultResponse> betResultResponses = new ArrayList<BetResultResponse>();
		Map<Long,List<BetState>> betStatesByMarket = matchService.getBetStatusByMarket(matchId,
					latestScore.getMatchOver(), latestScore.getSessionId());
			
		Map<Long, List<AmountLeft>> amountLeftByMarket = marketDetailChartService.getMarketPlayersAmountDetails(matchId);
		Map<Long, List<UserBet>> userBetsByMarket = matchService.getUserBetsByMarket(matchId, latestScore.getSessionId(), latestScore.getMatchOver());
		for (long marketId : betStatesByMarket.keySet()){
			List<BetState> betStates = betStatesByMarket.get(marketId);
			matchService.calculateBetStatesWithoutBetAmountForLatestOver(marketId,latestScore,betStates, userBetsByMarket.get(marketId),amountLeftByMarket.get(marketId));
			// parsing datastucture in simple form to support ftls
			Collection<UserSessionBets> sessionWiseBets = matchService
					.getSessionWiseMarketBets(betStates,latestScore).values();
	
			BetResultResponse betResultResponse = new BetResultResponse();
			betResultResponse.setSessionWiseBets(sessionWiseBets);
			betResultResponse.setMatchId(matchId);
			betResultResponse.setMarketId(marketId);
			betResultResponse.setAmountLeft(amountLeftByMarket.get(marketId));
			betResultResponses.add(betResultResponse);
		}
		return betResultResponses;
	}

	private MatchScore getLatestScore(Long matchId) {
		MatchScore score = matchService
				.getLatestMatchScore(matchId);
		return score;
	}

	/**
	 * Over completion event. Calculate the bet result for all the markets and
	 * push the result to users playing in those markets.
	 */
	@Override
	public List<BetResultResponse> onOverCompleted(long matchId) {
		System.out.println("bet result invoked");
		// Get the latest score against which bet results will be calculated
		MatchScore latestScore = getLatestScore(matchId);
		
		return getBetResultForAllMarkets(matchId, latestScore);
		
	}
	
	@Override
	public void pushBetResults(List<BetResultResponse> betResultResponses){
		for (BetResultResponse betResultResponse : betResultResponses) {
			// Push the bet result response through abstract messagingAdaptor
			messagingAdaptor.pushBetResult(betResultResponse);
		}
	}

}
