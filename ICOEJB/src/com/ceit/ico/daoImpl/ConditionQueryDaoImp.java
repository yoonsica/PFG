package com.ceit.ico.daoImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.ceit.ico.dao.ConditionQueryDao;
import com.vic.beans.Condition;

@Stateless
public class ConditionQueryDaoImp implements ConditionQueryDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<Condition> getConditions(String pageId) {
		List<String> conditonIds = getConditonIds(pageId);
		StringBuilder sb1 = new StringBuilder();
		sb1.append("select t from Condition t where conditionId in(");
		for (String id : conditonIds) {
			sb1.append(id).append(",");
		}
		String sql = sb1.substring(0, sb1.length()-1)+")";
		Query query =em.createQuery(sql);
		List<Condition> resultList = query.getResultList();
		//根据每个Condition对象的sql来生成datalist属性值
		for (Condition condition : resultList) {
			query = em.createQuery(condition.getSqlStr());//这里注意用QL查询，新建一个CodeName表，相当于原来的CodeMap
			condition.setDataList(query.getResultList());
		}
		return resultList;
	}
	
	private List<String> getConditonIds(String pageId){
		StringBuilder sb = new StringBuilder();
		sb.append("select conditonId from ConditionPageMap where pageId=")
				.append(pageId);
		Query query = em.createNativeQuery(sb.toString());
		return query.getResultList();
	}

}
