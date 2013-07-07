package com.vic.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="PAGE")
public class Page implements Serializable{
	private String pageId;
	private String pageUrl;
	private String pageName;
	private List<Condition> conditionList = new ArrayList<Condition>();
	private List<Chart> chartList = new ArrayList<Chart>();
	private List<DataTable> tableList = new ArrayList<DataTable>();
	
	@Id
	@GeneratedValue
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name="Page_Condition",joinColumns={@JoinColumn(name="pageId",referencedColumnName="pageId")},
	inverseJoinColumns={ @JoinColumn(name = "conditionId", referencedColumnName = "conditionId") })
	public List<Condition> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<Condition> conditionList) {
		this.conditionList = conditionList;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name="Page_Chart",joinColumns={@JoinColumn(name="pageId",referencedColumnName="pageId")},
	inverseJoinColumns={ @JoinColumn(name = "chartId", referencedColumnName = "chartId") })
	public List<Chart> getChartList() {
		return chartList;
	}
	public void setChartList(List<Chart> chartList) {
		this.chartList = chartList;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name="Page_DataTable",joinColumns={@JoinColumn(name="pageId",referencedColumnName="pageId")},
	inverseJoinColumns={ @JoinColumn(name = "tableId", referencedColumnName = "tableId") })
	public List<DataTable> getTableList() {
		return tableList;
	}
	public void setTableList(List<DataTable> tableList) {
		this.tableList = tableList;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
