package com.ceit.defect.dao.impl;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ceit.defect.dao.IndexDefectDao;
import com.ceit.defect.util.LogUtil;

@Stateless
public class IndexDefectDaoImpl implements IndexDefectDao {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findIndexResult(String sql) {
		LogUtil.log("����ȱ����ҳ����", Level.INFO, null);
		try {
			Query query=entityManager.createNamedQuery(sql);
			LogUtil.log("���ҳɹ�", Level.INFO, null);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("����ʧ��", Level.INFO, re);
			throw re;
		}
	}

}
