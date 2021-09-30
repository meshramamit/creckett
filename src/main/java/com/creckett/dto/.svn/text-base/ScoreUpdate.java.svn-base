package com.creckett.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("scoreUpdate")
public class ScoreUpdate {
	
	@XStreamOmitField
	private long matchId;
	
	@XStreamAlias("overRuns")
	private OverRuns overRuns;

	@XStreamAlias("miniScorecard")
	private MiniScorecard miniScoreCard;
	
	public MiniScorecard getMiniScoreCard() {
		return miniScoreCard;
	}

	public void setMiniScoreCard(MiniScorecard miniScoreCard) {
		this.miniScoreCard = miniScoreCard;
	}

	public OverRuns getOverRuns() {
		return overRuns;
	}

	public void setOverRuns(OverRuns overRuns) {
		this.overRuns = overRuns;
	}

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	
	
}
