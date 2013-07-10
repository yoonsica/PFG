package com.vic.ico.serviceImp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ceit.ico.dao.CodeNameDao;
import com.ceit.ico.dao.ConditionDao;
import com.vic.beans.CodeName;
import com.vic.beans.Condition;
import com.vic.ico.service.ConditionService;
import com.vic.ico.types.ConditionType;

public class ConditionServiceImp implements ConditionService {
	private ConditionDao conditionDao;
	private CodeNameDao codeNameDao;
	public ConditionDao getConditionDao() {
		return conditionDao;
	}

	public void setConditionDao(ConditionDao conditionDao) {
		this.conditionDao = conditionDao;
	}
	public CodeNameDao getCodeNameDao() {
		return codeNameDao;
	}
	public void setCodeNameDao(CodeNameDao codeNameDao) {
		this.codeNameDao = codeNameDao;
	}
	@Override
	public List<String> getConditionsHtmlByPageId(String pageId) {
		List<String> resultList = new ArrayList<String>();
		Set<Condition> list = conditionDao.getConditionsByPageId(pageId);
		for (Condition condition : list) {
			if (condition.getType().equals("select")) {
				resultList.add(getSelectHtml(condition));
			} else if (condition.getType().equals("checkbox")) {
				resultList.add(getCheckBoxHtml(condition));
			}else if (condition.getType().equals("dateTimePicker")) {
				resultList.add(getDateInternalHtml(condition));
			}
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
	private String getSelectHtml(Condition condition) {
		StringBuilder sb = new StringBuilder(
				"<div class=\"conditionDiv\"><label for=\"")
				.append(condition.getName()).append("\">")
				.append(condition.getLabel()).append("</label><select id=\"")
				.append(condition.getName()).append("\">");
		sb.append("<option value=\"all\">全部</option>");
		for (CodeName codeName : condition.getCodeNameSet()) {
			sb.append("<option value=\"").append(codeName.getCode())
					.append("\">").append(codeName.getName())
					.append("</option>");
		}
		sb.append("</select></div>");
		return sb.toString();
	}

	/**
	 * 如果查询条件是复选框，生产复选框的html的函数
	 * 
	 * @param dataList
	 * @param name
	 * @return
	 */
	private String getCheckBoxHtml(Condition condition) {
		return null;
	}
	
	private String getDateInternalHtml(Condition condition){
		StringBuilder sb = new StringBuilder("<div class=\"conditionDiv\"><label for=\"");
		sb.append(condition.getName()).append("\">").append(condition.getLabel()).append("</label>");
		sb.append("<input id=\"").append(condition.getName()).append("\" class=\"easyui-datebox\" readonly=\"true\" required=\"required\" value=\"2013-12-31\" />");
		return sb.toString();
	}

	@Override
	public List<String> getAllConditionsHtml() {
		List<String> resultList = new ArrayList<String>();
		List<Condition> list = conditionDao.getAllConditions();
		for (Condition condition : list) {
			if (condition.getType().equals("select")) {
				resultList.add(getSelectHtml(condition));
			} else if (condition.getType().equals(ConditionType.checkbox)) {
				resultList.add(getCheckBoxHtml(condition));
			}else if (condition.getType().equals(ConditionType.dateInternal)) {
				resultList.add(getDateInternalHtml(condition));
			}
		}
		return resultList;
	}

	@Override
	public boolean addNewCondition(String name, String label,String type,
			String[] items) {
		//本来用的是List<String>的参数，结果报异常，困扰一天，list是可以序列化的，但是里面的string是不能序列化的，所以报错。
		Condition condition = new Condition();
		condition.setLabel(label);
		condition.setName(name);
		condition.setType(type);
		if (type.equals("select")||type.equals("checkbox")) {
			if (null==conditionDao.getConditionByName(name)) {
				List<CodeName> list = codeNameDao.getCodeNamesByCodes(items);
				condition.setCodeNameSet(new HashSet<CodeName>(list));
				return conditionDao.insertNewCondition(condition);
			}else {
				System.out.println("name重复");
				return false;
			}
		}else {
			return conditionDao.insertNewCondition(condition);
		}
	}

	@Override
	public List<Condition> getConditionsByPageId(String pageId) {
		return new ArrayList<Condition>(conditionDao.getConditionsByPageId(pageId));
	}

	@Override
	public List<Condition> getAllConditions() {
		return conditionDao.getAllConditions();
	}

	@Override
	public Condition getConditionById(String conditionId) {
		return conditionDao.getConditionById(conditionId);
	}

	@Override
	public void updateCondition(String conditionId, String name, String lable,
			String type, String[] codeNameSelected) {
		if (null!=conditionId) {
			Set<CodeName> codeNameSet = new HashSet<CodeName>(codeNameDao.getCodeNamesByCodes(codeNameSelected));
			Condition condition = new Condition(conditionId, name, lable, type, null, codeNameSet);
			conditionDao.update(condition);
		}
		
	}
}
