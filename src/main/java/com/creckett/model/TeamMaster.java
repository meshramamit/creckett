package com.creckett.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TEAM_MASTER", uniqueConstraints = { @UniqueConstraint(columnNames = { "TEAM_NAME" }) })
public class TeamMaster extends BusinessObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -188392136808632544L;

	@Column(name = "TEAM_NAME")
    private String teamName;

    @Column(name = "TEAM_ALIAS")
    private String teamAlias;

    @Column(name = "TEAM_LOGO_PATH")
    private String teamLogoPath;

    public String getTeamName() {
	return teamName;
    }

    public void setTeamName(String teamName) {
	this.teamName = teamName;
    }

    public String getTeamAlias() {
	return teamAlias;
    }

    public void setTeamAlias(String teamAlias) {
	this.teamAlias = teamAlias;
    }

    public String getTeamLogoPath() {
	return teamLogoPath;
    }

    public void setTeamLogoPath(String teamLogoPath) {
	this.teamLogoPath = teamLogoPath;
    }

}
