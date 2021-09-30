package com.creckett.messaging.application.rtmp.red5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.so.ISharedObject;
import org.red5.server.api.so.ISharedObjectListener;
import org.red5.server.api.statistics.ISharedObjectStatistics;

import com.creckett.core.codec.CodecStrategy;
import com.creckett.core.codec.FreeMarkerCodecStrategy;
import com.creckett.core.exception.TechnicalException;
import com.creckett.dto.AmountLeft;
import com.creckett.dto.BetResultResponse;
import com.creckett.dto.BetSubmitRequest;
import com.creckett.dto.ScoreUpdate;
import com.creckett.logger.CreckettLogger;
import com.creckett.messaging.application.MessagingAdaptor;

/**
 * Messaging adaptor based on Red5. This implementation uses 'Remote Shared
 * Object' in order to send/receive messages to/from connected clients.
 * 
 * @author vkarmani
 * 
 */
public class CreckettMessagingAdaptor extends ApplicationAdapter implements
		MessagingAdaptor {

	/**
	 * Abstract codecStrategy to encode/decode messages to/from client.
	 */
	private CodecStrategy codecStrategy;

	private FreeMarkerCodecStrategy freeMarkerCodecStrategy;

	private CreckettLogger creckettLogger = CreckettLogger.getInstance();

	/**
	 * Bet update listener listening to bet updates from clients
	 */
	private ISharedObjectListener betUpdateListener;

	public FreeMarkerCodecStrategy getFreeMarkerCodecStrategy() {
		return freeMarkerCodecStrategy;
	}

	public void setFreeMarkerCodecStrategy(
			FreeMarkerCodecStrategy freeMarkerCodecStrategy) {
		this.freeMarkerCodecStrategy = freeMarkerCodecStrategy;
	}

	public CodecStrategy getCodecStrategy() {
		return codecStrategy;
	}

	public void setCodecStrategy(CodecStrategy codecStrategy) {
		this.codecStrategy = codecStrategy;
	}

	public ISharedObjectListener getBetUpdateListener() {
		return betUpdateListener;
	}

	public void setBetUpdateListener(ISharedObjectListener betUpdateListener) {
		this.betUpdateListener = betUpdateListener;
	}

	/**
	 * This method sends the score update to all connected clients playing a
	 * given match. This method first encodes the 'scoreUpdate' object using
	 * abstract codecStrategy and then uses an RSO to send the encoded update.
	 * 
	 */
	@Override
	public void pushScoreUpdate(ScoreUpdate scoreUpdate) {
		long matchId = scoreUpdate.getMatchId();
		// Name of an RSO - 'scoreUpdate' followed by $matchId
		// Will create one if this is first time reference, will reuse the same
		// reference otherwise.
		ISharedObject scoreUpdateRSO = this.getSharedObject(scope,
				"scoreUpdate" + matchId, true);
		try {
			creckettLogger.info("Sending score update for match : "
					+ scoreUpdate.getMatchId());
			// Encode the score update
			Object encodedScoreUpdate = codecStrategy.encode(scoreUpdate);
			creckettLogger.debug("Score update response : "
					+ encodedScoreUpdate);
			// Update an RSO with scoreUpdate so that it is received by all
			// connected clients
			scoreUpdateRSO.setAttribute("scoreUpdate", encodedScoreUpdate);

			creckettLogger.info("Score update for match : "
					+ scoreUpdate.getMatchId() + " is pushed. ");
		} catch (TechnicalException e) {
			creckettLogger.error(
					"Technical exception occured while updating scoreUpate RSO for match : "
							+ matchId, e);

		} catch (Exception e) {
			creckettLogger.error(
					"Exception occured while updating scoreUpate RSO for match : "
							+ matchId, e);
		}

	}

	/**
	 * This method attaches the listener to betUpdate RSO so that bets submitted
	 * by clients are notified at server end.
	 */
	@Override
	public synchronized void addListnerForBetUpdate(Long matchId, Long marketId) {
		try {

			String betUpdateRSOId = "betUpdate" + String.valueOf(matchId)
					+ String.valueOf(marketId);

			ISharedObject betUpdateRSO = this.getSharedObject(scope,
					betUpdateRSOId, false);
			ISharedObjectStatistics statistics = betUpdateRSO.getStatistics();
			// Attach the listener only if there is none.
			// Listeners are detached by Red5 when all clients are
			// disconnected.
			// This condition also makes sure
			// that it is reattached whenever any client is reconnected.
			if (statistics.getActiveListeners() == 0) {
				betUpdateRSO.addSharedObjectListener(betUpdateListener);
				creckettLogger.info("Listener is added for BetUpate RSO : "
						+ betUpdateRSO.getName());
			} else {
				creckettLogger
						.info(" betUpdate listener is already added for RSO : "
								+ betUpdateRSO.getName());
			}
		} catch (Throwable t) {
			creckettLogger.error(
					"Exception while attaching the listener to bet update : ",
					t);
		}

	}

	/**
	 * Pushes the bet result to all connected clients within the market.
	 */
	@Override
	public void pushBetResult(BetResultResponse betResultResponse) {
		// Name of an RSO - 'betResult' followed by $matchId followed by
		// $marketId
		String betResultRSOId = "betResult"
				+ String.valueOf(betResultResponse.getMatchId())
				+ String.valueOf(betResultResponse.getMarketId());
		// Refer an RSO with name
		ISharedObject betResultRSO = this.getSharedObject(scope,
				betResultRSOId, false);
		// Encode the response to send it to client
		Object encodedBetResultResponse = getEncodedBetResultResponse(betResultResponse);
		creckettLogger.debug("Bet result response for match id : "
				+ betResultResponse.getMatchId() + " and " + " market id : "
				+ betResultResponse.getMarketId() + " is "
				+ encodedBetResultResponse);
		// Update an RSO so that all connected clients receive it
		betResultRSO.setAttribute("betResult", encodedBetResultResponse);
		creckettLogger.info("Bet result response is pushed for market :"
				+ betResultResponse.getMarketId());

	}

	private Object getEncodedBetResultResponse(
			BetResultResponse betResultResponse) {
		Map<String, Object> betResultMap = new HashMap<String, Object>();
		betResultMap.put("betStates", betResultResponse.getSessionWiseBets());
		betResultMap.put("amountLeft", betResultResponse.getAmountLeft());
		Map<String, Map<String, Object>> model = new HashMap<String, Map<String, Object>>();
		model.put("betResultResponse", betResultMap);
		return freeMarkerCodecStrategy.encode(model);
	}

	@Override
	public void notifyUserJoiningInTheMarket(Long matchId, Long marketId,
			AmountLeft userAmountLeft) {
		String rsoName = "chat" + matchId + marketId;
		ISharedObject rso = this.getSharedObject(scope, rsoName, false);
		String encodedString = (String) codecStrategy.encode(userAmountLeft);
		rso.setAttribute(" user " + userAmountLeft.getUserId(), encodedString);
	}

	@Override
	public void pushBetUpdate(long matchId, long marketId,
			BetSubmitRequest betSubmitRequest) {
		addListnerForBetUpdate(matchId, marketId);
		String betUpdateRSOId = "betUpdate" + String.valueOf(matchId)
				+ String.valueOf(marketId);

		ISharedObject betUpdateRSO = this.getSharedObject(scope,
				betUpdateRSOId, false);
		String betSumbmitXML = (String) codecStrategy.encode(betSubmitRequest);
		creckettLogger.debug(" Pushing bet update from user : " + betSubmitRequest.getUserId() + " is " + betSumbmitXML);
		Map<String,Object> attributes = new HashMap<String, Object>();
		attributes.put("betXML",betSumbmitXML);
		betUpdateRSO.setAttribute(String.valueOf(betSubmitRequest.getUserId()), attributes);
	}
	
	@Override
	public RTMPStatsSummary getRTMPStatsSummary(){
		RTMPStatsSummary rtmpStatsSummary = new RTMPStatsSummary();
		rtmpStatsSummary.setNoOfConnections(getConnections().size());
		Set<String> names =getSharedObjectNames(this.getScope());
		rtmpStatsSummary.setNoOfRSOs(names.size());
		return rtmpStatsSummary;
	}

	@Override
	public RTMPConnectionStatsSummary getRTMPConnectionStats() {
		Collection<Set<IConnection>> connections =  getConnections();
		RTMPConnectionStatsSummary rtmpConnectionStatSummary = new RTMPConnectionStatsSummary();
		for (Set<IConnection> iconnections : connections){
			for (IConnection iconnection : iconnections){
				rtmpConnectionStatSummary.addStat(iconnection.getRemoteAddress(),iconnection.getReadBytes(),iconnection.getWrittenBytes());
			}
		}
		return rtmpConnectionStatSummary;
	}
	
	@Override
	public List<RTMPRSOStat> getRTMPRSOStats() {
		Set<String> rsoNames = getSharedObjectNames(scope);
		List<RTMPRSOStat> rsoStats = new ArrayList<RTMPRSOStat>();
		for (String rsoName : rsoNames){
			ISharedObject rso = getSharedObject(scope, rsoName);
			rsoStats.add(new RTMPRSOStat(rsoName, rso.getVersion()));
		}
		return rsoStats;
	}

	@Override
	public void sendWinner(Long matchId, String winner) {
		ISharedObject scoreUpdateRSO = this.getSharedObject(scope,
				"scoreUpdate" + matchId, true);
		scoreUpdateRSO.setAttribute("matchWinner", winner);
		creckettLogger.debug(" Match winner sent as : " + winner);
		
	}
	
	

}
