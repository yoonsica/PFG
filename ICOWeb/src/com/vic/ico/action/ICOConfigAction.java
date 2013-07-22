package com.vic.ico.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.vic.beans.Chart;
import com.vic.beans.Condition;
import com.vic.ico.service.ChartService;
import com.vic.ico.service.ConditionService;

public class ICOConfigAction extends ActionSupport {
	private ConditionService conditionService;
	private ChartService chartService;
	private List<Chart> chartList;
	private List<String> conditionHTMLList;
	private List<Condition> conditionList;
	private List<String> conditionChecked;//条件选择复选框的值集合
	private List<String> chartChecked;
	public List<String> getChartChecked() {
		return chartChecked;
	}
	public void setChartChecked(List<String> chartChecked) {
		this.chartChecked = chartChecked;
	}
	public List<String> getConditionChecked() {
		return conditionChecked;
	}
	public void setConditionChecked(List<String> conditionChecked) {
		this.conditionChecked = conditionChecked;
	}
	public List<Condition> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<Condition> conditionList) {
		this.conditionList = conditionList;
	}
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
	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}
	
	public List<Chart> getChartList() {
		return chartList;
	}
	public void setChartList(List<Chart> chartList) {
		this.chartList = chartList;
	}
	@Override
	public String execute() throws Exception {
		//conditionHTMLList = conditionService.getAllConditionsHtml();
		System.out.println(conditionChecked);
		
	/*	根据conditionChecked获得Condition集合，然后给page的Conditionset赋值
	 * 	List<Condition> list = 
		Page page = new Page();
		page.setConditionSet(new HashSet<Condition>(conditionChecked));*/
		return SUCCESS;
	}
	
	public String getJson() throws Exception{
		System.out.println("getJson()");
		setConditionList(conditionService.getAllConditions());
		chartList = chartService.getAllCharts();
		return "icojson";
	}

}
