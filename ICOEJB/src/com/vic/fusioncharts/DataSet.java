package com.vic.fusioncharts;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="DATASET")
public class DataSet implements Serializable{
	private String id;
	private String seriesName;
	private String showValues;
	private List<String> values;
	private String dataSetSql;
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

	public String getShowValues() {
		return showValues;
	}

	public void setShowValues(String showValues) {
		this.showValues = showValues;
	}

	@Transient
	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Column(length=1024)
	public String getDataSetSql() {
		return dataSetSql;
	}

	public void setDataSetSql(String dataSetSql) {
		this.dataSetSql = dataSetSql;
	}

	public DataSet(){}

	public DataSet(String seriesName, String dataSetSql) {
		super();
		this.seriesName = seriesName;
		this.dataSetSql = dataSetSql;
	}

	public String dataSetToXMLString(){
		StringBuilder sb = new StringBuilder("<dataset seriesName='");
		sb.append(seriesName).append("' showValues='").append(showValues).append("'");
		for (String value : values) {
			sb.append("<set value='").append(value).append("'/>");
		}
		sb.append("</dataset>");
		return sb.toString();
	}
}
