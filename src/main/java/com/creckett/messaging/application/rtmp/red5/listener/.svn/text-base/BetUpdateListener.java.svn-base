package com.creckett.messaging.application.rtmp.red5.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.red5.server.api.IAttributeStore;
import org.red5.server.api.so.ISharedObjectBase;
import org.red5.server.api.so.ISharedObjectListener;

import com.creckett.core.codec.CodecStrategy;
import com.creckett.dto.BetSubmitRequest;
import com.creckett.logger.CreckettLogger;
import com.creckett.service.MatchService;
/**
 * Bet update listener listening to various updates on betUpdate RSO.
 * @author vkarmani
 *
 */
public class BetUpdateListener implements ISharedObjectListener {

	private CreckettLogger creckettLogger = CreckettLogger.getInstance();

	private CodecStrategy codecStrategy;

	private MatchService matchService;
	
	public MatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}
	
	public CodecStrategy getCodecStrategy() {
		return codecStrategy;
	}

	public void setCodecStrategy(CodecStrategy codecStrategy) {
		this.codecStrategy = codecStrategy;
	}
	
	@Override
	public void onSharedObjectConnect(ISharedObjectBase paramISharedObjectBase) {

	}

	@Override
	public void onSharedObjectDisconnect(
			ISharedObjectBase paramISharedObjectBase) {

	}
	
	/**
	 * Handler for bet update event. It decodes the incoming bet update string into a POJO
	 * and persist it for later usages like result calculations, archival etc.
	 */

	@Override
	public void onSharedObjectUpdate(ISharedObjectBase paramISharedObjectBase,
			String paramString, Object betUpdateObject) {
		try{
			String betUpdateStr = (String) ((HashMap) betUpdateObject)
					.get("betXML");
			creckettLogger.debug(" Bet submitted by user : " + paramString + " is "
					+ betUpdateStr);
			BetSubmitRequest betSubmitRequest = (BetSubmitRequest) codecStrategy
					.decode(betUpdateStr);
			creckettLogger.info("Bet is being saved for user " + paramString);
			int status = matchService
					.submitUserBet(betSubmitRequest.getUserId(),
							betSubmitRequest.getMarketId(),
							betSubmitRequest.getBetRuns(),
							betSubmitRequest.getBetWickets(),
							betSubmitRequest.getSession(),
							betSubmitRequest.getOverNumber());
	
			creckettLogger.debug("Bet submittion status : " + status);
		}catch(Exception e){
			creckettLogger.error("Exception occurred while updating user bet for user id : " + paramString, e);
		}

	}

	@Override
	public void onSharedObjectUpdate(ISharedObjectBase paramISharedObjectBase,
			IAttributeStore paramIAttributeStore) {

	}

	@Override
	public void onSharedObjectUpdate(ISharedObjectBase paramISharedObjectBase,
			Map<String, Object> paramMap) {

	}

	@Override
	public void onSharedObjectDelete(ISharedObjectBase paramISharedObjectBase,
			String paramString) {

	}

	@Override
	public void onSharedObjectClear(ISharedObjectBase paramISharedObjectBase) {

	}

	@Override
	public void onSharedObjectSend(ISharedObjectBase paramISharedObjectBase,
			String paramString, List<?> paramList) {

	}

}
