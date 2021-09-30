package com.creckett.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.creckett.dao.UserPrefererenceDAO;
import com.creckett.model.User;
import com.creckett.model.UserPreference;

/**
 * @author vipul
 *
 */
public class UserPreferenceDAOImpl extends BaseHibernateDAO<UserPreference> implements UserPrefererenceDAO {

	/**
	 * @param user
	 * @return
	 */
	public UserPreference getUserPreference(User user){
		UserPreference userPreference = null;
		Criteria criteria = this.getSession().createCriteria(UserPreference.class);
		criteria.add(Restrictions.eq("user.id", user.getId()));
		userPreference = (UserPreference) criteria.uniqueResult();
		return userPreference;
	}
	
	public UserPreference getUserPreference(Long id){
		return get(UserPreference.class, id);
	}
	
}
