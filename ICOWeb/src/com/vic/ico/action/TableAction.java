package com.vic.ico.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.vic.ico.service.TableService;

public class TableAction extends ActionSupport{
	private TableService tableService;
	
	public TableService getTableService() {
		return tableService;
	}

	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}

	public String add() throws Exception {
		ActionContext context=ActionContext.getContext();     
        Map parameterMap=context.getParameters();  
        tableService.addTable(parameterMap);
		return "add";
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
