package com.vic.ico.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.vic.beans.Chart;
import com.vic.beans.Condition;
import com.vic.beans.DataTable;
import com.vic.ico.service.ChartService;
import com.vic.ico.service.ConditionService;
import com.vic.ico.service.TableService;

public class ICOConfigAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1680771922589168690L;
	private ConditionService conditionService;
	private ChartService chartService;
	private TableService tableService;
	private List<Chart> chartList;
	private List<DataTable> tableList;
	private List<String> conditionHTMLList;
	private List<Condition> conditionList;
	private List<String> conditionChecked;//条件选择复选框的值集合
	private List<String> chartChecked;
	
	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}
	public List<DataTable> getTableList() {
		return tableList;
	}
	public void setTableList(List<DataTable> tableList) {
		this.tableList = tableList;
	}
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
	
	public String toAddPage() throws Exception{
		conditionList = conditionService.getAllConditions();
		chartList = chartService.getAllCharts();
		tableList = tableService.getAllTables();
		return "toAddPage";
	}

}
