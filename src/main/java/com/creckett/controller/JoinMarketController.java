package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.model.Market;
import com.creckett.model.User;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

public class JoinMarketController implements Controller {

	private MatchService matchService;
	
	private DateUtils dateUtils;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Long marketId = Long.valueOf(request.getParameter("marketId"));
		String marketToken = request.getParameter("marketToken");
		Map<String, Object> model = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute(
				SessionAttributes.USER);

		Market market = matchService.joinMarket(marketToken, user);
		
		if (market == null) {
			model.put("status", 0);
			return new ModelAndView("genericStatus", model);
		}

		
		model.put("market", market);
		model.put("dateUtils", dateUtils);
		model.put(SessionAttributes.USER, user);

		return new ModelAndView("JoinMarketScreen", model);
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
