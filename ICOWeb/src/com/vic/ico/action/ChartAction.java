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
	private String Pie2D_caption;
	//private String MSStackedColumn2DLineDY_caption;
	private String MSStackedColumn2DLineDY_xAxisName;
	private String MSStackedColumn2DLineDY_PYaxisname;
	private String MSStackedColumn2DLineDY_SYAxisName;
	//private String[] dataSet_seriesName;
	private String MSStackedColumn2DLineDY_categoriesSql;
	//private String[] MSStackedColumn2DLineDY_dataSetsSql;
	//private String[] MSStackedColumn2DLineDY_lineSetsSql;
	private String Pie2D_setSql;
	private ChartService chartService;
	private List<Chart> chartsList;
	public List<Chart> getChartsList() {
		return chartsList;
	}
	public void setChartsList(List<Chart> chartsList) {
		this.chartsList = chartsList;
	}
	public String getMSStackedColumn2DLineDY_categoriesSql() {
		return MSStackedColumn2DLineDY_categoriesSql;
	}
	public void setMSStackedColumn2DLineDY_categoriesSql(
			String mSStackedColumn2DLineDY_categoriesSql) {
		MSStackedColumn2DLineDY_categoriesSql = mSStackedColumn2DLineDY_categoriesSql;
	}
	/*public String[] getMSStackedColumn2DLineDY_dataSetsSql() {
		return MSStackedColumn2DLineDY_dataSetsSql;
	}
	public void setMSStackedColumn2DLineDY_dataSetsSql(
			String[] mSStackedColumn2DLineDY_dataSetsSql) {
		MSStackedColumn2DLineDY_dataSetsSql = mSStackedColumn2DLineDY_dataSetsSql;
	}
	public String[] getMSStackedColumn2DLineDY_lineSetsSql() {
		return MSStackedColumn2DLineDY_lineSetsSql;
	}
	public void setMSStackedColumn2DLineDY_lineSetsSql(
			String[] mSStackedColumn2DLineDY_lineSetsSql) {
		MSStackedColumn2DLineDY_lineSetsSql = mSStackedColumn2DLineDY_lineSetsSql;
	}*/
	public ChartService getChartService() {
		return chartService;
	}
	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}
	/*public String getMSStackedColumn2DLineDY_caption() {
		return MSStackedColumn2DLineDY_caption;
	}
	public void setMSStackedColumn2DLineDY_caption(
			String mSStackedColumn2DLineDY_caption) {
		MSStackedColumn2DLineDY_caption = mSStackedColumn2DLineDY_caption;
	}*/
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
	public String getPie2D_caption() {
		return Pie2D_caption;
	}
	public void setPie2D_caption(String pie2d_caption) {
		Pie2D_caption = pie2d_caption;
	}
	public String getMSStackedColumn2DLineDY_xAxisName() {
		return MSStackedColumn2DLineDY_xAxisName;
	}
	public void setMSStackedColumn2DLineDY_xAxisName(
			String mSStackedColumn2DLineDY_xAxisName) {
		MSStackedColumn2DLineDY_xAxisName = mSStackedColumn2DLineDY_xAxisName;
	}
	public String getMSStackedColumn2DLineDY_PYaxisname() {
		return MSStackedColumn2DLineDY_PYaxisname;
	}
	public void setMSStackedColumn2DLineDY_PYaxisname(
			String mSStackedColumn2DLineDY_PYaxisname) {
		MSStackedColumn2DLineDY_PYaxisname = mSStackedColumn2DLineDY_PYaxisname;
	}
	public String getMSStackedColumn2DLineDY_SYAxisName() {
		return MSStackedColumn2DLineDY_SYAxisName;
	}
	public void setMSStackedColumn2DLineDY_SYAxisName(
			String mSStackedColumn2DLineDY_SYAxisName) {
		MSStackedColumn2DLineDY_SYAxisName = mSStackedColumn2DLineDY_SYAxisName;
	}
	public String getPie2D_setSql() {
		return Pie2D_setSql;
	}
	public void setPie2D_setSql(String pie2d_setSql) {
		Pie2D_setSql = pie2d_setSql;
	}
	/*public String[] getDataSet_seriesName() {
		return dataSet_seriesName;
	}
	public void setDataSet_seriesName(String[] dataSet_seriesName) {
		this.dataSet_seriesName = dataSet_seriesName;
	}*/
	@Override
	public String execute() throws Exception {
		chartsList = chartService.getAllCharts();
		return SUCCESS;
	}
	
	public String add() throws Exception {
		System.out.println(chartName+chartType+Pie2D_caption);
		ActionContext context=ActionContext.getContext();     
        Map parameterMap=context.getParameters();  
        System.out.println(parameterMap.get("MSStackedColumn2DLineDY_caption"));
        //测试这个map能否不需要getset方法就能获得前台参数，然后重新设计sql，下面的方法参数要加入map
        if (chartType.equals("MSStackedColumn2DLineDY")) {
        	chartService.addMSStackedColumn2DLineDY(parameterMap);
		}
        /*if (chartType.equals("MSStackedColumn2DLineDY")) {
			Chart chart = new MSStackedColumn2DLineDY(MSStackedColumn2DLineDY_caption, MSStackedColumn2DLineDY_xAxisName, MSStackedColumn2DLineDY_PYaxisname, MSStackedColumn2DLineDY_SYAxisName, MSStackedColumn2DLineDY_categoriesSql, MSStackedColumn2DLineDY_dataSetsSql, MSStackedColumn2DLineDY_lineSetsSql);
			chartService.addChart(chart);
        }*/
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
