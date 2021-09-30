package com.creckett.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class LiveMatchCommentary extends BusinessObject {

	private static long commentaryCnt=0;
	
	public static void resetCommentaryCn(){
		commentaryCnt=0;
	}
	
	public LiveMatchCommentary(){
		this.commentaryId = commentaryCnt++;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -305193161852960188L;

	@Column
	@GeneratedValue
	private Long commentaryId;
	
	@Column
	private String commentaryText;
	
	/**
	 * @return the commentoryId
	 */
	public Long getCommentaryId() {
		return commentaryId;
	}

	/**
	 * @param commentoryId the commentoryId to set
	 */
	public void setCommentaryId(Long commentaryId) {
		this.commentaryId = commentaryId;
	}

	/**
	 * @return the commentoryText
	 */
	public String getCommentaryText() {
		return commentaryText;
	}

	/**
	 * @param commentoryText the commentoryText to set
	 */
	public void setCommentaryText(String commentaryText) {
		this.commentaryText = commentaryText;
	}

	
	
}
