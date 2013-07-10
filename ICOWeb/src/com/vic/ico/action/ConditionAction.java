package com.vic.ico.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.vic.beans.CodeName;
import com.vic.beans.Condition;
import com.vic.ico.service.CodeNameService;
import com.vic.ico.service.ConditionService;

public class ConditionAction extends ActionSupport{
	private String name;//英文名
	private String label;//中文名
	private String type;//条件类型，select或者checkbox
	private List<CodeName> codeNameList;//从数据库查出的可供选择的条目集合，用来展示给前台
	private String[] codeNameSelected;//前台复选框用户选择的值集合
	private String conditionId;
	private Condition condition;
	private CodeNameService codeNameService;
	private ConditionService conditionService;
	private List<String> codeChecked= new ArrayList<String>();
	public List<String> getCodeChecked() {
		return codeChecked;
	}
	public void setCodeChecked(List<String> codeChecked) {
		this.codeChecked = codeChecked;
	}
	public String getConditionId() {
		return conditionId;
	}
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
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
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
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
	
	public String toBeEdit() throws Exception{
	    condition = conditionService.getConditionById(conditionId);
	    codeNameList = codeNameService.getAllCodeName();//全部可选的codeName 
	    //设置已经选择的复选框
	    Set<CodeName> set = condition.getCodeNameSet();
	    for (CodeName codeName : set) {
			codeChecked.add(codeName.getCode());
		}
		return "toBeEdit";
	}
	
	public String edit() throws Exception{
		System.out.println(conditionId);
		conditionService.updateCondition(conditionId,name,label,type,codeNameSelected);
		return SUCCESS;
	}
}
