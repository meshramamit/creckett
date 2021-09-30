/**
 * 
 */
package com.creckett.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Dhaval
 * 
 */
@Entity
public class MatchMaster extends BusinessObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4916180511528811580L;

	@Column(length = 150)
    private String matchName;

    @Column(length = 200)
    private String description;

    @Column
    private Date matchDate;

    /*
     * @Column private String teamOne;
     * 
     * @Column private String teamOneAlias;
     * 
     * @Column private String teamTwo;
     * 
     * @Column private String teamTwoAlias;
     */

    @ManyToOne
    @JoinColumn(name = "TEAM_1")
    private TeamMaster team1;

    @ManyToOne
    @JoinColumn(name = "TEAM_2")
    private TeamMaster team2;

    @Column(name = "OVERS")
    private Integer over;

    @Column
    private String winner;
    
    @Column
    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MATCH_MASTER_ID")
    private List<Market> markets;

    public String getMatchName() {
	return matchName;
    }

    public void setMatchName(String matchName) {
	this.matchName = matchName;
    }

    public String getWinner() {
	return winner;
    }

    public void setWinner(String winner) {
	this.winner = winner;
    }

    public Date getMatchDate() {
	return matchDate;
    }

    public void setMatchDate(Date matchDate) {
	this.matchDate = matchDate;
    }

    /*
     * public String getTeamOne() { return teamOne; }
     * 
     * public void setTeamOne(String teamOne) { this.teamOne = teamOne; }
     * 
     * public String getTeamTwo() { return teamTwo; }
     * 
     * public void setTeamTwo(String teamTwo) { this.teamTwo = teamTwo; }
     */

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public List<Market> getMarkets() {
	return markets;
    }

    public void setMarkets(List<Market> markets) {
	this.markets = markets;
    }

    /*
     * public String getTeamOneAlias() { return teamOneAlias; }
     * 
     * public void setTeamOneAlias(String teamOneAlias) { this.teamOneAlias =
     * teamOneAlias; }
     * 
     * public String getTeamTwoAlias() { return teamTwoAlias; }
     * 
     * public void setTeamTwoAlias(String teamTwoAlias) { this.teamTwoAlias =
     * teamTwoAlias; }
     */

    public Integer getOver() {
	return over;
    }

    public void setOver(Integer over) {
	this.over = over;
    }

    public TeamMaster getTeam1() {
	return team1;
    }

    public void setTeam1(TeamMaster team1) {
	this.team1 = team1;
    }

    public TeamMaster getTeam2() {
	return team2;
    }

    public void setTeam2(TeamMaster team2) {
	this.team2 = team2;
    }

	public MatchStatus getStatus() {
		return status;
	}

	public void setStatus(MatchStatus status) {
		this.status = status;
	}

}
