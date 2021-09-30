package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.dao.UserMatchVoteDAO;
import com.creckett.dto.MatchVoteGlobalTrend;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.MatchMaster;
import com.creckett.model.TeamMaster;
import com.creckett.model.User;
import com.creckett.model.UserMatchVote;
import com.creckett.service.MatchService;
import com.creckett.service.UserMatchVoteService;

public class MatchVoteGlobalTrendController implements Controller {

	
	private MatchService matchService;
	
	private UserMatchVoteService userMatchVoteService;
	
	
	public UserMatchVoteService getUserMatchVoteService() {
		return userMatchVoteService;
	}

	public void setUserMatchVoteService(UserMatchVoteService userMatchVoteService) {
		this.userMatchVoteService = userMatchVoteService;
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
		User user = (User) request.getSession().getAttribute(SessionAttributes.USER);
		if (user == null){
			throw new AuthenticationException("INVALID USER");
		}
		MatchMaster matchMaster = matchService.getMatchById(new Long(request.getParameter("matchId")));
		TeamMaster team1 = matchMaster.getTeam1();
		int numberOfVotesForTeam1 = userMatchVoteService.getGlobalTrendForMatchAndTeam(matchMaster, team1);
		TeamMaster team2 = matchMaster.getTeam2();
		int numberOfVotesForTeam2 = userMatchVoteService.getGlobalTrendForMatchAndTeam(matchMaster, team2);
		String userVote = "0";
		UserMatchVote umv = userMatchVoteService.findByUserAndMatch(user.getId(), matchMaster.getId());
		if (umv != null){
			TeamMaster teamMaster = userMatchVoteService.findByUserAndMatch(user.getId(), matchMaster.getId()).getUserVote();
			if (teamMaster == null){
				userVote = "Draw";
			}else{
				userVote = teamMaster.getTeamName();
			}
		}
		int numberOfVotesForDraw = userMatchVoteService.getGlobalTrendForMatchAndTeam(matchMaster, null);
		MatchVoteGlobalTrend matchVoteGlobalTrend = new MatchVoteGlobalTrend();
		matchVoteGlobalTrend.setTeam1Id(team1.getId());
		matchVoteGlobalTrend.setTeam1Name(team1.getTeamName());
		matchVoteGlobalTrend.setNumberOfVotesForTeam1(numberOfVotesForTeam1);
		matchVoteGlobalTrend.setTeam2Id(team2.getId());
		matchVoteGlobalTrend.setTeam2Name(team2.getTeamName());
		matchVoteGlobalTrend.setNumberOfVotesForTeam2(numberOfVotesForTeam2);
		matchVoteGlobalTrend.setUserVote(userVote);
		matchVoteGlobalTrend.setNumberOfVotesForDraw(numberOfVotesForDraw);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("matchVoteGlobalTrend", matchVoteGlobalTrend);
		return new ModelAndView("matchVoteGlobalTrend", model);
	}

}
