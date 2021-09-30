package com.creckett.dao.impl;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.creckett.dao.AdminDAO;
import com.creckett.model.Admin;

public class AdminDAOImpl extends HibernateDaoSupport implements AdminDAO {

    @Override
    public Admin getAdminUserByUsername(String username) {
	Query query = this.getSession().createQuery(
		"from Admin where upper(username)=:user");
	query.setParameter("user", username.toUpperCase());
	return (Admin) query.uniqueResult();
    }

    @Override
    public int save(Admin admin) {
	if (admin != null) {
	    this.getSession().save(admin);
	    return admin != null ? 1 : 0;
	}
	return 0;
    }

}
