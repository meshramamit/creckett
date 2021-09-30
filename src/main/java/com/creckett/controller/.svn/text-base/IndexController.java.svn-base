package com.creckett.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.model.MatchMaster;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

public class IndexController implements Controller {

	private Integer interval;

	private MatchService matchService;

	private DateUtils dateUtils;
	
	private String userLogoutURL;
	
	private String homePageUrl;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Date fromDate = dateUtils.generateTodaysDateUsingCalendar();
		Date toDate = dateUtils.generateDateAfterNDaysUsingCalendar(interval);

		List<MatchMaster> listOfUpcomingMatches = matchService
				.getListOfUpcomingMatches(fromDate, toDate);

		Collections.sort(listOfUpcomingMatches, new Comparator<MatchMaster>(){
			@Override
			public int compare(MatchMaster current, MatchMaster other) {
				if( current.getMatchDate().after(other.getMatchDate()) ){
					return 1;
				}else if( current.getMatchDate().before(other.getMatchDate()) ){
					return -1;
				}
				return 0;
			}
		});
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listOfUpcomingMatches", listOfUpcomingMatches);
		model.put("dateUtils", dateUtils);
		model.put("today", new Date());

		if (request.getParameter("logout") != null
				&& request.getParameter("logout").equalsIgnoreCase("true")) {
			if (request.getParameter("logout_from_facebook") != null
					&& request.getParameter("logout_from_facebook").equalsIgnoreCase("true")) {
				String url = this.userLogoutURL.replaceAll("#homepage#", homePageUrl );
				url = url + request.getSession().getAttribute(SessionAttributes.USER_ACCESS_TOKEN);
				model.put("logoutUrl", url);
				request.getSession().invalidate();
				return new ModelAndView("logout", model);
			}
			request.getSession().invalidate();
		}

		return new ModelAndView("index", model);
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public DateUtils getDateUtils() {
		return dateUtils;
	}

	public void setDateUtils(DateUtils dateUtils) {
		this.dateUtils = dateUtils;
	}

	public MatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	public String getUserLogoutURL() {
		return userLogoutURL;
	}

	public void setUserLogoutURL(String userLogoutURL) {
		this.userLogoutURL = userLogoutURL;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

}
