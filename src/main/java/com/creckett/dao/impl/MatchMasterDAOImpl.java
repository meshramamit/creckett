package com.creckett.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.creckett.dao.MatchMasterDAO;
import com.creckett.model.MatchMaster;
import com.creckett.model.MatchStatus;

@SuppressWarnings("unchecked")
public class MatchMasterDAOImpl extends BaseHibernateDAO<MatchMaster> implements
		MatchMasterDAO {

	@Override
	public MatchMaster get(Long id) {
		return get(MatchMaster.class, id);
	}

	public List<MatchMaster> getListOfUpcomingMatches(Date fromDate, Date toDate) {

		Query query = this.getSession().createQuery(" from MatchMaster match where " +
				"match.matchDate between :fromDate and " +
				":toDate and match.status = :status");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		query.setParameter("status", MatchStatus.ACTIVE);
		return query.list();
	}

	@Override
	public List<MatchMaster> getListOfPlayingMatches() {
		Query query = this.getSession().createQuery(" from MatchMaster match where " +
				"match.status = :status");
		query.setParameter("status", MatchStatus.PLAYING);
		return query.list();
	}
}
