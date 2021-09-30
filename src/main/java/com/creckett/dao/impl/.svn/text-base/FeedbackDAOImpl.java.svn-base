package com.creckett.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.creckett.dao.FeedbackDAO;
import com.creckett.logger.CreckettLogger;
import com.creckett.model.Feedback;

public class FeedbackDAOImpl extends BaseHibernateDAO<Feedback> implements
		FeedbackDAO {
	
	//private static final Logger LOG = LoggerInstancesManager.getLogger(FeedbackDAOImpl.class);
	private CreckettLogger creckettLogger = CreckettLogger.getInstance();
	
	public List<Feedback> getFeedBackListByDate(Date fromDate,Date toDate){
		List<Feedback> feedbackList = null;
		Criteria criteria = this.getSession().createCriteria(Feedback.class);
		if(fromDate!=null){
			criteria.add(Restrictions.ge("createdDate", fromDate));
		}
		if(toDate!=null){
			criteria.add(Restrictions.le("createdDate",toDate));
		}
		feedbackList = (List<Feedback>)criteria.list();
		creckettLogger.info(">>getFeedBackListByDate>> feedbackList:"+feedbackList);
		return feedbackList;
	}


}
