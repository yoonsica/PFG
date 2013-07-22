package com.vic.ico.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ceit.ico.dao.ChartDao;
import com.vic.beans.Chart;
import com.vic.fusioncharts.Categories;
import com.vic.fusioncharts.DataSet;
import com.vic.fusioncharts.LineSet;
import com.vic.fusioncharts.MSStackedColumn2DLineDY;
import com.vic.ico.service.ChartService;

public class ChartServiceImp implements ChartService {

	private ChartDao chartDao;
	public ChartDao getChartDao() {
		return chartDao;
	}
	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}
	@Override
	public void addChart(Chart chart) {
		chartDao.insert(chart);
	}
	@Override
	public void updateChart(Chart chart) {
		chartDao.update(chart);
	}
	@Override
	public Chart getChartByChartId(String chartId) {
		return chartDao.getChartById(chartId);
	}
	@Override
	public List<Chart> getAllCharts() {
		List<Chart> chartList = chartDao.findAllCharts();
		//对每个chart生成xml
		for (Chart chart : chartList) {
			if (chart.getChartType().equals("MSStackedColumn2DLineDY")) {
				Categories categories = ((MSStackedColumn2DLineDY)chart).getCategories();
				String categoriesSql = categories.getCategoriesSql();
				categories.setCategory(chartDao.nameQuery(categoriesSql,null));
				Set<DataSet> dataSets = ((MSStackedColumn2DLineDY)chart).getDataSets();
				for (DataSet dataSet : dataSets) {
					dataSet.setValues(chartDao.nameQuery(dataSet.getDataSetSql(), null));
				}
			}
		}
		return chartList;
	}

	@Override
	public void addMSStackedColumn2DLineDY(Map parameterMap) {
		String[] caption = (String[]) parameterMap.get("MSStackedColumn2DLineDY_caption");
		String[] xAxisName = (String[]) parameterMap.get("MSStackedColumn2DLineDY_xAxisName");
		String[] pYaxisname = (String[]) parameterMap.get("MSStackedColumn2DLineDY_PYaxisname");
		String[] sYAxisName = (String[]) parameterMap.get("MSStackedColumn2DLineDY_SYAxisName");
		String[] categoriesSql = (String[]) parameterMap.get("MSStackedColumn2DLineDY_categoriesSql");
		String[] dataSetsSql = (String[]) parameterMap.get("MSStackedColumn2DLineDY_dataSetsSql");
		String[] lineSetSql = (String[]) parameterMap.get("MSStackedColumn2DLineDY_lineSetsSql");
		String[] dataSet_seriesNames = (String[]) parameterMap.get("MSStackedColumn2DLineDY_dataSet_seriesName");
		String[] lineSet_seriesName = (String[]) parameterMap.get("MSStackedColumn2DLineDY_lineSet_seriesName");

		Set<DataSet> dataSets = new HashSet<DataSet>();
		for (int i = 0; i < dataSetsSql.length; i++) {
			dataSets.add(new DataSet(dataSet_seriesNames[i],dataSetsSql[i]));
		}
		Set<LineSet> lineSets = new HashSet<LineSet>();
		for (int i = 0; i < lineSetSql.length; i++) {
			lineSets.add(new LineSet(lineSet_seriesName[i],lineSetSql[i]));
		}
		
		Categories categories = new Categories(categoriesSql[0]);
		MSStackedColumn2DLineDY chart = new MSStackedColumn2DLineDY(caption[0], xAxisName[0], pYaxisname[0], sYAxisName[0], categories, dataSets, lineSets);
		try {
			chartDao.insert(chart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
