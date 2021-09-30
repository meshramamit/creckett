package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.RequestParameters;
import com.creckett.constant.SessionAttributes;
import com.creckett.dto.BetResultResponse;
import com.creckett.exception.AuthenticationException;
import com.creckett.logger.CreckettLogger;
import com.creckett.messaging.application.MessagingAdaptor;
import com.creckett.model.AmountLeft;
import com.creckett.model.Market;
import com.creckett.model.User;
import com.creckett.service.BetResultService;
import com.creckett.service.MatchService;
import com.creckett.service.UserService;

public class PlayMarketController implements Controller {
	private CreckettLogger creckettLogger = CreckettLogger.getInstance();
	private UserService userService;
	private MatchService matchService;
	private BetResultService betResultService; 
	private MessagingAdaptor messagingAdaptor;
	
	
	
	public MessagingAdaptor getMessagingAdaptor() {
		return messagingAdaptor;
	}

	public void setMessagingAdaptor(MessagingAdaptor messagingAdaptor) {
		this.messagingAdaptor = messagingAdaptor;
	}

	public BetResultService getBetResultService() {
		return betResultService;
	}

	public void setBetResultService(BetResultService betResultService) {
		this.betResultService = betResultService;
	}

	public CreckettLogger getCreckettLogger() {
		return creckettLogger;
	}

	public void setCreckettLogger(CreckettLogger creckettLogger) {
		this.creckettLogger = creckettLogger;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		creckettLogger.debug("Playing market....");
		Map<String, Object> model = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute(
				SessionAttributes.USER);

		//checking user session
		if (user == null) {
			throw new AuthenticationException("INVALID USER");
		}
		Long matchId =Long.valueOf(request
				.getParameter(RequestParameters.MATCH_ID));
		Long marketId = Long.valueOf(request
				.getParameter(RequestParameters.MARKET_ID));
		
		User persistentUser = userService.getPersistentUser(user);

		creckettLogger.debug("market:"+persistentUser.getMarket());
		if(persistentUser.getMarket()!=null){
			creckettLogger.debug("request.marketId"+marketId);
			creckettLogger.debug("user.marketId"+persistentUser.getMarket().getId());
		}
		
		Market market = matchService.getMarketById(marketId);
		if( market.getNoOfUsers() < 2 ){
			model.put("status", 0);
			model.put("message", "You cannot join the market alone. Please share this market on your facebook wall using a control on bottom left.");
			return new ModelAndView("genericStatus", model);
		}
		
		if( persistentUser != null && persistentUser.isPlaying() ){
			if( persistentUser.getMarket() != null && !persistentUser.getMarket().getId().equals(marketId) ){
				throw new Exception( "User playing another market can not join this market.." );
			}else{
				persistentUser.setPlaying(true);
				persistentUser.setMarket(matchService.getMarketById(marketId));
				userService.updateUser(persistentUser);
			}
		}else if( persistentUser != null && !persistentUser.isPlaying() ){
			persistentUser.setPlaying(true);
			persistentUser.setMarket(matchService.getMarketById(marketId));
			userService.updateUser(persistentUser);
		}
		//matchService.penaltyForUserToJoiningLate(persistentUser, market.getId(), matchService.getLatestMatchScore(matchId));
		BetResultResponse betResultResponse = betResultService.getBetResult(matchId, marketId,true);
		
		model.put("betStates", betResultResponse.getSessionWiseBets());
		model.put("amountLeft", betResultResponse.getAmountLeft());
		messagingAdaptor.addListnerForBetUpdate(matchId, marketId);
		com.creckett.dto.AmountLeft userAmountLeft = null;  
		for (AmountLeft amountLeft : betResultResponse.getAmountLeft()){
			if (user.getId().longValue() == amountLeft.getUserId().getId().longValue()){
				userAmountLeft = convertToDTO(amountLeft);
				break;
			}
		}
		messagingAdaptor.notifyUserJoiningInTheMarket(matchId,marketId,userAmountLeft);
		return new ModelAndView("BetResultResponse", model);

	}

	private com.creckett.dto.AmountLeft convertToDTO(AmountLeft amountLeft) {
		com.creckett.dto.AmountLeft userAmountLeft = new com.creckett.dto.AmountLeft();
		userAmountLeft.setUserId(amountLeft.getUserId().getId());
		userAmountLeft.setUserName(amountLeft.getUserId().getName());
		userAmountLeft.setAmount(amountLeft.getLeftAmount());
		return userAmountLeft;
	}

}
