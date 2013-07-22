package com.vic.ico.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vic.beans.Chart;
import com.vic.fusioncharts.DataSet;

public interface ChartService {
	public void addChart(Chart chart);
	public void updateChart(Chart chart);
	public Chart getChartByChartId(String chartId);
	public List<Chart> getAllCharts();
	public void addMSStackedColumn2DLineDY(Map parameterMap);
}
