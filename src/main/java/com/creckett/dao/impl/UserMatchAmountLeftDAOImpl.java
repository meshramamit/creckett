package com.creckett.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.creckett.dao.UserMatchAmountLeftDAO;
import com.creckett.model.UserMatchAmountLeft;

public class UserMatchAmountLeftDAOImpl extends BaseHibernateDAO<UserMatchAmountLeft> implements UserMatchAmountLeftDAO {

	@Override
	public List<UserMatchAmountLeft> getMatchAmountLeft(List<Long> userIds) {
		if (userIds.isEmpty()){
			return Collections.emptyList();
		}
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from UserMatchAmountLeft matchAmounteLeft where matchAmounteLeft.user.id in (:userIds) order by matchAmounteLeft.createDate desc");
		query.setParameterList("userIds", userIds);
		return query.list();
	}

}
