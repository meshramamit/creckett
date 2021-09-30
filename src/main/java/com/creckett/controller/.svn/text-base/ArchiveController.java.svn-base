package com.creckett.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creckett.constant.SessionAttributes;
import com.creckett.dto.SessionWiseArchiveBets;
import com.creckett.dto.UserSessionBets;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.AmountLeft;
import com.creckett.model.ArchivedAmountLeft;
import com.creckett.model.BetState;
import com.creckett.model.Market;
import com.creckett.model.User;
import com.creckett.service.ArchiveService;
import com.creckett.service.MarketDetailChartService;
import com.creckett.service.MatchService;
import com.creckett.util.DateUtils;

public class ArchiveController extends MultiActionController {

	private MatchService matchService;
	private ArchiveService archiveService;
	private MarketDetailChartService marketDetailChartService;
	private DateUtils dateUtils;
	
	public ModelAndView loadArchivedMatchList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = (User) request.getSession().getAttribute(SessionAttributes.USER);
		Map<String, Object> model = new HashMap<String, Object>();
		
		if (user == null) {
			throw new AuthenticationException("INVALID USER");
		}
		
		List<Market> markets = archiveService.getArchivedMatchListForUser(user);
		Collections.sort(markets, new Comparator<Market>(){
			@Override
			public int compare(Market current, Market other) {
				if( current.getMatch().getMatchDate().after(other.getMatch().getMatchDate()) ){
					return -1;
				}else if( current.getMatch().getMatchDate().before(other.getMatch().getMatchDate()) ){
					return 1;
				}
				return 0;
			}
		});
		model.put("markets", markets);
		model.put("dateUtils", dateUtils);
		return new ModelAndView("ArchivedMatchList", model);
	}
	
	public ModelAndView loadArchivedBetMarketStatistics(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = (User) request.getSession().getAttribute(SessionAttributes.USER);
		Map<String, Object> model = new HashMap<String, Object>();
		Long marketId = null;
		if (user == null) {
			throw new AuthenticationException("INVALID USER");
		}
		
		try{
			marketId = Long.parseLong(request.getParameter("marketId"));
		}catch( Exception e ){
			throw new Exception( "Not Valid params" );
		}
		
		List<ArchivedAmountLeft> bets = archiveService.getArchivedAmountLeftForMarket(marketId);
		Collection<SessionWiseArchiveBets> sessionWiseBets = archiveService.getArchivedSessionWiseBets( bets );
		model.put("sessionbets", sessionWiseBets);
		model.put("dateUtils", dateUtils);
		return new ModelAndView("ArchivedBetMarketStatistics", model);
	}
	
	public ModelAndView loadArchivedBetData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute(SessionAttributes.USER);
		Map<String, Object> model = new HashMap<String, Object>();
		Long marketId = null;
		if (user == null) {
			throw new AuthenticationException("INVALID USER");
		}
		
		try{
			marketId = Long.parseLong(request.getParameter("marketId"));
		}catch( Exception e ){
			throw new Exception( "Not Valid params" );
		}
		
		List<BetState> bets = archiveService.getArchivedBetsForMarket(marketId);		
		Collection<UserSessionBets> sessionWiseBets = matchService.getSessionWiseMarketBets(bets).values();
		List<AmountLeft> amountLeft = marketDetailChartService
		.getMarketMatchDetails(marketId, matchService.getMarketById(marketId).getMatch().getId());
		model.put("betStates", sessionWiseBets);
		model.put("amountLeft", amountLeft);
		model.put("dateUtils", dateUtils);
		return new ModelAndView("ArchivedBetData", model);
		
	}

	public MatchService getMatchService() {
		return matchService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	public ArchiveService getArchiveService() {
		return archiveService;
	}

	public void setArchiveService(ArchiveService archiveService) {
		this.archiveService = archiveService;
	}

	public MarketDetailChartService getMarketDetailChartService() {
		return marketDetailChartService;
	}

	public void setMarketDetailChartService(
			MarketDetailChartService marketDetailChartService) {
		this.marketDetailChartService = marketDetailChartService;
	}

	public DateUtils getDateUtils() {
		return dateUtils;
	}

	public void setDateUtils(DateUtils dateUtils) {
		this.dateUtils = dateUtils;
	}
	
}
