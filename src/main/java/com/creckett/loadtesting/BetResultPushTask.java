package com.creckett.loadtesting;

import java.util.List;
import java.util.Random;

import com.creckett.constant.CreckettConstant;
import com.creckett.dto.BetSubmitRequest;
import com.creckett.messaging.application.MessagingAdaptor;
import com.creckett.model.Market;
import com.creckett.service.MatchService;
import com.creckett.service.impl.AsyncBetResultHelper;

public class BetResultPushTask{

	private MatchService matchService;
	private AsyncBetResultHelper asyncBetResultHelper;
	
	private MessagingAdaptor messagingAdaptor;
	
	int over=0;
	
	
	public MessagingAdaptor getMessagingAdaptor() {
		return messagingAdaptor;
	}

	public void setMessagingAdaptor(MessagingAdaptor messagingAdaptor) {
		this.messagingAdaptor = messagingAdaptor;
	}

	public BetResultPushTask(){
		
	}

	public AsyncBetResultHelper getAsyncBetResultHelper() {
		return asyncBetResultHelper;
	}


	public void setAsyncBetResultHelper(AsyncBetResultHelper asyncBetResultHelper) {
		this.asyncBetResultHelper = asyncBetResultHelper;
	}


	public MatchService getMatchService() {
		return matchService;
	}


	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}


	public void push() {
		over++;
		submitBetFromVirtualUser(1);
		System.out.println("bet resuls invoked");
		int runs = new Random().nextInt(16);
		int wickets = new Random().nextInt(2);
		String score = "" + runs + "/" + wickets;
		matchService.updateScore(String.valueOf(1), String.valueOf(1), String.valueOf(over), String.valueOf(runs), String.valueOf(wickets), score);
		asyncBetResultHelper.onOverCompleted(1);

	}

	private void submitBetFromVirtualUser(int matchId) {
		List<Market> markets = matchService.getMarketsByMatch(matchId);
		for (Market market : markets){
			BetSubmitRequest betSubmitRequest = createBetSubmitRequest(market);
			messagingAdaptor.pushBetUpdate(1, market.getId(), betSubmitRequest);
		}
		
	}

	private BetSubmitRequest createBetSubmitRequest(Market market) {
		BetSubmitRequest betSubmitRequest = new BetSubmitRequest();
		betSubmitRequest.setUserId(CreckettConstant.VIRTUAL_USER_ID);
		betSubmitRequest.setUserName("creckett");
		betSubmitRequest.setBetRuns(new Random().nextInt(16));
		betSubmitRequest.setMarketId(market.getId());
		betSubmitRequest.setBetWickets(new Random().nextInt(1) - 1);
		betSubmitRequest.setSession(1);
		betSubmitRequest.setOverNumber(over);
		return betSubmitRequest;
		
	}

}
