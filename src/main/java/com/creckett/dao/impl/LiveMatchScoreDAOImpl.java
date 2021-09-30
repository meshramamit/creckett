package com.creckett.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.creckett.dao.LiveMatchScoreDAO;
import com.creckett.logger.CreckettLogger;
import com.creckett.model.LiveMatchCommentary;
import com.creckett.model.LiveMatchScore;

public class LiveMatchScoreDAOImpl extends BaseHibernateDAO<LiveMatchScore> implements LiveMatchScoreDAO {

	private CreckettLogger creckettLogger = CreckettLogger.getInstance();
	
	@Override
	public void saveMatchScore(LiveMatchScore score) {
		if( score != null ){
			Session session = this.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try{
				session.save(score);
			}catch( Exception e ){
				creckettLogger.error(e.getMessage());
				e.printStackTrace();
			}finally{
				tx.commit();
				session.close();
			}
		}
	}

	
	
	@Override
	public void saveMatchCommentary( List<LiveMatchCommentary> commantaryList ) {
		if( commantaryList != null ){
			Session session = this.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try{
				int len = commantaryList.size();
				for ( int i=0; i<len; i++ ) {
				    session.save(commantaryList.get(i));
				    if ( i % 10 == 0 ) { 
				        session.flush();
				        session.clear();
				    }
				}
			}catch( Exception e ){
				creckettLogger.error(e.getMessage());
				e.printStackTrace();
			}finally{
				tx.commit();
				session.close();
			}
		}
	}
	

	@Override
	@Deprecated
	public void saveMatchScores( List<LiveMatchScore> scores ) {
		if( scores != null ){
			Session session = this.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try{
				int len = scores.size();
				for ( int i=0; i<len; i++ ) {
				    session.save(scores.get(i));
				    if ( i % 10 == 0 ) { 
				        session.flush();
				        session.clear();
				    }
				}
			}catch( Exception e ){
				creckettLogger.error(e.getMessage());
				e.printStackTrace();
			}finally{
				tx.commit();
				session.close();
			}
		}
	}

}
