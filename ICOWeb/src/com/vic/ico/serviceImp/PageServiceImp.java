package com.vic.ico.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;

import org.springframework.transaction.annotation.Transactional;

import com.ceit.ico.dao.ChartDao;
import com.ceit.ico.dao.ConditionDao;
import com.ceit.ico.dao.PageDao;
import com.ceit.ico.dao.TableDao;
import com.vic.beans.Chart;
import com.vic.beans.Condition;
import com.vic.beans.DataTable;
import com.vic.beans.Page;
import com.vic.beans.TFoot;
import com.vic.ico.handler.AddChartHandler;
import com.vic.ico.service.ChartService;
import com.vic.ico.service.PageService;

public class PageServiceImp implements PageService{
	private PageDao pageDao; 
	private ConditionDao conditionDao;
	private ChartDao chartDao;
	private TableDao tableDao;
	private ChartService chartService;
	public ChartService getChartService() {
		return chartService;
	}

	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}

	public ConditionDao getConditionDao() {
		return conditionDao;
	}

	public void setConditionDao(ConditionDao conditionDao) {
		this.conditionDao = conditionDao;
	}

	public ChartDao getChartDao() {
		return chartDao;
	}

	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}

	public TableDao getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	@TransactionAttribute
	public void addPage(Map parameterMap) {
		String[] conditionChecked = ((String[])parameterMap.get("conditionChecked"));
		String[] chartChecked = ((String[])parameterMap.get("chartChecked"));
		String[] tableChecked = ((String[])parameterMap.get("tableChecked"));
		String pageName = ((String[])parameterMap.get("pageName"))[0];
		Set<Condition> conditionSet = new HashSet<Condition>();
		for (String conditionId : conditionChecked) {
			conditionSet.add(conditionDao.getConditionById(conditionId));
		}
		Set<Chart> chartSet = new HashSet<Chart>();
		for (String chartId : chartChecked) {
			chartSet.add(chartDao.getChartById(chartId));
		}
		Set<DataTable> tableSet = new HashSet<DataTable>();
		for (String tableId : tableChecked) {
			tableSet.add(tableDao.getTableById(tableId));
		}
		Page page = new Page(pageName, conditionSet, chartSet, tableSet);
		pageDao.insert(page);
	}

	@Override
	public List<Page> getAllPages() {
		return pageDao.getAllPages();
	}
	@TransactionAttribute
	public Page getPageById(String pageId,Map<String, String[]> map) {
		Page page = pageDao.findPageByPageId(pageId);
		Set<Chart> chartSet = page.getChartSet();
		//为chart的xmlStr赋值
		for (Chart chart : chartSet) {
				chartService.setChartXml(chart, map);
		}
		Set<DataTable> tableSet = page.getTableSet();
		for (DataTable dataTable : tableSet) {
			if (dataTable.gettBodySql()!=null) {
				dataTable.setTbodyList(tableDao.nameQuery(dataTable.gettBodySql(), map));
			}
			Set<TFoot> set = dataTable.gettFoots();
			for (TFoot tFoot : set) {
				tFoot.setDataList(tableDao.nameQuery(tFoot.getSql(), map));
			}
			dataTable.setTableHTML(dataTable.toHTML());
		}
		return page;
	}


}
