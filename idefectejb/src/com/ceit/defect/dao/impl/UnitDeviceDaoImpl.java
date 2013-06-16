package com.ceit.defect.dao.impl;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ceit.defect.dao.UnitDeviceDao;
import com.ceit.defect.util.LogUtil;

@Stateless
public class UnitDeviceDaoImpl implements UnitDeviceDao {

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Object[]> getUnitDeviceData(String sql) {
		LogUtil.log("getUnitDeviceData", Level.INFO, null);
		try {
			Query query = entityManager.createNativeQuery(sql);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("getUnitDeviceData", Level.INFO, re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<String> getVoltagesByIds(String[] dydj){
		LogUtil.log("getVoltagesByIds", Level.INFO, null);
		StringBuffer sql = new StringBuffer("select name from volmap where code in(");
		for (int i = 0; i < dydj.length-1; i++) {
			sql.append(dydj[i]+",");
		}
		sql.append(dydj[dydj.length-1]+") order by code desc");
		try {
			Query query = entityManager.createNativeQuery(sql.toString());
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("getVoltagesByIds", Level.INFO, re);
			throw re;
		}
	}


}
