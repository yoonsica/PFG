package com.vic.ico.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vic.beans.Chart;
import com.vic.beans.Condition;
import com.vic.beans.DataTable;
import com.vic.beans.Page;
import com.vic.ico.service.ChartService;
import com.vic.ico.service.ConditionService;
import com.vic.ico.service.PageService;
import com.vic.ico.service.TableService;

public class PageAction extends ActionSupport{
	private PageService pageService;
	private List<Page> pageList;
	private String pageId;
	private List<Chart> chartList;
	private List<DataTable> tableList;
	private List<Condition> conditionList;
	private ConditionService conditionService;
	private ChartService chartService;
	private TableService tableService;
	private Page page;
	
	public ConditionService getConditionService() {
		return conditionService;
	}

	public void setConditionService(ConditionService conditionService) {
		this.conditionService = conditionService;
	}

	public ChartService getChartService() {
		return chartService;
	}

	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}

	public TableService getTableService() {
		return tableService;
	}

	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}

	public List<Chart> getChartList() {
		return chartList;
	}

	public void setChartList(List<Chart> chartList) {
		this.chartList = chartList;
	}

	public List<DataTable> getTableList() {
		return tableList;
	}

	public void setTableList(List<DataTable> tableList) {
		this.tableList = tableList;
	}

	public List<Condition> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<Condition> conditionList) {
		this.conditionList = conditionList;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public String toAddPage() throws Exception{
		conditionList = conditionService.getAllConditions();
		chartList = chartService.getAllCharts();
		tableList = tableService.getAllTables();
		return "toAddPage";
	}
	public String allPages() throws Exception {
		pageList = pageService.getAllPages();
		return "allPages";
	}
	
	public String add() throws Exception {
		ActionContext context=ActionContext.getContext();     
        Map parameterMap=context.getParameters();  
        pageService.addPage(parameterMap);
		return "add";
	}
	
	public String show() throws Exception {
		System.out.println(pageId+"****************");
		ActionContext context=ActionContext.getContext();     
        Map parameterMap=context.getParameters();
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (Object key : parameterMap.keySet()) {
			if (!(key.toString().equals("pageId"))) {
				map.put(key.toString(), (String[]) parameterMap.get(key));
			}
		}
        page = pageService.getPageById(pageId,map);
		return "show";
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
