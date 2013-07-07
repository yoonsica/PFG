package com.vic.ico.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.vic.beans.Condition;
import com.vic.ico.service.ConditionService;

public class ICOConfigAction extends ActionSupport {
	private ConditionService conditionService;
	private List<String> conditionHTMLList;
	public ConditionService getConditionService() {
		return conditionService;
	}
	public void setConditionService(ConditionService conditionService) {
		this.conditionService = conditionService;
	}
	public List<String> getConditionHTMLList() {
		return conditionHTMLList;
	}
	public void setConditionHTMLList(List<String> conditionHTMLList) {
		this.conditionHTMLList = conditionHTMLList;
	}
	@Override
	public String execute() throws Exception {
		conditionHTMLList = conditionService.getAllConditionsHtml();
		return SUCCESS;
	}

}
