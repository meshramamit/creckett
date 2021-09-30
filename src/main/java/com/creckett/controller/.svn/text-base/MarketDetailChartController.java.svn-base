package com.creckett.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.dto.MarketMatchSessionDetail;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.AmountLeft;
import com.creckett.model.User;
import com.creckett.service.MarketDetailChartService;

public class MarketDetailChartController implements Controller {

    private MarketDetailChartService marketDetailChartService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
	    HttpServletResponse respose) throws Exception {

	User user = (User) request.getSession().getAttribute(
		SessionAttributes.USER);
	Long marketId;
	Long matchId ;
	Map<Integer, MarketMatchSessionDetail> toReturn = new HashMap<Integer, MarketMatchSessionDetail>();
	Map<String, Object> model = new HashMap<String, Object>();
	if (user != null) {
	    
		marketId = Long.valueOf( request.getParameter("marketId"));
	    matchId = Long.valueOf( request.getParameter("matchId"));
	    
	    if (marketId != null && matchId != null) {
		List<AmountLeft> list = marketDetailChartService
			.getMarketMatchDetails(marketId, matchId);
		toReturn = marketDetailChartService
			.getSessionWiseMarketMatchDetail(list);
		model.put("sessions", toReturn.values());
	    } else {
		throw new IllegalArgumentException(
			"Not valid request arguments " + marketId + ":"
				+ matchId);
	    }
	} else {
	    throw new AuthenticationException("USER NOT VALID");
	}
	return new ModelAndView("MarketMatchChartDetail", model);
    }

    public MarketDetailChartService getMarketDetailChartService() {
	return marketDetailChartService;
    }

    public void setMarketDetailChartService(
	    MarketDetailChartService marketDetailChartService) {
	this.marketDetailChartService = marketDetailChartService;
    }

}
