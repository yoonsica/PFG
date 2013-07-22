package com.ceit.ico.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.vic.beans.Chart;

@Remote
public interface ChartDao {
	public List<Chart> getCharts(String pageId);
	public void insert(Chart chart);
	public void update(Chart chart);
	public Chart getChartById(String chartId);
	public List<Chart> findAllCharts();
	/**
	 * 执行除命名参数查询以外的本地查询
	 * @param sql
	 * @return
	 */
	public List nativeQuery(String sql);
	/**
	 * 执行命名参数查询
	 * @param sql
	 * @param paraMap 查询条件和值
	 */
	public List nameQuery(String sql,Map<String, String[]> paraMap);
}
