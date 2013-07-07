package com.ceit.ico.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ceit.ico.dao.NativeQueryDao;
import com.vic.beans.CodeName;

@Stateless
public class NativeQueryDaoImpl implements NativeQueryDao {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public String getQuerySql(String functionId,String queryTypeId) {
		String sql = "select t.query from fz_query t where t.function_id="
				+ functionId + " and t.query_type_id=" + queryTypeId;
		Query query = entityManager.createNativeQuery(sql);
		return (String) query.getSingleResult();
	}

	public String getConditionSql(String id) {
		Query query = entityManager.createNativeQuery("select t.query from FZ_CONDITION t where t.id=" + id);
		return (String) query.getSingleResult();
	}	
	
	public List<CodeName> getCodeNameDTOList(String id, boolean addAll) {
		List<CodeName> codeNameDTOList = new ArrayList<CodeName>();
		if (addAll) {
			CodeName allClassDTO = new CodeName();
			allClassDTO.setName("全部");
			allClassDTO.setCode("-1");
			codeNameDTOList.add(allClassDTO);
		}
		String sqlStr = this.getConditionSql(id);
		Query query=entityManager.createNativeQuery(sqlStr);
		@SuppressWarnings("unchecked")
		List<Object[]> tempList = query.getResultList();	
		for (Object[] objects : tempList) {
			CodeName dto = new CodeName();
			dto.setCode((String) objects[0]);
			dto.setName((String) objects[1]);
			codeNameDTOList.add(dto);
		}
		return codeNameDTOList;
	}
}
