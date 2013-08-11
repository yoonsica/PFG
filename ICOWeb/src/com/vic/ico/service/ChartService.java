package com.vic.ico.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vic.beans.Chart;
import com.vic.fusioncharts.DataSet;

public interface ChartService {
	public void addChart(Map parameterMap);
	public void updateChart(Chart chart);
	public Chart getChartByChartId(String chartId);
	public List<Chart> getAllCharts();
	public void setChartXml(Chart chart,Map<String, String[]> map);
	public void getMSColumn2D(Chart chart,Map<String, String[]> map);
	public void getPie2D(Chart chart,Map<String, String[]> map);
	public void getMSStackedColumn2DLineDY(Chart chart,Map<String, String[]> map);
}
