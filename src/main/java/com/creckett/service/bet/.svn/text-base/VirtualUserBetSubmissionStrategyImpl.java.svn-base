package com.creckett.service.bet;

import java.awt.font.NumericShaper;
import java.util.Random;

import org.apache.commons.lang.NumberUtils;
import org.springframework.util.StringUtils;

import com.creckett.constant.CreckettConstant;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.dto.BetSubmitRequest;
import com.creckett.dto.OverRuns;
import com.creckett.dto.ScoreUpdate;
import com.creckett.messaging.application.MessagingAdaptor;
import com.creckett.model.Market;
import com.creckett.model.MatchMaster;

public class VirtualUserBetSubmissionStrategyImpl implements
		VirtualUserBetSubmissionStrategy {
	
	private int ballNumberToBet;
	
	private int runsForRemainingBalls;
	
	private MessagingAdaptor messagingAdaptor;
	
	private MatchMasterDAO matchMasterDAO;
	
	public MatchMasterDAO getMatchMasterDAO() {
		return matchMasterDAO;
	}

	public void setMatchMasterDAO(MatchMasterDAO matchMasterDAO) {
		this.matchMasterDAO = matchMasterDAO;
	}

	public MessagingAdaptor getMessagingAdaptor() {
		return messagingAdaptor;
	}

	public void setMessagingAdaptor(MessagingAdaptor messagingAdaptor) {
		this.messagingAdaptor = messagingAdaptor;
	}

	public int getBallNumberToBet() {
		return ballNumberToBet;
	}
	
	public void setBallNumberToBet(int ballNumberToBet) {
		this.ballNumberToBet = ballNumberToBet;
	}


	public int getRunsForRemainingBalls() {
		return runsForRemainingBalls;
	}

	public void setRunsForRemainingBalls(int runsForRemainingBalls) {
		this.runsForRemainingBalls = runsForRemainingBalls;
	}

	@Override
	public void submitBet(ScoreUpdate scoreUpdate, int ballNumber) {
		//Assumption here is that VU will submit a bet at least before last ball
		if (ballNumberToBet != ballNumber){
			return;
		}
		OverRuns overRuns = scoreUpdate.getOverRuns();
		int totalRunsSoFar = getTotalRunsSoFar(overRuns);
		int runBet =new Random().nextInt(runsForRemainingBalls + 1) + totalRunsSoFar;
		MatchMaster matchMaster = matchMasterDAO.get(scoreUpdate.getMatchId());
		for (Market market : matchMaster.getMarkets()){
			BetSubmitRequest betSubmitRequest = createBetSubmitRequest(market.getId(),scoreUpdate,runBet);
			messagingAdaptor.pushBetUpdate(scoreUpdate.getMatchId(),market.getId(),betSubmitRequest);
		}
		
	}

	private BetSubmitRequest createBetSubmitRequest(Long marketId,ScoreUpdate scoreUpdate, int runBet) {
		BetSubmitRequest betSubmitRequest = new BetSubmitRequest();
		betSubmitRequest.setUserId(CreckettConstant.VIRTUAL_USER_ID);
		betSubmitRequest.setUserName("Creckett");
		betSubmitRequest.setBetRuns(runBet);
		betSubmitRequest.setMarketId(marketId);
		betSubmitRequest.setGroupId(scoreUpdate.getOverRuns().getOverNumber() + 1);
		betSubmitRequest.setBetWickets(new Random().nextInt(1) - 1);
		betSubmitRequest.setSession(scoreUpdate.getOverRuns().getSession());
		betSubmitRequest.setOverNumber(scoreUpdate.getOverRuns().getOverNumber() + 1);
		return betSubmitRequest;
		
	}

	private int getTotalRunsSoFar(OverRuns overRuns) {
		int totalRunsSoFar = 0;
		if (org.apache.commons.lang.math.NumberUtils.isNumber(overRuns.getRunsInBall1()) && StringUtils.hasText(overRuns.getRunsInBall1())){
			totalRunsSoFar = totalRunsSoFar + Integer.parseInt(overRuns.getRunsInBall1());
		}
		if(org.apache.commons.lang.math.NumberUtils.isNumber(overRuns.getRunsInBall2()) && StringUtils.hasText(overRuns.getRunsInBall2())){
			totalRunsSoFar = totalRunsSoFar + Integer.parseInt(overRuns.getRunsInBall2());
		}
		if(org.apache.commons.lang.math.NumberUtils.isNumber(overRuns.getRunsInBall3()) && StringUtils.hasText(overRuns.getRunsInBall3())){
			totalRunsSoFar = totalRunsSoFar + Integer.parseInt(overRuns.getRunsInBall3());
		}
		if(org.apache.commons.lang.math.NumberUtils.isNumber(overRuns.getRunsInBall4()) && StringUtils.hasText(overRuns.getRunsInBall4())){
			totalRunsSoFar = totalRunsSoFar + Integer.parseInt(overRuns.getRunsInBall4());
		}
		if(org.apache.commons.lang.math.NumberUtils.isNumber(overRuns.getRunsInBall5()) && StringUtils.hasText(overRuns.getRunsInBall5())){
			totalRunsSoFar = totalRunsSoFar + Integer.parseInt(overRuns.getRunsInBall5());
		}
		return totalRunsSoFar;
	}

}
