package com.creckett.service.impl;

import java.util.List;

import com.creckett.dao.TeamDAO;
import com.creckett.model.TeamMaster;
import com.creckett.service.TeamService;
import com.creckett.util.StringUtil;

public class TeamServiceImpl implements TeamService {

    private TeamDAO teamDAO;

    @Override
    public TeamMaster addTeam(String teamName, String teamAlias, String teamLogo) {
	TeamMaster team = new TeamMaster();
	team.setTeamName(teamName);
	team.setTeamAlias(teamAlias);
	team.setTeamLogoPath(teamLogo);
	return teamDAO.save(team);
    }

    @Override
    public int deleteTeam(String teamId) {
	long id = StringUtil.getLongFromString(teamId);
	TeamMaster team = teamDAO.get(id);
	if (team != null) {
	    teamDAO.delete(team);
	    return 1;
	}
	return 0;
    }

    @Override
    public List<TeamMaster> getTeamListForAdmin() {
	return teamDAO.getTeamListForAdmin();
    }

    @Override
    public int updateTeam(String teamId, String teamName, String teamAlias,
	    String teamLogo) {
	long id = StringUtil.getLongFromString(teamId);
	TeamMaster team = teamDAO.get(id);
	team.setTeamAlias(teamAlias);
	team.setTeamLogoPath(teamLogo);
	team.setTeamName(teamName);
	return (teamDAO.update(team) != null) ? 1 : 0;
    }

    public TeamDAO getTeamDAO() {
	return teamDAO;
    }

    public void setTeamDAO(TeamDAO teamDAO) {
	this.teamDAO = teamDAO;
    }

}
