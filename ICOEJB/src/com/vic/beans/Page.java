package com.vic.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="PAGE")
public class Page implements Serializable{
	private String pageId;
	private String pageUrl;
	private String pageName;
	private Set<Condition> conditionSet = new HashSet<Condition>();
	private Set<Chart> chartSet = new HashSet<Chart>();
	private Set<DataTable> tableSet = new HashSet<DataTable>();
	
	public Page() {
	}
	
	public Page(String pageName, Set<Condition> conditionSet,
			Set<Chart> chartSet, Set<DataTable> tableSet) {
		this.pageName = pageName;
		this.conditionSet = conditionSet;
		this.chartSet = chartSet;
		this.tableSet = tableSet;
	}


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
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="Page_Condition",joinColumns={@JoinColumn(name="pageId",referencedColumnName="pageId")},
	inverseJoinColumns={ @JoinColumn(name = "conditionId", referencedColumnName = "conditionId") })
	@OrderBy(value = "conditionId ASC")
	public Set<Condition> getConditionSet() {
		return conditionSet;
	}
	public void setConditionSet(Set<Condition> conditionSet) {
		this.conditionSet = conditionSet;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="Page_Chart",joinColumns={@JoinColumn(name="pageId",referencedColumnName="pageId")},
	inverseJoinColumns={ @JoinColumn(name = "chartId", referencedColumnName = "chartId") })
	@OrderBy(value = "chartId ASC")
	public Set<Chart> getChartSet() {
		return chartSet;
	}
	public void setChartSet(Set<Chart> chartSet) {
		this.chartSet = chartSet;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="Page_DataTable",joinColumns={@JoinColumn(name="pageId",referencedColumnName="pageId")},
	inverseJoinColumns={ @JoinColumn(name = "tableId", referencedColumnName = "tableId") })
	public Set<DataTable> getTableSet() {
		return tableSet;
	}
	public void setTableSet(Set<DataTable> tableSet) {
		this.tableSet = tableSet;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
