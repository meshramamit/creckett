package com.creckett.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creckett.exception.AuthenticationException;
import com.creckett.model.TeamMaster;
import com.creckett.service.TeamService;

public class TeamController extends MultiActionController {

    private TeamService teamService;

    public ModelAndView loadTeams(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    List<TeamMaster> teams = teamService.getTeamListForAdmin();
	    Map<String, Object> model = new HashMap<String, Object>();
	    model.put("teams", teams);
	    return new ModelAndView("loadTeams", model);
	} else {
	    throw new AuthenticationException("USER NOT VALID");
	}
    }

    public ModelAndView addTeam(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    String teamName = request.getParameter("team");
	    String teamAlias = request.getParameter("teamAlias");
	    String teamLogoPath = request.getParameter("teamLogo");
	    if (teamName != null && teamAlias != null && teamLogoPath != null) {
		TeamMaster team = teamService.addTeam(teamName, teamAlias,
			teamLogoPath);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("team", team);
		return new ModelAndView("createTeam", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + teamName + ":"
				+ teamAlias + ":" + teamLogoPath);
	    }
	} else {
	    throw new AuthenticationException("Invalid user");
	}
    }

    public ModelAndView updateTeam(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    String teamName = request.getParameter("team");
	    String teamAlias = request.getParameter("teamAlias");
	    String teamLogoPath = request.getParameter("teamLogo");
	    if (teamName != null && teamAlias != null && teamLogoPath != null) {
		String teamId = request.getParameter("teamId");
		int status = -1;
		if (teamId != null && !teamId.isEmpty()) {
		    status = teamService.updateTeam(teamId, teamName,
			    teamAlias, teamLogoPath);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		return new ModelAndView("teamGenericStatus", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + teamName + ":"
				+ teamAlias + ":" + teamLogoPath);
	    }
	} else {
	    throw new AuthenticationException("Invalid user");
	}
    }

    public ModelAndView deleteTeam(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String isAdmin = (String) request.getSession().getAttribute("isAdmin");
	if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
	    String teamId = request.getParameter("teamId");
	    int status = -1;
	    if (teamId != null && !teamId.isEmpty()) {
		status = teamService.deleteTeam(teamId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", status);
		return new ModelAndView("teamGenericStatus", model);
	    } else {
		throw new IllegalArgumentException(
			"Not valid request parameter." + teamId);
	    }
	} else {
	    throw new AuthenticationException("Invalid user");
	}
    }

    public TeamService getTeamService() {
	return teamService;
    }

    public void setTeamService(TeamService teamService) {
	this.teamService = teamService;
    }

}
