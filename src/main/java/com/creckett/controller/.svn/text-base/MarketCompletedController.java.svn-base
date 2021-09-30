/**
 * 
 */
package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.model.User;
import com.creckett.service.MatchService;

/**
 * @author Latesh
 *
 */
public class MarketCompletedController implements Controller {

	private MatchService matchService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = (User) request.getSession().getAttribute(SessionAttributes.USER);
		Long marketId = Long.valueOf(request.getParameter("market_id"));
		String marketWinners = request.getParameter("market_winners");
		Map<String, Object> model = new HashMap<String, Object>();
		
		int status = 0;
		
		if( user != null ){
			if( marketId != null && marketWinners != null && !marketWinners.isEmpty() ){
				status = matchService.updateMarketWinners(marketId, marketWinners);
			}
		}
		
		model.put("status", status);
		return new ModelAndView("genericStatus", model);
	}

	public MatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

}
