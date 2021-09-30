package com.creckett.dto;

import java.util.Date;

import com.creckett.model.MatchMaster;

public class MatchScore {

	private MatchMaster matchId;

	private int sessionId;

	private int matchOver;

	private int runs;
	
	private String score;

	private int wickets;

	private boolean invalidate;

	private Date timestamp;

	public MatchMaster getMatchId() {
		return matchId;
	}

	public void setMatchId(MatchMaster matchId) {
		this.matchId = matchId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getMatchOver() {
		return matchOver;
	}

	public void setMatchOver(int matchOver) {
		this.matchOver = matchOver;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public boolean getInvalidate() {
		return invalidate;
	}

	public void setInvalidate(boolean invalidate) {
		this.invalidate = invalidate;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public static MatchScore buildObjectFromModel( com.creckett.model.MatchScore model ){
		MatchScore matchScore = new MatchScore();
		if( model != null && model.getRuns() != null){
			matchScore.setInvalidate(model.getInvalidate().booleanValue());
			matchScore.setMatchId(model.getMatchId());
			matchScore.setMatchOver(model.getMatchOver().intValue());
			matchScore.setRuns(model.getRuns().intValue());
			matchScore.setScore(model.getScore());
			matchScore.setSessionId(model.getSessionId().intValue());
			matchScore.setTimestamp(model.getTimestamp());
			matchScore.setWickets(model.getWickets().intValue());
		}
		return matchScore;
	}
}
