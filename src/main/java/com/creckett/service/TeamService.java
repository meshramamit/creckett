package com.creckett.service;

import java.util.List;

import com.creckett.model.TeamMaster;

public interface TeamService {

    public List<TeamMaster> getTeamListForAdmin();

    public TeamMaster addTeam(String teamName, String teamAlias, String teamLogo);

    public int updateTeam(String teamId, String teamName, String teamAlias,
	    String teamLogo);

    public int deleteTeam(String teamId);

}
