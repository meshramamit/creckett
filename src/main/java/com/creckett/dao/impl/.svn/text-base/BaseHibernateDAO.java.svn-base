package com.creckett.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.creckett.model.BusinessObject;

public class BaseHibernateDAO<T extends BusinessObject> extends
		HibernateDaoSupport {

	public T save(T businessObject) {
		if( businessObject!= null && businessObject.getId() == null ){
			if( businessObject.getCreateDate() == null ){
				businessObject.setCreateDate(new Date());
			}
		}
		businessObject.setUpdateDate(new Date());
		this.getHibernateTemplate().save(businessObject);
		return businessObject;

	}
	
	public void saveOrUpdateAll(List<T> businessObjects){
		this.getHibernateTemplate().saveOrUpdateAll(businessObjects);
	}

	public T get(Class<T> objectClass, Long id) {
		return objectClass.cast(this.getHibernateTemplate()
				.get(objectClass, id));
	}

	public void delete(T businessObject) {
		this.getHibernateTemplate().delete(businessObject);
	}

	public T update(T businessObject) {
		if( businessObject!= null && businessObject.getId() == null ){
			if( businessObject.getCreateDate() == null ){
				businessObject.setCreateDate(new Date());
			}
		}
		businessObject.setUpdateDate(new Date());
		this.getHibernateTemplate().update(businessObject);
		return businessObject;
	}
}
