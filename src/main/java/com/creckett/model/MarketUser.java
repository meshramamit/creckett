package com.creckett.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MarketUser extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3089512509671012909L;

	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
	private User userId;

	@Column
	private Date creationDate;

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
