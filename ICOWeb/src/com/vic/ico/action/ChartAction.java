package com.vic.ico.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vic.beans.Chart;
import com.vic.fusioncharts.DataSet;
import com.vic.fusioncharts.MSStackedColumn2DLineDY;
import com.vic.ico.service.ChartService;

public class ChartAction extends ActionSupport{
	private String chartName;
	private String chartType;
	private String chartId;
	private ChartService chartService;
	private List<Chart> chartsList;
	public List<Chart> getChartsList() {
		return chartsList;
	}
	public void setChartsList(List<Chart> chartsList) {
		this.chartsList = chartsList;
	}
	public ChartService getChartService() {
		return chartService;
	}
	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	public String add() throws Exception {
		ActionContext context=ActionContext.getContext();     
        Map parameterMap=context.getParameters();  
        chartService.addChart(parameterMap);
		return SUCCESS;
	}
	
	public String update() throws Exception{
        /*if (chartType.equals("MSStackedColumn2DLineDY")) {
			Chart chart = new MSStackedColumn2DLineDY(MSStackedColumn2DLineDY_caption, MSStackedColumn2DLineDY_xAxisName, MSStackedColumn2DLineDY_PYaxisname, MSStackedColumn2DLineDY_SYAxisName, MSStackedColumn2DLineDY_categoriesSql, MSStackedColumn2DLineDY_dataSetsSql, MSStackedColumn2DLineDY_lineSetsSql);
			chart.setChartId(chartId);
			chartService.updateChart(chart);
        }*/
		return "updateSuccess";
	}
}
