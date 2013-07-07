package com.vic.ico.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.vic.beans.CodeName;
import com.vic.ico.service.CodeNameService;
import com.vic.ico.service.ConditionService;

public class AddConditionAction extends ActionSupport{
	private String name;//英文名
	private String label;//中文名
	private String type;//条件类型，select或者checkbox
	private List<CodeName> codeNameList;//从数据库查出的可供选择的条目集合，用来展示给前台
	private String[] codeNameSelected;//前台复选框用户选择的值集合
	private CodeNameService codeNameService;
	private ConditionService conditionService;
	public ConditionService getConditionService() {
		return conditionService;
	}
	public void setConditionService(ConditionService conditionService) {
		this.conditionService = conditionService;
	}
	public CodeNameService getCodeNameService() {
		return codeNameService;
	}
	public void setCodeNameService(CodeNameService codeNameService) {
		this.codeNameService = codeNameService;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<CodeName> getCodeNameList() {
		return codeNameList;
	}
	public void setCodeNameList(List<CodeName> codeNameList) {
		this.codeNameList = codeNameList;
	}
	public String[] getCodeNameSelected() {
		return codeNameSelected;
	}
	public void setCodeNameSelected(String[] codeNameSelected) {
		this.codeNameSelected = codeNameSelected;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String execute() throws Exception {
		System.out.println("execute");
		codeNameList = codeNameService.getAllCodeName();//这里需要获取所有的codeName
		return SUCCESS;
	}
	
	/**
	 * 处理添加条件请求
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		conditionService.addNewCondition(name, label,type, codeNameSelected);
		return "json";
	}
}
