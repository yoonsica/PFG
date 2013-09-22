package com.vic.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="CHART")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="chartType") 
public class Chart implements Serializable {
	private String chartId;
	private String chartType;
	@Transient
	private String chartXmlStr;
	private Set<Page> pageSet = new HashSet<Page>();
	
	public Chart() {
	}
	@Id
	@GeneratedValue
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	@ManyToMany(mappedBy="chartSet",fetch=FetchType.EAGER)
	public Set<Page> getPageSet() {
		return pageSet;
	}
	public void setPageSet(Set<Page> pageSet) {
		this.pageSet = pageSet;
	}
	@Column(name="chartType",insertable=false, updatable=false) 
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	@Transient
	public String getChartXmlStr() {
		return chartXmlStr;
	}
	public void setChartXmlStr(String chartXmlStr) {
		this.chartXmlStr = chartXmlStr;
	}
	public String toXMLStr(){
		return null;
	}
}
