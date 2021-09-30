package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.creckett.dao.MatchScoreDAO;
import com.creckett.model.MatchScore;

public class MatchScoreDAOImpl extends BaseHibernateDAO<MatchScore> implements
	MatchScoreDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<MatchScore> getMatchScore(Long matchId) {
	Query matchScoreQuery = this.getSession().createQuery(
		" from MatchScore where matchId.id = :id");
	matchScoreQuery.setParameter("id", matchId);
	return matchScoreQuery.list();
    }

    @Override
    public MatchScore getMatchScoreForOver(Long matchId, int over,
	    Integer matchSession) {
	Criteria criteria = this.getSession().createCriteria(MatchScore.class);
	criteria.add(Restrictions.eq("matchOver", over)).add(
		Restrictions.eq("sessionId", matchSession)).createCriteria(
		"matchId").add(Restrictions.eq("id", matchId));
	return (MatchScore) criteria.uniqueResult();
    }

    @Override
    public MatchScore get(long matchId, int sessionId, int over) {
	Query matchScoreQuery = this.getSession().getNamedQuery(
		"getMatchScoreForOver");
	// .createQuery(
	// " from MatchScore where matchId.id = :id and sessionId = :session and matchOver = :over");
	matchScoreQuery.setParameter("id", matchId);
	matchScoreQuery.setParameter("session", sessionId);
	matchScoreQuery.setParameter("over", over);
	return (MatchScore) matchScoreQuery.uniqueResult();
    }

    public MatchScore getLatestInvalidatedOver(Long matchId) {

	Query query = this.getSession().getNamedQuery(
		"getLatestInvalidatedOver");
	query.setParameter("matchId", matchId);
	query.setMaxResults(1);
	return (MatchScore) query.uniqueResult();
    }

    public MatchScore getLatestMatchScore(Long matchId) {
	Query query = this.getSession().getNamedQuery("getLatestScore");
	query.setParameter("matchId", matchId);
	query.setMaxResults(1);
	return (MatchScore) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MatchScore> getMatchScoreForOvers(Long matchId, int minOver,
	    int maxOver, Integer matchSession) {
	Query matchScoreQuery = this.getSession().getNamedQuery(
		"getMatchScoreBetweenOvers");
	// .createQuery(
	// " from MatchScore where matchId.id = :id and sessionId = :session and matchOver between :minOver and :maxOver ");
	matchScoreQuery.setParameter("id", matchId);
	matchScoreQuery.setParameter("session", matchSession);
	matchScoreQuery.setParameter("minOver", minOver);
	matchScoreQuery.setParameter("maxOver", maxOver);
	return matchScoreQuery.list();
    }

}
