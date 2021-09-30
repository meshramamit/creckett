package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.creckett.dao.TeamDAO;
import com.creckett.model.TeamMaster;

public class TeamDAOImpl extends BaseHibernateDAO<TeamMaster> implements
	TeamDAO {

    @Override
    public TeamMaster get(Long id) {
	return super.get(TeamMaster.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TeamMaster> getTeamListForAdmin() {
	Query query = this.getSession().createQuery("from TeamMaster");
	return query.list();
    }

    @Override
    public TeamMaster getTeamByName(String teamName) {
	Criteria criteria = this.getSession().createCriteria(TeamMaster.class);
	criteria.add(Restrictions.eq("teamName", teamName));
	return (TeamMaster) criteria.uniqueResult();
    }

}
