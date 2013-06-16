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
		LogUtil.log("查找缺陷首页数据", Level.INFO, null);
		try {
			Query query=entityManager.createNamedQuery(sql);
			LogUtil.log("查找成功", Level.INFO, null);
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("查找失败", Level.INFO, re);
			throw re;
		}
	}

}
