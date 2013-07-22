package com.ceit.ico.daoImpl;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ceit.ico.dao.ConditionDao;
import com.vic.beans.Condition;
import com.vic.beans.Page;

@Stateless
public class ConditionDaoImpl implements ConditionDao {

	@PersistenceContext
	protected EntityManager em;

	public Set<Condition> getConditionsByPageId(String pageId) {
		StringBuilder sb = new StringBuilder();
		sb.append("select p from Page where pageId=")
				.append(pageId);
		Query query = em.createQuery(sb.toString());
		Page page = (Page) query.getSingleResult();
		return page.getConditionSet();
	}
	
	public List<Condition> getAllConditions() {
		StringBuilder sb1 = new StringBuilder();
		sb1.append("select t from Condition t order by t.conditionId");
		Query query =em.createQuery(sb1.toString());
		List<Condition> resultList = query.getResultList();
		return resultList;
	}

	public Condition getConditionByName(String name){
		StringBuilder sb = new StringBuilder("from Condition t where t.name='");
		sb.append(name).append("'");
		try {
			Query query =em.createQuery(sb.toString());
			if (null==query.getResultList()) {
				return null;
			}else {
				Condition condition = (Condition) query.getSingleResult();
				return condition;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public Condition getConditionById(String conditionId){
		StringBuilder sb = new StringBuilder("from Condition t where t.conditionId='");
		sb.append(conditionId).append("'");
		Query query =em.createQuery(sb.toString());
		Condition condition = (Condition) query.getSingleResult();
		return condition;
	}
	public boolean insertNewCondition(Condition condition) {
		try {
			em.persist(condition);
		} catch (Exception e) {
			System.out.println("error************");
			e.printStackTrace();
		}
		System.out.println("插入成功");
		return true;
	}

	@Override
	public void update(Condition condition) {
		try {
			em.merge(condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
