package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.Market;
import com.creckett.model.User;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

public class CreateMarketController implements Controller {

	private MatchService matchService;

	private DateUtils dateUtils;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = (User) request.getSession().getAttribute(SessionAttributes.USER);
		if (user == null) {
			throw new AuthenticationException("INVALID USER");
		}
		
		Long matchId = Long.valueOf(request.getParameter("match_id"));
		int groupOverValue = Integer.valueOf(request.getParameter("group_over_val"));
		String level = request.getParameter("difficulty_level");
		
		Market market = null;
		
		if( request.getParameter("market_id") != null ){
			Long marketId = Long.valueOf(request.getParameter("market_id"));
			market = matchService.updateMarketForUser(user, marketId, groupOverValue, level);
		}else{
			market = matchService.createMarketForUser(user, matchId, groupOverValue, level);
			matchService.autoJoinMarketForVirtualUser(market);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("market", market);
		model.put("dateUtils", dateUtils);
		model.put(SessionAttributes.USER, user);

		return new ModelAndView("MarketCreatedScreen", model);
	}

	public MatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	public DateUtils getDateUtils() {
		return dateUtils;
	}

	public void setDateUtils(DateUtils dateUtils) {
		this.dateUtils = dateUtils;
	}
	
}
