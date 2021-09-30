package com.creckett.service.impl;

import java.util.List;

import org.springframework.scheduling.annotation.Async;

import com.creckett.dto.BetResultResponse;
import com.creckett.logger.CreckettLogger;
import com.creckett.service.BetResultService;
public class AsyncBetResultHelper {
	
	private static CreckettLogger creckettLogger = CreckettLogger.getInstance();
	private BetResultService betResultService;
	
	
	public BetResultService getBetResultService() {
		return betResultService;
	}


	public void setBetResultService(BetResultService betResultService) {
		this.betResultService = betResultService;
	}

	@Async
	public void onOverCompleted(long matchId){
		try {
			List<BetResultResponse> betResultResponses =betResultService.onOverCompleted(matchId);
			betResultService.pushBetResults(betResultResponses);
		}catch(Throwable e){
			creckettLogger.error("Exception while calculating bet results ", e);
		}
	}
}
