package com.vic.ico.serviceImp;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ceit.ico.dao.ConditionQueryDao;
import com.vic.beans.CodeName;
import com.vic.beans.Condition;
import com.vic.ico.service.ConditionService;
import com.vic.ico.types.ConditionType;
import com.vic.ico.utils.GetInitialContext;

public class ConditionSserviceImp implements ConditionService {

	@Override
	public List<String> getConditionsHtml(String pageId) {
		List<String> resultList = new ArrayList<String>();
		try {
			InitialContext ctx = GetInitialContext.getContext();
			ConditionQueryDao conditionQueryDao = (ConditionQueryDao) ctx
					.lookup("ConditionQueryDaoImp/remote");
			List<Condition> list = conditionQueryDao.getConditions(pageId);
			for (Condition condition : list) {
				if (condition.getType().equals(ConditionType.select)) {
					resultList.add(getSelectHtml(condition.getDataList(), condition.getName()));
				}else if (condition.getType().equals(ConditionType.checkbox)) {
					resultList.add(getCheckBoxHtml(condition.getDataList(), condition.getName()));
				}
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * 条件是下拉列表的情况
	 * 
	 * @param dataList
	 * @param name
	 * @return
	 */
	private String getSelectHtml(List<CodeName> dataList, String name) {
		StringBuilder sb = new StringBuilder("<select name=\"").append(name)
				.append("\">");
		for (CodeName codeName : dataList) {
			sb.append("<option value=\"").append(codeName.getCode())
					.append("\">").append(codeName.getName())
					.append("</option>");
		}
		sb.append("</select>");
		return sb.toString();
	}

	/**
	 * 如果查询条件是复选框，生产复选框的html的函数
	 * 
	 * @param dataList
	 * @param name
	 * @return
	 */
	private String getCheckBoxHtml(List<CodeName> dataList, String name) {
		return null;
	}
}
