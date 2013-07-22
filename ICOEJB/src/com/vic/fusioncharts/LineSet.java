package com.vic.fusioncharts;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table
public class LineSet implements Serializable{
	private String seriesName="百分比";
	private String color = "ff0000";
	private String showValues = "0";
	private String lineThickness = "4";
	private Set<String> valueSet;
	private String id;
	private String lineSetSql;
	@Id
	@GeneratedValue
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getShowValues() {
		return showValues;
	}

	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}

	public String getLineThickness() {
		return lineThickness;
	}

	public void setLineThickness(String lineThickness) {
		this.lineThickness = lineThickness;
	}

	@Transient
	public Set<String> getValueSet() {
		return valueSet;
	}

	public void setValueSet(Set<String> valueSet) {
		this.valueSet = valueSet;
	}
	
	@Column(length=1024)
	public String getLineSetSql() {
		return lineSetSql;
	}

	public void setLineSetSql(String lineSetSql) {
		this.lineSetSql = lineSetSql;
	}

	public LineSet(){}

	public LineSet(String seriesName, String lineSetSql) {
		super();
		this.seriesName = seriesName;
		this.lineSetSql = lineSetSql;
	}

	public String toXmlString(){
		StringBuilder sb = new StringBuilder("<lineSet seriesname='");
		sb.append(seriesName).append("' color='")
		.append(color).append("' showValues='").append(showValues)
		.append("' lineThickness=").append(lineThickness);
		for (String value : valueSet) {
			sb.append("<set value='").append(value).append("'/>");
		}
		sb.append("</lineSet>");
		return sb.toString();
	}
}
