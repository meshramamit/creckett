package com.creckett.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.creckett.util.StringUtil;

@Entity
@Table(name="USER_PREFERENCE")
public class UserPreference extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7240531720343335803L;

	@OneToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column
	private Boolean showVideoTutorial;
	
	@Column
	private Boolean showAssistant;

	@Column
	private Boolean termsAccepted;
	
	/**
	 * @return the acceptTermsnConditions
	 */
	public Boolean getTermsAccepted() {
		return termsAccepted;
	}

	/**
	 * @param acceptTermsnConditions the acceptTermsnConditions to set
	 */
	public void setTermsAccepted(Boolean acceptTermsnConditions) {
		this.termsAccepted = acceptTermsnConditions;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the showVideoTutorial
	 */
	public Boolean getShowVideoTutorial() {
		return showVideoTutorial;
	}

	/**
	 * @param showVideoTutorial the showVideoTutorial to set
	 */
	public void setShowVideoTutorial(Boolean showVideoTutorial) {
		this.showVideoTutorial = showVideoTutorial;
	}

	/**
	 * @return the showAssistant
	 */
	public Boolean getShowAssistant() {
		return showAssistant;
	}

	/**
	 * @param showAssistant the showAssistant to set
	 */
	public void setShowAssistant(Boolean showAssistant) {
		this.showAssistant = showAssistant;
	}

   public String getShowVideoTutorialStr(){
	   return StringUtil.getStringValueFromBoolean(this.getShowVideoTutorial());
   }
   
   public String getShowAssistantStr(){
	   return StringUtil.getStringValueFromBoolean(this.getShowAssistant());
   }

   public String getTermsAcceptedStr(){
	   return StringUtil.getStringValueFromBoolean(this.getTermsAccepted());
   }

}
