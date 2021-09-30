package com.creckett.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "MATCH_SCORE", uniqueConstraints={@UniqueConstraint(columnNames={"MATCH_ID","sessionId","matchOver"})})
public class MatchScore extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4630192652395458627L;

	@ManyToOne
	@JoinColumn(name = "MATCH_ID")
	private MatchMaster matchId;

	@Column
	private Integer sessionId;

	@Column
	private Integer matchOver;

	@Column
	private Integer runs;
	
	@Column
	private String score;

	@Column
	private Integer wickets;

	@Column
	private Boolean invalidate;

	@Column
	private Date timestamp;

	public MatchMaster getMatchId() {
		return matchId;
	}

	public void setMatchId(MatchMaster matchId) {
		this.matchId = matchId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getMatchOver()
	{
		return matchOver;
	}

	public void setMatchOver( Integer matchOver )
	{
		this.matchOver = matchOver;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Integer getRuns() {
	    return runs;
	}

	public void setRuns(Integer runs) {
	    this.runs = runs;
	}

	public Integer getWickets() {
		return wickets;
	}

	public void setWickets(Integer wickets) {
		this.wickets = wickets;
	}

	public Boolean getInvalidate() {
		return invalidate;
	}

	public void setInvalidate(Boolean invalidate) {
		this.invalidate = invalidate;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
