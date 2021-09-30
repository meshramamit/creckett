package com.creckett.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
public class LiveMatchScore extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8403361419851089823L;
	
	@OneToOne
	private MatchMaster match;

	@Column
	private Double over;
	
	@Column
	private Long run;

	@Column
	private Integer wicket;
	
	@Column
	private String battingSide;


	@Column
	@Deprecated
	private String score;
	
	@Column
	@Deprecated
	private String commentaryText;
	
	
	/**
	 * @return the run
	 */
	public Long getRun() {
		return run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(Long run) {
		this.run = run;
	}

	/**
	 * @return the wicket
	 */
	public Integer getWicket() {
		return wicket;
	}

	/**
	 * @param wicket the wicket to set
	 */
	public void setWicket(Integer wicket) {
		this.wicket = wicket;
	}


	public MatchMaster getMatch() {
		return match;
	}

	public void setMatch(MatchMaster match) {
		this.match = match;
	}

	public Double getOver() {
		return over;
	}

	public void setOver(Double over) {
		this.over = over;
	}

	@Deprecated
	public String getScore() {
		return score;
	}

	@Deprecated
	public void setScore(String score) {
		this.score = score;
	}

	@Deprecated
	public String getCommentaryText() {
		return commentaryText;
	}

	@Deprecated
	public void setCommentaryText(String commentaryText) {
		this.commentaryText = commentaryText;
	}
	
	
	/**
	 * @return the battingSide
	 */
	public String getBattingSide() {
		return battingSide;
	}

	/**
	 * @param battingSide the battingSide to set
	 */
	public void setBattingSide(String battingSide) {
		this.battingSide = battingSide;
	}

}
