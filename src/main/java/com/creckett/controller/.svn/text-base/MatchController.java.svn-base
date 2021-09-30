package com.creckett.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creckett.constant.SessionAttributes;
import com.creckett.exception.AuthenticationException;
import com.creckett.logger.CreckettLogger;
import com.creckett.model.MatchMaster;
import com.creckett.model.MatchScore;
import com.creckett.model.MatchStatus;
import com.creckett.model.User;
import com.creckett.service.LiveMatchScoreService;
import com.creckett.service.MatchService;
import com.creckett.service.UserService;
import com.creckett.service.impl.AsyncBetResultHelper;
import com.creckett.util.DateUtils;
import com.creckett.util.StringUtil;

public class MatchController extends MultiActionController {

    private Integer interval;

    private MatchService matchService;

    private DateUtils dateUtils;
    
    private UserService userService;
    
	private LiveMatchScoreService liveMatchScoreService;
	
	private AsyncBetResultHelper asyncBetResultHelper;
	
	private CreckettLogger creckettLogger;
	
	

	public CreckettLogger getCreckettLogger() {
		return creckettLogger;
	}

	public void setCreckettLogger(CreckettLogger creckettLogger) {
		this.creckettLogger = creckettLogger;
	}

	public AsyncBetResultHelper getAsyncBetResultHelper() {
		return asyncBetResultHelper;
	}

	public void setAsyncBetResultHelper(AsyncBetResultHelper asyncBetResultHelper) {
		this.asyncBetResultHelper = asyncBetResultHelper;
	}

	/**
	 * @return the liveMatchScoreService
	 */
	public LiveMatchScoreService getLiveMatchScoreService() {
		return liveMatchScoreService;
	}

	/**
	 * @param liveMatchScoreService the liveMatchScoreService to set
	 */
	public void setLiveMatchScoreService(LiveMatchScoreService liveMatchScoreService) {
		this.liveMatchScoreService = liveMatchScoreService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ModelAndView loadMatches(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    Date fromDate = dateUtils.generateTodaysDateUsingCalendar();
	    Date toDate = dateUtils
		    .generateDateAfterNDaysUsingCalendar(interval);
	    List<MatchMaster> upcomingMatches = matchService
		    .getListOfUpcomingMatches(fromDate, toDate);
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("matches", upcomingMatches);
	    model.put("dateUtils", dateUtils);
	    model.put("today", new Date());
	    return new ModelAndView("loadMatches", model);
	} else {
	    throw new AuthenticationException("USER NOT VALID");
	}
    }

    public ModelAndView addMatch(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    String team1 = request.getParameter("team1");
	    String team2 = request.getParameter("team2");
	    String overs = request.getParameter("overs");
	    String matchDate = request.getParameter("matchDate");
	    String matchTime = request.getParameter("matchTime");
	    if (team1 != null && team2 != null && overs != null
		    && matchDate != null && matchTime != null
		    && !team1.equalsIgnoreCase(team2)) {

		MatchMaster match = matchService.createMatch(team1, team2,
			overs, matchDate, matchTime);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("match", match);
		model.put("dateUtils", dateUtils);
		return new ModelAndView("createMatch", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + team1 + ":" + team2
				+ ":" + overs + ":" + matchDate + ":"
				+ matchTime);
	    }
	} else {
	    throw new AuthenticationException("Invalid user");
	}
    }

    public ModelAndView updateMatch(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    String team1 = request.getParameter("team1");
	    String team2 = request.getParameter("team2");
	    String overs = request.getParameter("overs");
	    String matchDate = request.getParameter("matchDate");
	    String matchTime = request.getParameter("matchTime");
	    String winner = request.getParameter("winner");
	    if (team1 != null && team2 != null && overs != null
		    && matchDate != null && matchTime != null && winner != null) {
		String matchId = request.getParameter("matchId");
		int status = -1;
		if (matchId != null && !matchId.isEmpty()) {
		    status = matchService.updateMatch(matchId, team1, team2,
			    overs, matchDate, matchTime, winner);
		    liveMatchScoreService.cleanupScoreCommentary();
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		return new ModelAndView("matchGenericStatus", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + team1 + ":" + team2
				+ ":" + overs + ":" + matchDate + ":"
				+ matchTime);
	    }
	} else {
	    throw new AuthenticationException("Invalid user");
	}
    }

    public ModelAndView finishMatch(HttpServletRequest request,
    	    HttpServletResponse response) throws Exception {
    	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
    	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
    	    String winner = request.getParameter("winner");
    		String matchId = request.getParameter("matchId");
    		int status = -1;
    		status = matchService.finishMatch(matchId, winner);
    		Map<String, Object> model = new HashMap<String, Object>();
    		model.put("status", status);
    		return new ModelAndView("matchGenericStatus", model);
        }else{
        	throw new AuthenticationException("Invalid user");
        }
    }
    
    
    public ModelAndView deleteMatch(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    String matchId = request.getParameter("matchId");
	    int status = -1;
	    if (matchId != null && !matchId.isEmpty()) {
		status = matchService.deleteMatch(matchId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		return new ModelAndView("matchGenericStatus", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + matchId);
	    }
	} else {
	    throw new AuthenticationException("Invalid user");
	}
    }

    public ModelAndView loadMatchScore(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	User user = (User) request.getSession().getAttribute(
		SessionAttributes.USER);
	if ((isAdmin != null && isAdmin.equalsIgnoreCase("true"))
		|| (user != null)) {
	    String matchId = request.getParameter("matchId");
	    if (matchId != null && !matchId.isEmpty()) {
		Map<String, List<MatchScore>> sessionMatchScores = matchService
			.getMatchScore(matchId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("matchScoresMap", sessionMatchScores);
		model.put("matchId", matchId);
		return new ModelAndView("loadMatchScore", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + matchId);
	    }

	} else {
	    throw new AuthenticationException("Invalid user");
	}

    }

    public ModelAndView invalidateMatchOver(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    String matchId = request.getParameter("matchId");
	    String sessionId = request.getParameter("session");
	    String over = request.getParameter("over");
	    if (matchId != null && !matchId.isEmpty() && sessionId != null
		    && !sessionId.isEmpty() && over != null && !over.isEmpty()) {
		int status = matchService.addNewScoreAndInvalidate(matchId,
			sessionId, over);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		model.put("action", "invalidate");
		return new ModelAndView("scoreStatus", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + matchId + ":"
				+ sessionId + ":" + over);
	    }
	} else {
	    throw new AuthenticationException("Invalid user");
	}
    }

    public ModelAndView updateMatchScore(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    String matchId = request.getParameter("matchId");
	    String sessionId = request.getParameter("session");
	    String over = request.getParameter("over");
	    String runs = request.getParameter("runs");
	    String wickets = request.getParameter("wickets");
	    String score = request.getParameter("score");
	    if (matchId != null && !matchId.isEmpty() && sessionId != null
		    && !sessionId.isEmpty() && over != null && !over.isEmpty()
		    && runs != null && !runs.isEmpty() && wickets != null
		    && !wickets.isEmpty() && score != null && !score.isEmpty()) {
	    creckettLogger.debug(" Update score received with matchId := " + matchId + "  sessionId = " + sessionId + " over = " + over
	    		+ " runs = " + runs + " wickets = " + wickets + " score =" + score);
		int status = matchService.updateScore(matchId, sessionId, over,
			runs, wickets, score);
		// On over completion, calculate the results for all markets and push them to users
		asyncBetResultHelper.onOverCompleted(StringUtil.getLongFromString(matchId));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		model.put("action", "score");
		return new ModelAndView("scoreStatus", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + matchId + ":"
				+ sessionId + ":" + over + ":" + score);
	    }
	} else {
	    throw new AuthenticationException("Invalid user");
	}
    }
    
    public ModelAndView updateMatchSession(HttpServletRequest request,
    	    HttpServletResponse response) throws Exception {
    	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
    	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
    		String matchId = request.getParameter("matchId");
    	    String sessionId = request.getParameter("session");
    	    if (matchId != null && !matchId.isEmpty() && sessionId != null
    			    && !sessionId.isEmpty()){
    	    	int status = matchService.updateSsessionCompletion(matchId, sessionId);
    	    		Map<String, Object> model = new HashMap<String, Object>();
    	    		model.put("status", status);
    	    		return new ModelAndView("genericStatus", model);
    	    }else{
    	    	throw new IllegalArgumentException(
    	    			"Not valid request parameter." + matchId + ":"
    	    				+ sessionId);
    	    }
    	}else {
    	    throw new AuthenticationException("Invalid user");
    	}
    }
    
    public ModelAndView updateMatchStart(HttpServletRequest request,
    	    HttpServletResponse response) throws Exception {
    	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
    	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
    		String matchId = request.getParameter("matchId");
    	    if (matchId != null && !matchId.isEmpty()){
    	    	int status = matchService.updateMatchStatus(matchId, MatchStatus.PLAYING);
    	    		Map<String, Object> model = new HashMap<String, Object>();
    	    		model.put("status", status);
    	    		return new ModelAndView("genericStatus", model);
    	    }else{
    	    	throw new IllegalArgumentException(
    	    			"Not valid request parameter." + matchId);
    	    }
    	}else {
    	    throw new AuthenticationException("Invalid user");
    	}
    }

    public MatchService getMatchService() {
	return matchService;
    }

    public void setMatchService(MatchService matchService) {
	this.matchService = matchService;
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

    
	/**
	 * This method used to reset playing flag for all users.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView resetMatchPlayingFlag(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String isAdmin = (String) request.getSession().getAttribute("isAdmin");
		if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
			int status = -1;
			Map<String, Object> model = new HashMap<String, Object>();
			status = userService.resetMatchPlayingflag();
			model.put("status", status);
			return new ModelAndView("matchGenericStatus", model);
		} else {
			throw new AuthenticationException("Invalid user");
		}
	}

}
