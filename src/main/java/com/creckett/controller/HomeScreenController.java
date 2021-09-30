package com.creckett.controller;

import java.util.ArrayList;
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
import com.creckett.dto.FacebookUserFriend;
import com.creckett.dto.MatchMasterWithMarketDTO;
import com.creckett.model.Market;
import com.creckett.model.MatchMaster;
import com.creckett.model.User;
import com.creckett.model.UserPreference;
import com.creckett.service.MatchService;
import com.creckett.service.UserPreferenceService;
import com.creckett.util.DateUtils;

public class HomeScreenController implements Controller {

	private Integer interval;

	private MatchService matchService;

	private UserPreferenceService userPreferenceService;

	private DateUtils dateUtils;

	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		User user = (User) request.getSession(true).getAttribute(
				SessionAttributes.USER);
		String marketToken = (String) request.getParameter("marketToken");
		Map<String, Object> model = new HashMap<String, Object>();
		String marketStatus = null;

		// User user = UserPreferenceController.getHardCodedUser();

		if (marketToken != null && !"".equalsIgnoreCase(marketToken)) {
			request.getSession().setAttribute(
					SessionAttributes.USER_MARKET_TOKEN, marketToken);
		}

		if (user == null) {
			model.put("status", 0);
			return new ModelAndView("genericStatus", model);
		}

		if (request.getSession().getAttribute(
				SessionAttributes.USER_MARKET_TOKEN) != null
				&& !"".equalsIgnoreCase((String) request.getSession()
						.getAttribute(SessionAttributes.USER_MARKET_TOKEN))) {
			marketStatus = matchService.autoJoinMarket((String) request
					.getSession().getAttribute(
							SessionAttributes.USER_MARKET_TOKEN), user,
					(ArrayList<FacebookUserFriend>) request.getSession()
							.getAttribute(SessionAttributes.USER_FRIENDS_LIST));
			request.getSession().removeAttribute(
					SessionAttributes.USER_MARKET_TOKEN);
		}

		Date fromDate = dateUtils.generateTodaysDateUsingCalendar();
		Date toDate = dateUtils.generateDateAfterNDaysUsingCalendar(interval);

		List<MatchMasterWithMarketDTO> upcomingMatchesWithMarketForUser = new ArrayList<MatchMasterWithMarketDTO>();
		List<MatchMaster> upcomingMatches = matchService
				.getListOfUpcomingMatches(fromDate, toDate);

		for (MatchMaster matchMaster : upcomingMatches) {

			List<Market> markets = matchService.getMatchMarketsForUser(
					matchMaster.getId(), user.getId());
			List<Market> invites = matchService.getMarketInvitesForUser(
					matchMaster.getId(),
					(ArrayList<FacebookUserFriend>) request.getSession()
							.getAttribute(SessionAttributes.USER_FRIENDS_LIST));
			if( invites != null && !invites.isEmpty() ){
				upcomingMatchesWithMarketForUser.add(new MatchMasterWithMarketDTO(
						matchMaster, markets, invites));
			}else{
				upcomingMatchesWithMarketForUser.add(new MatchMasterWithMarketDTO(
						matchMaster, markets));
			}
		}

		Collections.sort(upcomingMatchesWithMarketForUser, new Comparator<MatchMasterWithMarketDTO>(){
			@Override
			public int compare(MatchMasterWithMarketDTO current, MatchMasterWithMarketDTO other) {
				if( current.getMatchMaster().getMatchDate().after(other.getMatchMaster().getMatchDate()) ){
					return 1;
				}else if( current.getMatchMaster().getMatchDate().before(other.getMatchMaster().getMatchDate()) ){
					return -1;
				}
				return 0;
			}
		});
		
		UserPreference userPreference = userPreferenceService
				.getUserPreference(user);

		System.out.println("FB friends : " + request.getSession()
				.getAttribute(SessionAttributes.LIMIT_USER_FRIENDS_LIST));
		model.put("matches", upcomingMatchesWithMarketForUser);
		model.put("dateUtils", dateUtils);
		model.put("today", new Date());
		model.put(SessionAttributes.USER, user);
		model.put("userpreference", userPreference);
		model.put("marketStatus", marketStatus);
		model.put("fbFriends", (String) request.getSession()
				.getAttribute(SessionAttributes.LIMIT_USER_FRIENDS_LIST));

		return new ModelAndView("homeScreen", model);
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
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

	/**
	 * @return the userPreferenceService
	 */
	public UserPreferenceService getUserPreferenceService() {
		return userPreferenceService;
	}

	/**
	 * @param userPreferenceService
	 *            the userPreferenceService to set
	 */
	public void setUserPreferenceService(
			UserPreferenceService userPreferenceService) {
		this.userPreferenceService = userPreferenceService;
	}

}
