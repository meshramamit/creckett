package com.creckett.messaging.application;

import java.util.List;

import com.creckett.dto.BetResultResponse;
import com.creckett.dto.BetSubmitRequest;
import com.creckett.dto.ScoreUpdate;
import com.creckett.messaging.application.rtmp.red5.RTMPConnectionStatsSummary;
import com.creckett.messaging.application.rtmp.red5.RTMPRSOStat;
import com.creckett.messaging.application.rtmp.red5.RTMPStatsSummary;

/**
 * This interface contains methods to push various messages to client OR receive
 * updates from clients as part of server to client communication. Concrete
 * classes implementing this interface will need to implement the below methods
 * as per given communication protocol.
 * 
 * @author vkarmani
 * 
 */
public interface MessagingAdaptor {

	void pushScoreUpdate(ScoreUpdate scoreUpdate);

	void addListnerForBetUpdate(Long matchId, Long marketId);

	void pushBetResult(BetResultResponse betResultResponse);

	void notifyUserJoiningInTheMarket(Long matchId, Long marketId,
			com.creckett.dto.AmountLeft userAmountLeft);
	
	void pushBetUpdate(long matchId, long marketId, BetSubmitRequest betSubmitRequest);

	RTMPStatsSummary getRTMPStatsSummary();

	RTMPConnectionStatsSummary getRTMPConnectionStats();

	List<RTMPRSOStat> getRTMPRSOStats();
	
	void sendWinner(Long matchId,String winner);
}
