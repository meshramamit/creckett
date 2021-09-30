package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.CreckettConstant;
import com.creckett.constant.SessionAttributes;
import com.creckett.model.User;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

public class DeleteMarketController implements Controller {

	private MatchService matchService;
	
	private DateUtils dateUtils;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long marketId = Long.valueOf(request.getParameter("marketId"));
		User user = (User) request.getSession().getAttribute(
				SessionAttributes.USER);

		int status = matchService.deleteMarket(marketId, user);

		if( status == 1 )
			request.getRequestDispatcher(CreckettConstant.HOME_SCREEN_URL).forward(request, response);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		model.put("dateUtils", dateUtils);
		model.put(SessionAttributes.USER, user);

		return new ModelAndView("DeleteMarketScreen", model);
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
