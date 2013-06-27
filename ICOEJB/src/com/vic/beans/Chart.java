package com.vic.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="CHART")
public class Chart {
	private String chartId;
	private String chartName;
	private String chartType;
	private String sqlStr;
	@ManyToMany(mappedBy="chartList")
	private List<Page> pageList;
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
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
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public List<Page> getPageList() {
		return pageList;
	}
	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}
}
