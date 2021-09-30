package com.creckett.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.creckett.constant.SessionAttributes;
import com.creckett.dao.MatchMasterDAO;
import com.creckett.dao.TeamDAO;
import com.creckett.dao.UserMatchVoteDAO;
import com.creckett.exception.AuthenticationException;
import com.creckett.model.TeamMaster;
import com.creckett.model.User;
import com.creckett.model.UserMatchVote;

public class SubmitVoteController implements Controller {

	private UserMatchVoteDAO userMatchVoteDAO;
	
	private TeamDAO teamDAO;
	
	private MatchMasterDAO matchMasterDAO;
	
	public UserMatchVoteDAO getUserMatchVoteDAO() {
		return userMatchVoteDAO;
	}

	public void setUserMatchVoteDAO(UserMatchVoteDAO userMatchVoteDAO) {
		this.userMatchVoteDAO = userMatchVoteDAO;
	}

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	public MatchMasterDAO getMatchMasterDAO() {
		return matchMasterDAO;
	}

	public void setMatchMasterDAO(MatchMasterDAO matchMasterDAO) {
		this.matchMasterDAO = matchMasterDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute(SessionAttributes.USER);
		if (user == null){
			throw new AuthenticationException("INVALID USER");
		}
		UserMatchVote umv = userMatchVoteDAO.findByUserAndMatch(user.getId(), Long.valueOf(request.getParameter("matchId")));
		String teamId = request.getParameter("teamId");
		TeamMaster teamMaster = null;
		if (!"draw".equalsIgnoreCase(teamId)){
			teamMaster = teamDAO.get(Long.valueOf(request.getParameter("teamId")));
		}
		if (umv == null){
			umv = new UserMatchVote();
			umv.setUser(user);
			umv.setMatchMaster(matchMasterDAO.get(Long.valueOf(request.getParameter("matchId"))));
			umv.setUserVote(teamMaster);
			userMatchVoteDAO.save(umv);
		}else{
			umv.setUserVote(teamMaster);
			userMatchVoteDAO.update(umv);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", 1);
		return new ModelAndView("genericStatus", model);
		
	}

}
