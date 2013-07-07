package com.ceit.ico.daoImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ceit.ico.dao.CodeNameDao;
import com.vic.beans.CodeName;
@Stateless
public class CodeNameDaoImpl implements CodeNameDao{

	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<CodeName> getAllCodeNames() {
		Query query = entityManager.createQuery("from CodeName where display>0 order by code");
		return query.getResultList();
	}

	//没用到，不打算让用户插入条目。
	public boolean insertCodeName(CodeName codeName) {
		//第一次录入
		/*Query query1 = entityManager.createNativeQuery("select t.name,t.code,t.display from device.volmap t");
		List<Object[]> tempList = query1.getResultList();
		for (Object[] objects : tempList) {
			CodeName temp = new CodeName();
			temp.setCode(String.valueOf(objects[1]));
			temp.setName(String.valueOf(objects[0]));
			temp.setDisplay(Integer.parseInt(String.valueOf(objects[2])));
			entityManager.persist(temp);
		}
		System.out.println("OK");*/
		Query query = entityManager.createQuery("from CodeName where code="+codeName.getCode());
		List<CodeName> list = query.getResultList();
		if (null!=list) {
			for (CodeName codeName2 : list) {
				if (codeName.equals(codeName2)) {
					System.out.println("重复的CodeName对象，插入失败");
					return false;
				}
			}
			//entityManager.persist(codeName);
			System.out.println("插入成功");
		}
		return true;
	}

	public boolean updateCodeName(CodeName codeName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CodeName> getCodeNamesByCodes(String[] codeNameSelected){
		StringBuilder sb = new StringBuilder();
		sb.append("from CodeName where code  in(");
		for (String code : codeNameSelected) {
			sb.append("'").append(code).append("',");
		}
		String sql = sb.substring(0,sb.length()-1)+")";
		System.out.println(sql);
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}

}
