package com.creckett.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.creckett.dao.UserDAO;
import com.creckett.model.User;

public class UserDAOImpl extends BaseHibernateDAO<User> implements UserDAO {

	@Override
	public User get(Long id) {
		return super.get(User.class, id);
	}

	public User getUserByEmailId(String emailId) {
		Criteria criteria = this.getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("emailId", emailId));
		return (User) criteria.uniqueResult();
	}

	@Override
	public int restMatchPlayingFlag() {
		Query query = this.getSession().createSQLQuery("update User set playing=0");
		query.executeUpdate();
		return 1;
	}

	@Override
	public List<User> getUsersByProfileIds(List<String> profileIds) {
		if (profileIds.isEmpty()){
			return Collections.emptyList();
		}
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from User where profile_id in (:profileIds)");
		query.setParameterList("profileIds", profileIds);
		return query.list();
	}

	@Override
	public int restMatchPlayingFlag(List<User> users) {
		if (users==null || users.isEmpty()){
			return 1;
		}
		List<Long> userIds = new ArrayList<Long>();
		for (User user : users){
			userIds.add(user.getId());
		}
		Query query = this.getSession().createSQLQuery("update User user set playing=0 where user.id in (:userIds) ");
		query.setParameterList("userIds", userIds);
		query.executeUpdate();
		return 1;
		
	}
}
