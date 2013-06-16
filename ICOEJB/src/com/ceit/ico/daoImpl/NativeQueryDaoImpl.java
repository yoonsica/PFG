package com.ceit.ico.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ceit.ico.dao.NativeQueryDao;
import com.ceit.ico.dto.CodeNameDTO;

@Stateless
public class NativeQueryDaoImpl implements NativeQueryDao {
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public String getQuerySql(String functionId,String queryTypeId) {
		String sql = "select t.query from fz_query t where t.function_id="
				+ functionId + " and t.query_type_id=" + queryTypeId;
		Query query = entityManager.createNativeQuery(sql);
		return (String) query.getSingleResult();
	}

	@Override
	public String getConditionSql(String id) {
		Query query = entityManager.createNativeQuery("select t.query from FZ_CONDITION t where t.id=" + id);
		return (String) query.getSingleResult();
	}	
	
	@Override
	public List<CodeNameDTO> getCodeNameDTOList(String id, boolean addAll) {
		List<CodeNameDTO> codeNameDTOList = new ArrayList<CodeNameDTO>();
		if (addAll) {
			CodeNameDTO allClassDTO = new CodeNameDTO();
			allClassDTO.setName("全部");
			allClassDTO.setCode("-1");
			codeNameDTOList.add(allClassDTO);
		}
		String sqlStr = this.getConditionSql(id);
		Query query=entityManager.createNativeQuery(sqlStr);
		@SuppressWarnings("unchecked")
		List<Object[]> tempList = query.getResultList();	
		for (Object[] objects : tempList) {
			CodeNameDTO dto = new CodeNameDTO();
			dto.setCode((String) objects[0]);
			dto.setName((String) objects[1]);
			codeNameDTOList.add(dto);
		}
		return codeNameDTOList;
	}
}
