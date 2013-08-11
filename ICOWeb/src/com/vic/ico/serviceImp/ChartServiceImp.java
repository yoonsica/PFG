package com.vic.ico.serviceImp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ceit.ico.dao.ChartDao;
import com.vic.beans.Chart;
import com.vic.fusioncharts.Categories;
import com.vic.fusioncharts.DataSet;
import com.vic.fusioncharts.LabelValue;
import com.vic.fusioncharts.LineSet;
import com.vic.fusioncharts.MSColumn2D;
import com.vic.fusioncharts.MSStackedColumn2DLineDY;
import com.vic.fusioncharts.Pie2D;
import com.vic.ico.handler.AddChartHandler;
import com.vic.ico.service.ChartService;

public class ChartServiceImp implements ChartService {
	private AddChartHandler handler;
	private ChartDao chartDao;
	public AddChartHandler getHandler() {
		return handler;
	}
	public void setHandler(AddChartHandler handler) {
		this.handler = handler;
	}
	public ChartDao getChartDao() {
		return chartDao;
	}
	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}
	@Override
	public void updateChart(Chart chart) {
		chartDao.update(chart);
	}
	@Override
	public Chart getChartByChartId(String chartId) {
		return chartDao.getChartById(chartId);
	}
	
	public void getMSStackedColumn2DLineDY(Chart chart,Map<String, String[]> map){
		Categories categories = ((MSStackedColumn2DLineDY)chart).getCategories();
		String categoriesSql = categories.getCategoriesSql();
		categories.setCategory(chartDao.nameQuery(categoriesSql,map));
		Set<DataSet> dataSets = ((MSStackedColumn2DLineDY)chart).getDataSets();
		for (DataSet dataSet : dataSets) {
			dataSet.setValues(chartDao.nameQuery(dataSet.getDataSetSql(), map));
		}
		Set<LineSet> lineSets = ((MSStackedColumn2DLineDY)chart).getLineSets();
		for (LineSet lineSet : lineSets) {
			lineSet.setValueList(chartDao.nameQuery(lineSet.getLineSetSql(), map));
		}
		chart.setChartXmlStr(chart.toXMLStr());
	}
	public void getPie2D(Chart chart,Map<String, String[]> map){
		Pie2D pie2d = (Pie2D)chart;
		String setSql = pie2d.getSetSql();
		List<Object[]> tempList = chartDao.nameQuery(setSql, map);
		List<LabelValue> lvList = new ArrayList<LabelValue>();
		for (Object[] objects : tempList) {
			lvList.add(new LabelValue(objects[0].toString(), objects[1].toString()));
		}
		pie2d.setLvSet(lvList);
		pie2d.setChartXmlStr(pie2d.toXMLStr());
	}
	
	public void getMSColumn2D(Chart chart,Map<String, String[]> map){
		Categories categories = ((MSColumn2D)chart).getCategories();
		String categoriesSql = categories.getCategoriesSql();
		categories.setCategory(chartDao.nameQuery(categoriesSql,map));
		Set<DataSet> dataSets = ((MSColumn2D)chart).getDataSets();
		for (DataSet dataSet : dataSets) {
			dataSet.setValues(chartDao.nameQuery(dataSet.getDataSetSql(), map));
		}
		chart.setChartXmlStr(chart.toXMLStr());
	}
	
	@Override
	public List<Chart> getAllCharts() {
		List<Chart> chartList = chartDao.findAllCharts();
		Map<String, String[]> map = new HashMap<String, String[]>();
		//对每个chart生成xml
		for (Chart chart : chartList) {
			try {
				Method method = this.getClass().getMethod("get"+chart.getChartType(),new Class<?>[]{Chart.class,Map.class});
				handler.invoke(this, method, new Object[]{chart,null});
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return chartList;
	}

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
			e.printStackTrace();
		}
	}
	public void addPie2D(Map parameterMap) {
		String[] caption = (String[]) parameterMap.get("Pie2D_caption");
		String[] setSql = (String[]) parameterMap.get("Pie2D_setSql");
		Pie2D chart = new Pie2D(caption[0], setSql[0]);
		chartDao.insert(chart);
	}
	
	public void addMSColumn2D(Map parameterMap) {
		String[] caption = (String[]) parameterMap.get("MSColumn2D_caption");
		String[] xAxisName = (String[]) parameterMap.get("MSColumn2D_xAxisName");
		String[] yAxisName = (String[]) parameterMap.get("MSColumn2D_yAxisName");
		String[] categoriesSql = (String[]) parameterMap.get("MSColumn2D_categoriesSql");
		String[] dataSet_seriesName = (String[]) parameterMap.get("MSColumn2D_dataSet_seriesName");
		String[] dataSet_color = (String[]) parameterMap.get("MSColumn2D_dataSet_color");
		String[] dataSetSql = (String[]) parameterMap.get("MSColumn2D_dataSetsSql");
		Set<DataSet> dataSets = new HashSet<DataSet>();
		for (int i = 0; i < dataSetSql.length; i++) {
			dataSets.add(new DataSet(dataSet_seriesName[i],dataSetSql[i],dataSet_color[0]));
		}
		Categories categories = new Categories(categoriesSql[0]);
		MSColumn2D chart = new MSColumn2D(caption[0], xAxisName[0], yAxisName[0], categories, dataSets);
		chartDao.insert(chart);
	}

	@Override
	public void addChart(Map parameterMap) {
		String[] chartTypes = (String[]) parameterMap.get("chartType");
		String chartType = chartTypes[0];
		try {
			Method method = this.getClass().getMethod("add"+chartType,new Class<?>[]{Map.class});
			handler.invoke(this, method, new Object[]{parameterMap});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setChartXml(Chart chart,Map<String, String[]> map) {
		try {
			Method method = this.getClass().getMethod("get"+chart.getChartType(),new Class<?>[]{Chart.class,Map.class});
			if (map.size()==0) {
				map = null;
			}
			handler.invoke(this, method, new Object[]{chart,map});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
}
