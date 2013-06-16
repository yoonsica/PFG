package com.ceit.defect.dao.impl;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ceit.defect.dao.VoltageClassDefectDao;
import com.ceit.defect.util.LogUtil;

@Stateless
public class VoltageClassDefectDaoImpl implements VoltageClassDefectDao {

	@PersistenceContext
	protected EntityManager entityManager;
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getDefectByVoltageClass(String sql,
			String devSelectCode, String startTime, String endTime) {
		LogUtil.log("getDefectByVoltageClass", Level.INFO, null);
		try {
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, devSelectCode);
			query.setParameter(2, startTime);
			query.setParameter(3,endTime);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("getDefectByVoltageClass", Level.INFO, re);
			throw re;
		}
	}

}
